/*
 * Copyright 2018 Dragon-Labs.net
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 */

package de.dragonlabs.scaleadapter.library.coder;

import de.dragonlabs.scaleadapter.library.packet.ScalePacket;
import de.dragonlabs.scaleadapter.library.protocol.PacketManager;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufInputStream;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

public class ScaleNetworkDecoder extends ByteToMessageDecoder
{
    private final PacketManager packetManager;

    public ScaleNetworkDecoder(PacketManager packetManager)
    {
        this.packetManager = packetManager;
    }

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf byteBuf, List<Object> list) throws Exception
    {
        Byte id = byteBuf.readByte();

        ScalePacket packet = packetManager.getPacketById(id);

        if (packet == null) {
            throw new IllegalArgumentException("Don't find a Packet with the ID " + id);
        }

        try {
            packet.setChannel(ctx.channel());
            packet.onRead(new ByteBufInputStream(byteBuf));
            list.add(packet);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

    }
}
