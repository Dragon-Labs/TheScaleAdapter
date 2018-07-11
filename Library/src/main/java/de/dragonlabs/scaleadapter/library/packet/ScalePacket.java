/*
 * Copyright 2018 Dragon-Labs.net
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 */

package de.dragonlabs.scaleadapter.library.packet;

import de.dragonlabs.scaleadapter.library.network.handler.ScaleConnectionHandler;
import io.netty.buffer.ByteBufInputStream;
import io.netty.buffer.ByteBufOutputStream;

import java.io.IOException;

public abstract class ScalePacket {

    /**
     * The ConnectionHandler for every single Connection
     */
    private ScaleConnectionHandler handler;

    /**
     * This method is be used when a message come and need to be converted back to the back from the ByteBuf
     *
     * @param input The ByteBugInputStream to read the stream
     */
    abstract public void onRead(ByteBufInputStream input) throws IOException;

    /**
     * This method is be used when a message come and need to be send  and need to convert to a ByteBuf
     *
     * @param output The ByteBufOutputStream to write data in the stream
     */
    abstract public void onWrite(ByteBufOutputStream output) throws IOException;

    /**
     * @return Return the Connection Handler which handles the Packet
     */
    public ScaleConnectionHandler getHandler() {
        return handler;
    }

    /**
     * @param handler Set the Connection Handler which handles the Packet
     */
    public void setHandler(ScaleConnectionHandler handler) {
        this.handler = handler;
    }
}
