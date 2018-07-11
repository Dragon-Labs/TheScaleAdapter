/*
 * Copyright 2018 Dragon-Labs.net
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 */

package de.dragonlabs.scaleadapter.example.server.handler;

import de.dragonlabs.scaleadapter.example.packets.MessagePacket;
import de.dragonlabs.scaleadapter.library.event.Listener;
import de.dragonlabs.scaleadapter.library.event.ScalePacketHandler;

public class MessageListener implements Listener
{
    @ScalePacketHandler
    public void onMessage(MessagePacket packet)
    {
        packet.getHandler().sendPackets(packet);
    }
}
