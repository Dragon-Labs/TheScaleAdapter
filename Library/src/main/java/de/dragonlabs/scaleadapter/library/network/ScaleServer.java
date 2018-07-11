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
import io.netty.channel.EventLoopGroup;

public abstract class ScaleServer extends NetworkComponent {

    protected EventLoopGroup master;
    protected EventLoopGroup deamon;

    protected Channel channel;

    public ScaleServer(ScaleConfig config) {
        super(config);
    }

    @Override
    public Boolean isOpen() {
        return this.channel != null && this.channel.isActive();
    }

    @Override
    public void close() {
        channel.close();
        master.shutdownGracefully();
        deamon.shutdownGracefully();

    }
}
