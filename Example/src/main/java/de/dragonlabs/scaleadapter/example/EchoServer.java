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

        //The Server is starting and then the client connect to the server
        server.open();
        client.open();

        //Simple command line
        Scanner scanner = new Scanner(System.in);
        while (true)
        {
            //Read the input
            String line = scanner.nextLine();

            //If the user write "close" the server and the client close the connection and shutdown normally
            if(line.equalsIgnoreCase("close"))
            {
                server.close();
                client.close();
                break;
            }

            // The message send to the server
            client.sendPackets(new MessagePacket(line));
        }
    }

    private void setupServer()
    {
        //Create own Packet and EventManager for the server
        EventManager eventManager = new EventManager();
        PacketManager packetManager = new PacketManager();

        //Register the Server Listener
        eventManager.registerListener(new de.dragonlabs.scaleadapter.example.server.handler.MessageListener());
        eventManager.registerListener(new de.dragonlabs.scaleadapter.example.server.handler.ConnectionListener());
        try {
            //Register the Message Packet
            packetManager.registerPacket(MessagePacket.class);
        } catch (PacketIdAlreadyExistsException | NoMetaExistsException e) {
            e.printStackTrace();
        }

        //Create the Config with Packet and EventManager and the Server information
        ScaleConfig config = new ScaleConfig("localhost", 8888, packetManager, eventManager);
        //Create an server with the data from the config
        server = ScaleNetworkFactory.createScaleServer(config);
    }

    private void setupClient()
    {
        //Create own Packet and EventManager for the client
        EventManager eventManager = new EventManager();
        PacketManager packetManager = new PacketManager();

        //Register the Client Listener
        eventManager.registerListener(new de.dragonlabs.scaleadapter.example.client.handler.MessageListener());
        try {
            //Register the Message Packet
            packetManager.registerPacket(MessagePacket.class);
        } catch (PacketIdAlreadyExistsException | NoMetaExistsException e) {
            e.printStackTrace();
        }

        //Create the Config with Packet and EventManager and the Client information
        ScaleConfig config = new ScaleConfig("localhost", 8888, packetManager, eventManager);
        //Create an client with the data from the config
        client = ScaleNetworkFactory.createScaleClient(config);
    }
}
