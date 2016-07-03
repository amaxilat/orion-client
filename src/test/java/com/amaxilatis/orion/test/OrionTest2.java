package com.amaxilatis.orion.test;

/*-
 * #%L
 * Orion Context Broker Client for Java
 * $Id:$
 * $HeadURL:$
 * %%
 * Copyright (C) 2015 - 2016 amaxilatis
 * %%
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 * #L%
 */

import com.amaxilatis.orion.OrionClient;
import com.amaxilatis.orion.model.subscribe.OrionEntity;
import com.amaxilatis.orion.model.subscribe.SubscriptionResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.TimeZone;

/**
 * Tests for OrionClient.
 */
public class OrionTest2 {
    /**
     * a log4j logger to print messages.
     */
    protected static final Logger LOGGER = Logger.getLogger(OrionTest2.class);
    private static final String ENTITY_URI = "urn:oc:entity:london:environmental:fixed:40318211-196c-11e4-9f59-984fee0004c4";
    private ObjectMapper mapper;
    private OrionClient client;
    private SimpleDateFormat df;

    @Before
    public void setUp() throws Exception {
        BasicConfigurator.configure();
        mapper = new ObjectMapper();
        TimeZone tz = TimeZone.getTimeZone("UTC");
        df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm'Z'");
        df.setTimeZone(tz);
        final String serverUrl = "http://146.169.46.162:1026/";
        client = new OrionClient(serverUrl, "", "organicity", "/");
    }

    @Test
    public void testCreateAndDeleteSubscriptionOnChange() throws IOException {
        OrionEntity e = new OrionEntity();
        e.setId(ENTITY_URI);
        e.setIsPattern("false");
        e.setType("urn:oc:entitytype:iotdevice");
        String[] attr = new String[0];
        String[] cond = new String[1];
        cond[0] = "TimeInstant";
        SubscriptionResponse r = client.subscribeChange(e, null, "http://150.140.5.11:7000/v1/notifyContext", cond, "P1D");
        if (r.getSubscribeResponse() != null) {
            final String subscriptionId = r.getSubscribeResponse().getSubscriptionId();
            LOGGER.info("subscriptionId: " + subscriptionId);
            client.unSubscribeChange(subscriptionId);
        }
    }
}

