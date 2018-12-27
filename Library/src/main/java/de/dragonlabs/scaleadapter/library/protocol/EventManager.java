/*
 * Copyright 2018 Dragon-Labs.net
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 */

package de.dragonlabs.scaleadapter.library.protocol;

import de.dragonlabs.scaleadapter.library.event.Listener;
import de.dragonlabs.scaleadapter.library.event.ScalePacketHandler;
import de.dragonlabs.scaleadapter.library.packet.ScalePacket;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

public class EventManager {
    private ArrayList<Listener> listeners;

    public EventManager()
    {
        this.listeners = new ArrayList<>();
    }

    /**
     * Register a new Listener for ScalePacketHandler
     * @param listener An object that extends from Listener
     * @return EventManager the orn Object
     */
    public EventManager registerListener(Listener listener)
    {
        listeners.add(listener);
        return this;
    }

    /**
     * Unregister an existing Listener for ScalePacketHandler
     * @param listener An object that extends from Listener
     * @return
     */
    public EventManager unregisterListener(Listener listener)
    {
        listeners.remove(listener);
        return this;
    }

    /**
     * Call all handler with the given Packet
     * @param packet the packet which should be used for the handler
     */
    public void call(ScalePacket packet)
    {
        listeners.stream().filter(Objects::nonNull).forEach(l -> {
            Arrays.stream(l.getClass().getMethods())
                    .filter(m -> m.isAnnotationPresent(ScalePacketHandler.class))
                    .filter(m -> m.getParameterTypes().length == 1)
                    .filter(m -> m.getParameterTypes()[0].isAssignableFrom(packet.getClass()))
                    .forEach(m -> {
                        try {
                            m.invoke(l, packet);
                        } catch (IllegalAccessException | InvocationTargetException e) {
                            e.printStackTrace();
                        }
                    });
        });
    }
}
