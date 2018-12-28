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
import io.netty.buffer.ByteBufOutputStream;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

public class ScaleNetworkEndcoder  extends MessageToByteEncoder<ScalePacket>
{
    private PacketManager packetManager;

    public ScaleNetworkEndcoder(PacketManager packetManager){
        this.packetManager = packetManager;
    }

    @Override
    protected void encode(ChannelHandlerContext ctx, ScalePacket packet, ByteBuf out) {

        Byte id = packetManager.getIdByClass(packet.getClass());

        if(id == null)
        {
            throw new NullPointerException("Packet not found (" + packet.getClass() + ")");
        }

        try {
            out.writeByte(id);
            packet.onWrite(new ByteBufOutputStream(out));
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
