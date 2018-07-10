/*
 * Copyright 2018 Dragon-Labs.net
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 */

package de.dragonlabs.scaleadapter.library.packet;

import io.netty.buffer.ByteBufInputStream;
import io.netty.buffer.ByteBufOutputStream;
import io.netty.channel.Channel;

import java.io.IOException;

public abstract class ScalePacket {

    /**
     * The channel from where the Packet come
     */
    private Channel channel;

    /**
     * This method is be used when a message come and need to be converted back to the back from the ByteBuf
     *
     * @param input The ByteBugInputStream to read the stream
     */
    abstract public void onRead(ByteBufInputStream input);

    /**
     * This method is be used when a message come and need to be send  and need to convert to a ByteBuf
     *
     * @param output The ByteBufOutputStream to write data in the stream
     */
    abstract public void onWrite(ByteBufOutputStream output);

    /**
     * @return Return the Channel fromm where the Packet come
     */
    public Channel getChannel() {
        return channel;
    }

    /**
     * @param channel Set the Channel from where the Packet come
     */
    public void setChannel(Channel channel) {
        this.channel = channel;
    }
}
