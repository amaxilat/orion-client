package com.amaxilatis.orion.test;

import com.amaxilatis.orion.OrionClient;
import com.amaxilatis.orion.model.OrionContextElement;
import com.amaxilatis.orion.model.subscribe.OrionEntity;
import com.amaxilatis.orion.model.subscribe.SubscriptionResponse;
import com.amaxilatis.orion.util.SensorMLTypes;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
    public void testCreateSubscriptionOnChange() throws IOException {
        OrionEntity e = new OrionEntity();
        e.setId(ENTITY_URI);
        e.setIsPattern("false");
        e.setType("urn:oc:entitytype:iotdevice");
        String[] attr = new String[0];
        String[] cond = new String[1];
        cond[0] = "TimeInstant";
        SubscriptionResponse r = client.subscribeChange(e, null, "http://54.68.181.32:1026/v1/notifyContext", cond);
        LOGGER.info(r.getSubscribeResponse().toString());
        LOGGER.info("---");
    }


}

