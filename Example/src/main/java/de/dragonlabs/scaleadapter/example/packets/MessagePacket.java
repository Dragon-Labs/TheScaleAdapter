/*
 * Copyright 2018 Dragon-Labs.net
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 */

package de.dragonlabs.scaleadapter.example.packets;

import de.dragonlabs.scaleadapter.library.packet.ScalePacket;
import de.dragonlabs.scaleadapter.library.packet.ScalePacketMeta;
import io.netty.buffer.ByteBufInputStream;
import io.netty.buffer.ByteBufOutputStream;

import java.io.IOException;

@ScalePacketMeta(id=1)
public class MessagePacket extends ScalePacket {

    //The message as data
    private String message;

    // Every packet need an empty constructor
    public MessagePacket() {}

    public MessagePacket(String message) {
        this.message = message;
    }

    @Override
    public void onRead(ByteBufInputStream input) throws IOException {
        //Read the data from an incoming ByteBuf
        message = input.readUTF();
    }

    @Override
    public void onWrite(ByteBufOutputStream output) throws IOException {
        //Write the message to an outgoing ByteBuf
        output.writeUTF(message);
    }

    //Getter for the message
    public String getMessage() {
        return message;
    }
}
