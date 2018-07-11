/*
 * Copyright 2018 Dragon-Labs.net
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 */

package de.dragonlabs.scaleadapter.library.config;

import de.dragonlabs.scaleadapter.library.protocol.EventManager;
import de.dragonlabs.scaleadapter.library.protocol.PacketManager;

public class ScaleConfig
{
    /**
     * The hostname of the Server
     */
    private String hostname;
    /**
     * The port of the Server
     */
    private Integer port;

    /**
     * The PacketManager
     */
    private PacketManager packetManager;

    /**
     * The EventManager
     */
    private EventManager eventManager;

    public ScaleConfig(String hostname, Integer port, PacketManager packetManager, EventManager eventManager)
    {
        this.hostname = hostname;
        this.port = port;
        this.packetManager = packetManager;
        this.eventManager = eventManager;
    }

    /**
     * @return the hostname
     */
    public String getHostname() {
        return hostname;
    }

    /**
     * @return the port
     */
    public Integer getPort() {
        return port;
    }

    /**
     * Set the hostname
     * @param hostname the hostname
     * @return ScaleConfig the own object
     */
    public ScaleConfig setHostname(String hostname) {
        this.hostname = hostname;
        return this;
    }

    /**
     * Set the port
     * @param port the port
     * @return ScaleConfig the own object
     */
    public ScaleConfig setPort(Integer port) {
        this.port = port;
        return this;
    }

    /**
     * @return the PacketManager
     */
    public PacketManager getPacketManager() {
        return packetManager;
    }

    /**
     *
     * @return the EventHandler
     */
    public EventManager getEventManager() {
        return eventManager;
    }
}
