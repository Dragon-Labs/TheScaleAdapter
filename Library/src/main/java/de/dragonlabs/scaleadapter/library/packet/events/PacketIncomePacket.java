/*
 * Copyright 2018 Dragon-Labs.net
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 */

package de.dragonlabs.scaleadapter.library.packet.events;

import de.dragonlabs.scaleadapter.library.packet.ScalePacket;
import de.dragonlabs.scaleadapter.library.packet.ScalePacketMeta;
import io.netty.buffer.ByteBufInputStream;
import io.netty.buffer.ByteBufOutputStream;
import io.netty.channel.Channel;

/**
 * This packet will be thrown when another packet come from the network and you can decide if the packet will be called or canceled.
 */
@ScalePacketMeta(id = -12)
public class PacketIncomePacket extends ScalePacket {

    private Channel channel;
    private String packetName;
    private Boolean cancelPacket;

    public PacketIncomePacket(Channel channel, String packetName)
    {
        this.channel = channel;
        this.packetName = packetName;
        this.cancelPacket = false;
    }

    @Override
    public void onRead(ByteBufInputStream input)  {

    }

    @Override
    public void onWrite(ByteBufOutputStream output) {

    }

    public Channel getChannel() {
        return channel;
    }

    public String getPacketName() {
        return packetName;
    }

    public Boolean getCancelPacket() {
        return cancelPacket;
    }

    public void setCancelPacket(Boolean cancelPacket) {
        this.cancelPacket = cancelPacket;
    }
}
