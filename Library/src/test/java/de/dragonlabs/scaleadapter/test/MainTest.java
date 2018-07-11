/*
 * Copyright 2018 Dragon-Labs.net
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 */

package de.dragonlabs.scaleadapter.test;

import de.dragonlabs.scaleadapter.library.config.ScaleConfig;
import de.dragonlabs.scaleadapter.library.factory.ScaleNetworkFactory;
import de.dragonlabs.scaleadapter.library.network.ScaleClient;
import de.dragonlabs.scaleadapter.library.network.ScaleServer;
import de.dragonlabs.scaleadapter.library.protocol.EventManager;
import de.dragonlabs.scaleadapter.library.protocol.PacketManager;
import org.junit.Assert;
import org.junit.Test;

public class MainTest
{
    @Test
    public void test()
    {
        PacketManager packetManager = new PacketManager();
        EventManager eventManager = new EventManager();

        ScaleConfig config =  new ScaleConfig("localhost", 29546, packetManager, eventManager);

        ScaleServer server = ScaleNetworkFactory.createScaleServer(config);
        ScaleClient client = ScaleNetworkFactory.createScaleClient(config);

        server.open();
        client.open();

        Assert.assertEquals(server.isOpen(), true);
        Assert.assertEquals(client.isOpen(), true);
        System.out.println("Test connection success");

        client.close();
        server.close();
        System.out.println("Test success");
    }
}
