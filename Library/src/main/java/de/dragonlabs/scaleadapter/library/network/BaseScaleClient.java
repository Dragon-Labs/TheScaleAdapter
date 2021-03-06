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
import de.dragonlabs.scaleadapter.library.packet.ScalePacket;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.epoll.Epoll;
import io.netty.channel.epoll.EpollEventLoopGroup;
import io.netty.channel.epoll.EpollSocketChannel;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.util.Arrays;

public class BaseScaleClient extends ScaleClient {

    public BaseScaleClient(ScaleConfig config) {
        super(config);
    }

    @Override
    public void sendPackets(ScalePacket... packets) {
        Arrays.stream(packets).forEach(channel::write);
        channel.flush();
    }

    @Override
    public void open() {
        this.deamon = Epoll.isAvailable() ? new EpollEventLoopGroup() : new NioEventLoopGroup();

        Bootstrap bootstrap = new Bootstrap();

        try {
            channel = bootstrap
                    .group(deamon)
                    .channel(Epoll.isAvailable() ? EpollSocketChannel.class : NioSocketChannel.class)
                    .handler(new ScaleChannelInitializer(scaleConfig.getPacketManager(), scaleConfig.getEventManager()))
                    .connect(scaleConfig.getHostname(), scaleConfig.getPort())
                    .sync().channel();
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
    }



}
