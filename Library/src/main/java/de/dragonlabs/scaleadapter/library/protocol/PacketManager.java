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

        if(id < 1)
        {
            throw new IllegalArgumentException("The Packet " + packetClass + " need to have a higher id, then 0");
        }

        if(packetClasses.containsKey(id))
        {
            throw new PacketIdAlreadyExistsException(id, packetClass);
        }
        packetClasses.put(id, packetClass);
        return this;
    }

    /**
     * Unregister an existing packet by ID
     * @param id The id of the packet that should be unregister
     * @return PacketManager his own object
     */
    public PacketManager unregisterPacket(Byte id)
    {
        packetClasses.remove(id);
        return this;
    }

    /**
     * Get the packet by Id
     * @param id The id of the packet
     * @return the packet object of the id
     * @throws IllegalAccessException if the class or its nullary constructor is not accessible.
     * @throws InstantiationException if this {@code Class} represents an abstract class,
     *          an interface, an array class, a primitive type, or void;
     *          or if the class has no nullary constructor;
     *          or if the instantiation fails for some other reason.
     */
    public ScalePacket getPacketById(Byte id) throws IllegalAccessException, InstantiationException {
        Class<? extends ScalePacket> clazz = packetClasses.get(id);
        if(clazz != null) {
            return clazz.newInstance();
        }
        return null;
    }

    /**
     * Get the Id by the PacketClass
     * @param packetClass the packetClass where you want the id from
     * @return the Id of the class
     */
    public Byte getIdByClass(Class<? extends ScalePacket> packetClass)
    {
        for(Byte id : packetClasses.keySet())
        {
            if(packetClasses.get(id).equals(packetClass)) return id;
        }
        return -1;
    }
}
