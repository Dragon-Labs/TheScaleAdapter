/*
 * Copyright 2018 Dragon-Labs.net
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 */

package de.dragonlabs.scaleadapter.library.exceptions;

import de.dragonlabs.scaleadapter.library.packet.ScalePacket;

/**
 * This Exception is be called when someone try to register a packet with an id, that is already registered
 */
public class PacketIdAlreadyExistsException extends Exception
{
    public PacketIdAlreadyExistsException(Byte id, Class<? extends ScalePacket> packetClass)
    {
        super("Packet with ID: " + id + " already registered and can't use for " + packetClass);
    }
}
