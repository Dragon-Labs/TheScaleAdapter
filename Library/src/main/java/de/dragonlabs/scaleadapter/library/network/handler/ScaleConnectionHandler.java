/*
 * Copyright 2018 Dragon-Labs.net
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 */

package de.dragonlabs.scaleadapter.library.network.handler;

import de.dragonlabs.scaleadapter.library.packet.ScalePacket;
import de.dragonlabs.scaleadapter.library.packet.events.ConnectionClosePacket;
import de.dragonlabs.scaleadapter.library.packet.events.ConnectionOpenPacket;
import de.dragonlabs.scaleadapter.library.packet.events.NetworkErrorPacket;
import de.dragonlabs.scaleadapter.library.packet.events.PacketIncomePacket;
import de.dragonlabs.scaleadapter.library.protocol.EventManager;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.io.IOException;
import java.util.Arrays;

public class ScaleConnectionHandler extends SimpleChannelInboundHandler<ScalePacket> {

    private EventManager eventManager;
    private Channel channel;

    public ScaleConnectionHandler(EventManager eventManager, Channel channel)
    {
        this.eventManager = eventManager;
        this.channel = channel;
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        ConnectionOpenPacket packet = new ConnectionOpenPacket();
        packet.setChannel(channel);
        eventManager.call(packet);
        if(packet.getCloseConnection()) ctx.close();
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) {
        ConnectionClosePacket packet = new ConnectionClosePacket();
        packet.setChannel(channel);
        eventManager.call(packet);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        if(cause.getClass() == IOException.class) {
            return;
        }

        NetworkErrorPacket packet = new NetworkErrorPacket(cause);
        packet.setChannel(channel);
        eventManager.call(packet);
    }

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, ScalePacket scalePacket) {

        PacketIncomePacket packet = new PacketIncomePacket(scalePacket.getClass().getSimpleName());
        packet.setChannel(channel);
        eventManager.call(packet);

        if(packet.getCancelPacket()) return;

        scalePacket.setHandler(this);
        eventManager.call(scalePacket);
    }

    public void sendPackets(ScalePacket... packet)
    {
        Arrays.stream(packet).forEach(channel::write);
        channel.flush();
    }
}
