package com.sparkworks.orion.test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sparkworks.orion.OrionClient;
import com.sparkworks.orion.model.OrionContextElement;
import com.sparkworks.orion.model.OrionContextElementWrapper;
import com.sparkworks.orion.util.SensorMLTypes;
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

        mapper = new ObjectMapper();

        TimeZone tz = TimeZone.getTimeZone("UTC");
        df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm'Z'");
        df.setTimeZone(tz);


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
        element.getAttributes().add(OrionClient.createAttributeWithCode("atmosphericPressure", SensorMLTypes.ATMOSPHERIC_PRESSURE, "1096.73", df.format(new Date())));

        final String objectString = mapper.writeValueAsString(element);
        LOGGER.info(objectString);

        final String entity = client.postContextEntity(ENTITY_URI, objectString);
        LOGGER.info(entity);
    }

    @Test
    public void testGetElement() throws IOException {
        final OrionContextElementWrapper entity = client.getContextEntity(ENTITY_URI);
        LOGGER.info(entity);
    }
}

