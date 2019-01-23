/*
 * Copyright 2018 Dragon-Labs.net
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 */

package de.dragonlabs.scaleadapter.library.factory;

import de.dragonlabs.scaleadapter.library.config.ScaleConfig;
import de.dragonlabs.scaleadapter.library.network.BaseScaleClient;
import de.dragonlabs.scaleadapter.library.network.BaseScaleServer;
import de.dragonlabs.scaleadapter.library.network.ScaleClient;
import de.dragonlabs.scaleadapter.library.network.ScaleServer;

public class ScaleNetworkFactory
{
    /**
     *  Create a new Instance for a Server
     *
     * @param config network and event settings
     * @return the new server instance
     */
    public static ScaleServer createScaleServer(ScaleConfig config)
    {
        return new BaseScaleServer(config);
    }

    /**
     *  Create a new Instance for a Client
     *
     * @param config network and event settings
     * @return the new client instance
     */
    public static ScaleClient createScaleClient(ScaleConfig config)
    {
        return new BaseScaleClient(config);
    }
}
