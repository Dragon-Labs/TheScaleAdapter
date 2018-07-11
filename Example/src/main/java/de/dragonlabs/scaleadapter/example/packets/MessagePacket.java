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

    private String message;

    public MessagePacket() {}

    public MessagePacket(String message) {
        this.message = message;
    }

    @Override
    public void onRead(ByteBufInputStream input) throws IOException {
        message = input.readUTF();
    }

    @Override
    public void onWrite(ByteBufOutputStream output) throws IOException {
        output.writeUTF(message);
    }

    public String getMessage() {
        return message;
    }
}
