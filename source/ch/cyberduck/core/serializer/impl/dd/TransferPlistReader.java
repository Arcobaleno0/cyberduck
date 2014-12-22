package ch.cyberduck.core.serializer.impl.dd;

/*
 * Copyright (c) 2002-2014 David Kocher. All rights reserved.
 * http://cyberduck.io/
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
 *
 * Bug fixes, suggestions and comments should be sent to:
 * feedback@cyberduck.io
 */

import ch.cyberduck.core.DeserializerFactory;
import ch.cyberduck.core.serializer.TransferDictionary;
import ch.cyberduck.core.transfer.Transfer;

import com.dd.plist.NSDictionary;

/**
 * @version $Id$
 */
public class TransferPlistReader extends PlistReader<Transfer> {

    private DeserializerFactory deserializer;

    public TransferPlistReader() {
        this.deserializer = new DeserializerFactory();
    }

    public TransferPlistReader(final DeserializerFactory deserializer) {
        this.deserializer = deserializer;
    }

    @Override
    public Transfer deserialize(final NSDictionary dict) {
        return new TransferDictionary(deserializer).deserialize(dict);
    }
}
