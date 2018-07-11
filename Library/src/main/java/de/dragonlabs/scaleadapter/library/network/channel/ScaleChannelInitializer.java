/*
 * Copyright 2018 Dragon-Labs.net
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 */

package de.dragonlabs.scaleadapter.library.network.channel;

import de.dragonlabs.scaleadapter.library.coder.ScaleNetworkDecoder;
import de.dragonlabs.scaleadapter.library.coder.ScaleNetworkEndcoder;
import de.dragonlabs.scaleadapter.library.network.handler.ScaleConnectionHandler;
import de.dragonlabs.scaleadapter.library.protocol.EventManager;
import de.dragonlabs.scaleadapter.library.protocol.PacketManager;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;

public class ScaleChannelInitializer extends ChannelInitializer<SocketChannel> {

    private PacketManager packetManager;
    private EventManager eventManager;

    public ScaleChannelInitializer(PacketManager packetManager, EventManager eventManager) {
        this.packetManager = packetManager;
        this.eventManager = eventManager;
    }

    @Override
    protected void initChannel(SocketChannel socketChannel) {
        ChannelPipeline channelPipeline = socketChannel.pipeline();

        channelPipeline.addLast(new LengthFieldBasedFrameDecoder(Short.MAX_VALUE, 0, 4, 0, 4));
        channelPipeline.addLast(new ScaleNetworkDecoder(packetManager));
        channelPipeline.addLast(new LengthFieldPrepender(4));
        channelPipeline.addLast(new ScaleNetworkEndcoder(packetManager));
        channelPipeline.addLast(new ScaleConnectionHandler(eventManager, socketChannel));
    }
}
