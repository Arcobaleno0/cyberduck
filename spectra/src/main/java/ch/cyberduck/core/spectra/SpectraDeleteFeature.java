package ch.cyberduck.core.spectra;

/*
 * Copyright (c) 2002-2016 iterate GmbH. All rights reserved.
 * https://cyberduck.io/
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 */

import ch.cyberduck.core.DefaultIOExceptionMappingService;
import ch.cyberduck.core.LoginCallback;
import ch.cyberduck.core.Path;
import ch.cyberduck.core.PathContainerService;
import ch.cyberduck.core.exception.BackgroundException;
import ch.cyberduck.core.s3.S3MultipleDeleteFeature;
import ch.cyberduck.core.s3.S3PathContainerService;

import java.io.IOException;
import java.security.SignatureException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.spectralogic.ds3client.Ds3Client;
import com.spectralogic.ds3client.commands.DeleteFolderRequest;
import com.spectralogic.ds3client.networking.FailedRequestException;

public class SpectraDeleteFeature extends S3MultipleDeleteFeature {

    private PathContainerService containerService
            = new S3PathContainerService();

    private final SpectraSession session;

    public SpectraDeleteFeature(final SpectraSession session) {
        super(session);
        this.session = session;
    }

    @Override
    public void delete(final List<Path> files, final LoginCallback prompt, final Callback callback) throws BackgroundException {
        try {
            final ArrayList<Path> filtered = new ArrayList<Path>(files);
            for(Iterator<Path> iter = filtered.iterator(); iter.hasNext(); ) {
                final Path file = iter.next();
                if(file.isVolume()) {
                    continue;
                }
                if(file.isDirectory()) {
                    final Ds3Client client = new SpectraClientBuilder().wrap(session);
                    client.deleteFolder(new DeleteFolderRequest(containerService.getContainer(file).getName(), containerService.getKey(file)));
                    iter.remove();
                }
            }
            super.delete(filtered, prompt, callback);
        }
        catch(FailedRequestException e) {
            throw new SpectraExceptionMappingService().map(e);
        }
        catch(IOException e) {
            throw new DefaultIOExceptionMappingService().map(e);
        }
        catch(SignatureException e) {
            throw new BackgroundException(e);
        }
    }
}
