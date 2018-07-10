/*
 * Copyright 2018 Dragon-Labs.net
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 */

package de.dragonlabs.scaleadapter.library.exceptions;

import de.dragonlabs.scaleadapter.library.packet.ScalePacket;

public class NoMetaExistsException extends Exception
{
    public NoMetaExistsException( Class<? extends ScalePacket> packetClass)
    {
        super("The Packet " + packetClass + " haven't any ScalePacketMeta.");
    }
}
