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
import com.amaxilatis.orion.model.ContextElementList;
import com.amaxilatis.orion.model.OrionContextElement;
import com.amaxilatis.orion.model.OrionContextElementWrapper;
import com.amaxilatis.orion.util.SensorMLTypes;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.TimeZone;

/**
 * Tests for OrionClient.
 */
public class OrionTest {
    /**
     * a log4j logger to print messages.
     */
    protected static final Logger LOGGER = Logger.getLogger(OrionTest.class);
    private static final String ENTITY_URI = "urn:x-iot:patras:u7jcfa:fixed:t3224";

    private OrionClient client;
    private ObjectMapper mapper;
    private SimpleDateFormat df;

    @Before
    public void setUp() throws Exception {
        BasicConfigurator.configure();

        TimeZone tz = TimeZone.getTimeZone("UTC");
        df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm'Z'");
        df.setTimeZone(tz);

        mapper = new ObjectMapper();

        final Properties properties = new Properties();
        properties.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("connection.properties"));

        final String serverUrl = properties.getProperty("serverUrl");
        final String token = properties.getProperty("token");

        client = new OrionClient(serverUrl, token);
    }

    @Test
    public void testPostElement() throws IOException {

        final OrionContextElement element = new OrionContextElement();

        element.setId(ENTITY_URI);
        element.setType("patras:env_station");
        element.getAttributes().add(OrionClient.createAttribute("TimeInstant", SensorMLTypes.ISO8601_TIME, df.format(new Date())));
        element.getAttributes().add(OrionClient.createAttribute("Latitud", SensorMLTypes.LATITUDE, String.valueOf(-3.79906)));
        element.getAttributes().add(OrionClient.createAttribute("Longitud", SensorMLTypes.LONGITUDE, String.valueOf(43.79906)));
        element.getAttributes().add(OrionClient.createAttributeWithTimeInstant("temperature", SensorMLTypes.TEMPERATURE, "32", new Date()));
        element.getAttributes().add(OrionClient.createAttributeWithCode("atmosphericPressure", SensorMLTypes.ATMOSPHERIC_PRESSURE, "1096.73", "bar"));

        LOGGER.info(element);

        final String entity = client.postContextEntity(ENTITY_URI, element);
        LOGGER.info(entity);
    }

    @Test
    public void testDeleteElementsById() throws IOException {
        final ContextElementList elementList = client.listContextEntities();
        for (final OrionContextElementWrapper orionContextElementWrapper : elementList.getContextResponses()) {
            final OrionContextElement element = orionContextElementWrapper.getContextElement();
            if (element.getId().contains("london")) {
                LOGGER.info(element.getId());
                client.deleteContextEntity(element.getId());
            }
        }
    }
}

