/*
 * Copyright 2018 Dragon-Labs.net
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 */

package de.dragonlabs.scaleadapter.library.protocol;

import de.dragonlabs.scaleadapter.library.exceptions.NoMetaExistsException;
import de.dragonlabs.scaleadapter.library.exceptions.PacketIdAlreadyExistsException;
import de.dragonlabs.scaleadapter.library.packet.ScalePacket;
import de.dragonlabs.scaleadapter.library.packet.ScalePacketMeta;

import java.util.HashMap;

public class PacketManager
{
    private HashMap<Byte, Class<? extends ScalePacket>> packetClasses;

    public PacketManager()
    {
        packetClasses = new HashMap<>();
    }

    /**
     * Register a new packet
     * @param packetClass the packetClass that need to be register
     * @return PacketManager his own object
     * @throws PacketIdAlreadyExistsException Is thrown the a packet will be registered with an id, that already exists.
     * @throws NoMetaExistsException Is thrown when you try to register an packet without the necessary meta
     */
    public PacketManager registerPacket(Class<? extends ScalePacket> packetClass) throws PacketIdAlreadyExistsException, NoMetaExistsException {


        ScalePacketMeta meta = packetClass.getAnnotation(ScalePacketMeta.class);
        if (meta == null)
        {
            throw new NoMetaExistsException(packetClass);
        }
        Byte id = meta.id();

        if(packetClasses.containsKey(id))
        {
            throw new PacketIdAlreadyExistsException(id, packetClass);
        }
        packetClasses.put(id, packetClass);
        return this;
    }


}
