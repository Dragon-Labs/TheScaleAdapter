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
 * This packet will be called when a error is thrown in the network stuff
 */
@ScalePacketMeta(id = -13)
public class NetworkErrorPacket extends ScalePacket {

    private Channel channel;
    private Throwable error;

    public NetworkErrorPacket(Channel channel, Throwable error) {
        this.channel = channel;
        this.error = error;
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

    public Throwable getError() {
        return error;
    }
}
