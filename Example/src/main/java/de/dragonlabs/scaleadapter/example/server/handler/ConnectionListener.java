/*
 * Copyright 2018 Dragon-Labs.net
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 */

package de.dragonlabs.scaleadapter.example.server.handler;

import de.dragonlabs.scaleadapter.library.event.Listener;
import de.dragonlabs.scaleadapter.library.event.ScalePacketHandler;
import de.dragonlabs.scaleadapter.library.packet.events.ConnectionClosePacket;
import de.dragonlabs.scaleadapter.library.packet.events.ConnectionOpenPacket;
import de.dragonlabs.scaleadapter.library.packet.events.NetworkErrorPacket;
import de.dragonlabs.scaleadapter.library.packet.events.PacketIncomePacket;

public class ConnectionListener implements Listener
{
    @ScalePacketHandler
    public void onNewConnection(ConnectionOpenPacket packet)
    {
        System.out.println("New Connection");
    }

    @ScalePacketHandler
    public void onCloseConnection(ConnectionClosePacket packet)
    {
        System.out.println("Close Connection");
    }

    @ScalePacketHandler
    public void onNewPacket(PacketIncomePacket packet)
    {
        System.out.println("Packet Income from Client: " + packet.getPacketName());
    }

    @ScalePacketHandler
    public void onNetworkError(NetworkErrorPacket packet)
    {
        packet.getError().printStackTrace();
    }
}
