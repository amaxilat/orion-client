package com.amaxilatis.orion.test;

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

