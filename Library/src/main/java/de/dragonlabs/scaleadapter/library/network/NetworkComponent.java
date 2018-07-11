/*
 * Copyright 2018 Dragon-Labs.net
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 */

package de.dragonlabs.scaleadapter.library.network;

import de.dragonlabs.scaleadapter.library.config.ScaleConfig;
import io.netty.channel.Channel;

public abstract class NetworkComponent
{
    ScaleConfig scaleConfig;
    Channel channel;

    NetworkComponent(ScaleConfig config)
    {
        this.scaleConfig = config;
    }

    /**
     * Starts the Server or the client connect to the server
     */
    abstract public void open();

    /**
     * Close the Server or the client close the connection to the server
     */
    abstract public void close();

    /**
     * @return if the server or client is open / connected
     */
    public Boolean isOpen() {
        return this.channel != null && this.channel.isActive();
    }

    /**
     *
     * @return the ScaleCOnfig
     */
    public ScaleConfig getScaleConfig() {
        return scaleConfig;
    }
}
