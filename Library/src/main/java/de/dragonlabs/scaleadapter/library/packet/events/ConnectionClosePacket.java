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
 * This Packet will be called when a connection is closed
 */
@ScalePacketMeta(id = -11)
public class ConnectionClosePacket extends ScalePacket {

    private Channel channel;

    public ConnectionClosePacket(Channel channel) {
        this.channel = channel;
    }

    @Override
    public void onRead(ByteBufInputStream input) {

    }

    @Override
    public void onWrite(ByteBufOutputStream output) {

    }

    public Channel getChannel() {
        return channel;
    }
}
