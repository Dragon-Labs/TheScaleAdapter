/*
 * Copyright 2018 Dragon-Labs.net
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 */

package de.dragonlabs.scaleadapter.example;

import de.dragonlabs.scaleadapter.example.packets.MessagePacket;
import de.dragonlabs.scaleadapter.library.config.ScaleConfig;
import de.dragonlabs.scaleadapter.library.exceptions.NoMetaExistsException;
import de.dragonlabs.scaleadapter.library.exceptions.PacketIdAlreadyExistsException;
import de.dragonlabs.scaleadapter.library.factory.ScaleNetworkFactory;
import de.dragonlabs.scaleadapter.library.network.ScaleClient;
import de.dragonlabs.scaleadapter.library.network.ScaleServer;
import de.dragonlabs.scaleadapter.library.protocol.EventManager;
import de.dragonlabs.scaleadapter.library.protocol.PacketManager;

import java.util.Scanner;

public class EchoServer
{
    private ScaleClient client;
    private ScaleServer server;

    public static void main(String[] args)
    {
        new EchoServer();
    }

    private EchoServer()
    {
        setupServer();
        setupClient();

        server.open();
        client.open();

        Scanner scanner = new Scanner(System.in);
        while (true)
        {
            String line = scanner.nextLine();

            if(line.equalsIgnoreCase("close"))
            {
                server.close();
                client.close();
                break;
            }

            client.sendPackets(new MessagePacket(line));
        }
    }

    private void setupServer()
    {
        EventManager eventManager = new EventManager();
        eventManager.registerListener(new de.dragonlabs.scaleadapter.example.server.handler.MessageListener());
        PacketManager packetManager = new PacketManager();
        try {
            packetManager.registerPacket(MessagePacket.class);
        } catch (PacketIdAlreadyExistsException | NoMetaExistsException e) {
            e.printStackTrace();
        }

        ScaleConfig config = new ScaleConfig("localhost", 8888, packetManager, eventManager);
        server = ScaleNetworkFactory.createScaleServer(config);
    }

    private void setupClient()
    {
        EventManager eventManager = new EventManager();
        eventManager.registerListener(new de.dragonlabs.scaleadapter.example.client.handler.MessageListener());
        PacketManager packetManager = new PacketManager();
        try {
            packetManager.registerPacket(MessagePacket.class);
        } catch (PacketIdAlreadyExistsException | NoMetaExistsException e) {
            e.printStackTrace();
        }

        ScaleConfig config = new ScaleConfig("localhost", 8888, packetManager, eventManager);
        client = ScaleNetworkFactory.createScaleClient(config);
    }
}
