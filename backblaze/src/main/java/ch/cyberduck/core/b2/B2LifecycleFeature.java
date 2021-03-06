package ch.cyberduck.core.b2;

/*
 * Copyright (c) 2002-2017 iterate GmbH. All rights reserved.
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
import ch.cyberduck.core.DisabledListProgressListener;
import ch.cyberduck.core.Path;
import ch.cyberduck.core.PathContainerService;
import ch.cyberduck.core.exception.BackgroundException;
import ch.cyberduck.core.exception.NotfoundException;
import ch.cyberduck.core.features.Lifecycle;
import ch.cyberduck.core.lifecycle.LifecycleConfiguration;

import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.util.List;

import synapticloop.b2.LifecycleRule;
import synapticloop.b2.exception.B2ApiException;
import synapticloop.b2.response.B2BucketResponse;

public class B2LifecycleFeature implements Lifecycle {

    private final PathContainerService containerService
            = new B2PathContainerService();

    private final B2Session session;
    private final B2FileidProvider fileid;

    public B2LifecycleFeature(final B2Session session, final B2FileidProvider fileid) {
        this.session = session;
        this.fileid = fileid;
    }

    @Override
    public LifecycleConfiguration getConfiguration(final Path container) throws BackgroundException {
        try {
            final List<B2BucketResponse> buckets = session.getClient().listBuckets();
            for(B2BucketResponse response : buckets) {
                if(response.getBucketName().equals(containerService.getContainer(container).getName())) {
                    final List<LifecycleRule> lifecycleRules = response.getLifecycleRules();
                    for(LifecycleRule rule : lifecycleRules) {
                        return new LifecycleConfiguration(
                                null == rule.getDaysFromUploadingToHiding() ? null : rule.getDaysFromUploadingToHiding().intValue(),
                                null,
                                null == rule.getDaysFromHidingToDeleting() ? null : rule.getDaysFromHidingToDeleting().intValue());
                    }
                    return LifecycleConfiguration.empty();
                }
            }
            throw new NotfoundException(container.getAbsolute());
        }
        catch(B2ApiException e) {
            throw new B2ExceptionMappingService().map("Failure to write attributes of {0}", e, container);
        }
        catch(IOException e) {
            throw new DefaultIOExceptionMappingService().map("Failure to write attributes of {0}", e, container);
        }
    }

    @Override
    public void setConfiguration(final Path container, final LifecycleConfiguration configuration) throws BackgroundException {
        try {
            session.getClient().updateBucket(
                fileid.getFileid(containerService.getContainer(container), new DisabledListProgressListener()),
                new B2BucketTypeFeature(session, fileid).convert(container.attributes().getAcl()),
                    new LifecycleRule(
                            null == configuration.getExpiration() ? null : configuration.getExpiration().longValue(),
                            null == configuration.getTransition() ? null : configuration.getTransition().longValue(),
                            StringUtils.EMPTY));
        }
        catch(B2ApiException e) {
            throw new B2ExceptionMappingService().map("Failure to write attributes of {0}", e, container);
        }
        catch(IOException e) {
            throw new DefaultIOExceptionMappingService().map("Failure to write attributes of {0}", e, container);
        }
    }
}
