/*
 * Copyright 2018 Dragon-Labs.net
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 */

package de.dragonlabs.scaleadapter.library.network;

import de.dragonlabs.scaleadapter.library.config.ScaleConfig;
import de.dragonlabs.scaleadapter.library.network.channel.ScaleChannelInitializer;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelOption;
import io.netty.channel.epoll.Epoll;
import io.netty.channel.epoll.EpollEventLoopGroup;
import io.netty.channel.epoll.EpollServerSocketChannel;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class BaseScaleServer extends ScaleServer {

    public BaseScaleServer(ScaleConfig config) {
        super(config);
    }

    @Override
    public void open() {
        this.master = Epoll.isAvailable() ? new EpollEventLoopGroup() : new NioEventLoopGroup();
        this.deamon = Epoll.isAvailable() ? new EpollEventLoopGroup() : new NioEventLoopGroup();

        ServerBootstrap serverBootstrap = new ServerBootstrap();

        try {
            channel = serverBootstrap
                    .group(master, deamon)
                    .channel(Epoll.isAvailable() ? EpollServerSocketChannel.class : NioServerSocketChannel.class)
                    .childHandler(new ScaleChannelInitializer(scaleConfig.getPacketManager(), scaleConfig.getEventManager()))
                    .childOption(ChannelOption.SO_KEEPALIVE, true)
                    .option(ChannelOption.TCP_NODELAY, true)
                    .option(ChannelOption.SO_BACKLOG, 50)
                    .bind(this.scaleConfig.getHostname(), this.scaleConfig.getPort())
                    .sync().channel();
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }
}
