package com.amaxilatis.orion;

import com.amaxilatis.orion.model.*;
import com.amaxilatis.orion.model.subscribe.NotifyConditions;
import com.amaxilatis.orion.model.subscribe.OrionEntity;
import com.amaxilatis.orion.model.subscribe.SubscribeContextAvailabilityRequest;
import com.amaxilatis.orion.model.subscribe.SubscriptionResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;

import javax.ws.rs.client.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * A client class to interact with an Orion Context Broker.
 *
 * @author Dimitrios Amaxilatis.
 */
public class OrionClient {
    /**
     * a log4j logger to print messages.
     */
    protected static final Logger LOGGER = Logger.getLogger(OrionClient.class);
    private static final SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");

    private final String token;
    private String serverUrl;
    private String service;
    private String servicePath;

    public static Map<String, Object> createAttribute(String name, String type, String value) {
        Map<String, Object> attribute = new HashMap<>();
        attribute.put("name", name);
        attribute.put("type", type);
        attribute.put("value", value);
        return attribute;
    }

    /**
     * Create an attribute with an ID metadata.
     *
     * @param name  the name of the attribute.
     * @param type  the type of the attribute.
     * @param value the value of the attribute.
     * @param id    the id metadata.
     * @return an Orion Attribute Map.
     */
    public static Map<String, Object> createAttributeWithMetadata(String name, String type, String value, final String id) {
        return createAttributeWithMetadata(name, type, value, "ID", "string", id);
    }

    /**
     * Create an attribute with a TimeInstant metadata.
     *
     * @param name  the name of the attribute.
     * @param type  the type of the attribute.
     * @param value the value of the attribute.
     * @param date  the time instant to be added.
     * @return an Orion Attribute Map.
     */
    public static Map<String, Object> createAttributeWithTimeInstant(String name, String type, String value, final Date date) {
        return createAttributeWithMetadata(name, type, value, "TimeInstant", "ISO8601", df.format(date));
    }

    /**
     * Create an attribute with a Code metadata.
     *
     * @param name  the name of the attribute.
     * @param type  the type of the attribute.
     * @param value the value of the attribute.
     * @param code  the unit of measurement to be added.
     * @return an Orion Attribute Map.
     */
    public static Map<String, Object> createAttributeWithCode(String name, String type, String value, final String code) {
        return createAttributeWithMetadata(name, type, value, "code", "", code);
    }

    public static Map<String, Object> createAttributeWithMetadata(
            final String name, final String type, final String value,
            List<Map<String, String>> metadatas) {
        final Map<String, Object> attribute = new HashMap<>();
        attribute.put("name", name);
        attribute.put("type", type);
        attribute.put("value", value);

        attribute.put("metadatas", metadatas);
        return attribute;
    }

    public static Map<String, Object> createAttributeWithMetadata(
            final String name, final String type, final String value,
            final String metadataName, final String metadataType, final String metadataValue) {
        final Map<String, Object> attribute = new HashMap<>();
        attribute.put("name", name);
        attribute.put("type", type);
        attribute.put("value", value);

        final List<Map<String, String>> metadatas = new ArrayList<>();
        metadatas.add(createMetadata(metadataName, metadataType, metadataValue));
        attribute.put("metadatas", metadatas);
        return attribute;
    }

    public static Map<String, String> createMetadata(String name, String type, String value) {
        Map<String, String> metadata = new HashMap<>();
        metadata.put("name", name);
        metadata.put("type", type);
        metadata.put("value", value);
        return metadata;
    }


    /**
     * Creates a new OrionClient.
     *
     * @param token the token to be used for authorization.
     */
    public OrionClient(final String token) {
        this.token = token;
        this.serverUrl = "http://orion.lab.fi-ware.org:1026/";
        this.service = null;
        this.servicePath = null;

        df.setTimeZone(TimeZone.getTimeZone("UTC"));

    }

    /**
     * Creates a new OrionClient.
     *
     * @param serverUrl the url of the server to interact with.
     * @param token     the token to be used for authorization.
     */
    public OrionClient(final String serverUrl, final String token) {
        this.token = token;
        this.serverUrl = serverUrl;

        df.setTimeZone(TimeZone.getTimeZone("UTC"));

    }

    /**
     * Creates a new OrionClient.
     *
     * @param serverUrl the url of the server to interact with.
     * @param token     the token to be used for authorization.
     * @param service   the service to add as header.
     */
    public OrionClient(final String serverUrl, final String token, final String service) {
        this.token = token;
        this.serverUrl = serverUrl;
        this.service = service;
        this.servicePath = null;

        df.setTimeZone(TimeZone.getTimeZone("UTC"));

    }

    /**
     * Creates a new OrionClient.
     *
     * @param serverUrl   the url of the seerver to interact with.
     * @param token       the token to be used for authorization.
     * @param service     the service to add as header.
     * @param servicePath the servicePath to add as header.
     */
    public OrionClient(final String serverUrl, final String token, final String service, final String servicePath) {
        this.token = token;
        this.serverUrl = serverUrl;
        this.service = service;
        this.servicePath = servicePath;

        df.setTimeZone(TimeZone.getTimeZone("UTC"));

    }


    /**
     * Execute a get request to the specified uri.
     *
     * @param uri the uri of the resource.
     * @return the response string from the server.
     */
    public final OrionContextElementWrapper getContextEntity(final String uri) throws IOException {
        final String resp = getPath("/v1/contextEntities/" + uri.replaceAll("/", ":"));
        return new ObjectMapper().readValue(resp, OrionContextElementWrapper.class);
    }

    /**
     * Execute a post request to the specified uri.
     *
     * @param uri    the uri of the resource.
     * @param entity the text containing the entity to store.
     * @return the response string from the server.
     */
    private final String postContextEntity(final String uri, final String entity) {
        return postPath("/v1/contextEntities/" + uri.replaceAll("/", ":"), entity);
    }

    /**
     * Execute a post request to the specified uri.
     *
     * @param uri    the uri of the resource.
     * @param entity the text containing the entity to store.
     * @return the response string from the server.
     */
    public final String postContextEntity(final String uri, final Object entity) throws IOException {
        return postPath("/v1/contextEntities/" + uri.replaceAll("/", ":"), new ObjectMapper().writeValueAsString(entity));
    }

    /**
     * Execute a delete request to the specified uri.
     *
     * @param uri    the uri of the resource.
     * @param entity the text containing the entity to store.
     * @return the response string from the server.
     */
    public String deleteFromContextEntity(String uri, OrionContextElement entity) throws JsonProcessingException {
        OrionContextElement element1 = new OrionContextElement();
        element1.setType(entity.getType());
        element1.setIsPattern("false");
        element1.setId(entity.getId());

        OrionContextElementOperation operation = new OrionContextElementOperation();
        operation.getContextElements().add(element1);
        operation.setUpdateAction("DELETE");
        return postPath("/v1/updateContext", new ObjectMapper().writeValueAsString(operation));
    }

    /**
     * Execute a delete request to the specified uri.
     *
     * @param uri       the uri of the resource.
     * @param attribute
     * @param id
     * @return the response string from the server.
     */
    public String deleteAttributeFromContextEntity(String uri, String attribute, String id) throws JsonProcessingException {
        return deletePath("/v1/contextEntities/" + uri + "/attributes/" + attribute + "/" + id);
    }

    /**
     * Execute a delete request to the specified uri.
     *
     * @param entity the text containing the entity to store.
     * @return the response string from the server.
     */
    public String updateFromContextEntity(OrionContextElement entity) throws JsonProcessingException {
        OrionContextElementOperation operation = new OrionContextElementOperation();
        operation.getContextElements().add(entity);
        operation.setUpdateAction("APPEND");
        return postPath("/v1/updateContext", new ObjectMapper().writeValueAsString(operation));
    }

    public SubscriptionResponse subscribeChange(final OrionEntity entity, final String attribute, final String reference) throws IOException {
        final SubscribeContextAvailabilityRequest request = new SubscribeContextAvailabilityRequest();
        request.getEntities().add(entity);
        request.getAttributes().add(attribute);
        request.setReference(reference);
        request.getNotifyConditions().add(new NotifyConditions("ONCHANGE", attribute));
        final String resp = getPathSubscription("/v1/subscribeContext", request);
        LOGGER.trace(resp);
        return new ObjectMapper().readValue(resp, SubscriptionResponse.class);
    }


 /*   {
        "entities": [
        {
            "type": �urn:oc:entityType:iotdevice�,
            "isPattern": �false�,
            "id": "urn:oc:entity:santander:environmental:fixed:667�
        }
        ],
        "attributes": [ ],
        "reference": "http://localhost:5050/notify",
            "duration": "P20Y",
            "notifyConditions": [
        {
            "type": "ONCHANGE",
                "condValues": [
            "TimeInstant"
            ]
        }
        ],
        "throttling": "PT1S"
    }*/

    public SubscriptionResponse subscribeChange(final OrionEntity entity, final String[] attributes, final String reference, final String[] conditions) throws IOException {
        final SubscribeContextAvailabilityRequest request = new SubscribeContextAvailabilityRequest();
        request.getEntities().add(entity);
        if (attributes==null ||attributes.length ==0 )
            request.setAttributes(null);
        else
        request.getAttributes().addAll(Arrays.<String>asList(attributes));
        request.setReference(reference);
        for (String cond : conditions)
            request.getNotifyConditions().add(new NotifyConditions("ONCHANGE", cond));
        LOGGER.debug(request.toString());
        LOGGER.info(new ObjectMapper().writeValueAsString(request));
        final String resp = getPathSubscription("/v1/subscribeContext", request);
        LOGGER.debug(resp);
        return new ObjectMapper().readValue(resp, SubscriptionResponse.class);
     }

    public ContextElementList listContextEntities() throws IOException {
        return listContextEntities(0);
    }

    public ContextElementList listContextEntities(final long offset) throws IOException {
        final String response = getPath("/v1/contextEntities", offset);
        LOGGER.trace(response);
        return new ObjectMapper().readValue(response, ContextElementList.class);
    }

    public String getContextEntityRegistry(final String uri) throws IOException {
        final String response = getPath("/v1/registry/contextEntities/" + uri);
        LOGGER.trace(response);
        return response;

    }

    public String discoverContextAvailability(String type, String isPattern, String id) throws IOException {
        DiscoverContextAvailabilityRequest request = new DiscoverContextAvailabilityRequest();
        OrionQueryElement element = new OrionQueryElement();
        element.setType(type);
        element.setIsPattern(isPattern);
        element.setId(id);
        request.getEntities().add(element);
        final String response = postPath("/v1/registry/discoverContextAvailability/", new ObjectMapper().writeValueAsString(request));
        LOGGER.trace(response);
        return response;
    }

    public String queryContext(String type, String isPattern, String id) throws IOException {
        DiscoverContextAvailabilityRequest request = new DiscoverContextAvailabilityRequest();
        OrionQueryElement element = new OrionQueryElement();
        element.setType(type);
        element.setIsPattern(isPattern);
        element.setId(id);
        request.getEntities().add(element);
        final String response = postPath("/v1/queryContext/", new ObjectMapper().writeValueAsString(request));
        LOGGER.trace(response);
        return response;
    }

    /**
     * Execute a get request to the specified path.
     *
     * @param path the path to request.
     * @return the response string from the server.
     */
    private String getPathSubscription(final String path, final SubscribeContextAvailabilityRequest request) throws IOException {
        final String requestString = new ObjectMapper().writeValueAsString(request);
        final Entity payload = Entity.json(requestString);
        LOGGER.debug(requestString);
        LOGGER.debug(payload);
        final Response response = getClientForPath(path).post(payload);

        LOGGER.debug("status: " + response.getStatus());
        LOGGER.debug("headers: " + response.getHeaders());
        final String responseString = response.readEntity(String.class);
        LOGGER.trace(responseString);
        return responseString;
    }

    /**
     * Execute a get request to the specified path.
     *
     * @param path the path to request.
     * @return the response string from the server.
     */
    private String getPath(final String path) {
        return getPath(path, 0);
    }

    /**
     * Execute a get request to the specified path.
     *
     * @param path   the path to request.
     * @param offset the offset for a list response.
     * @return the response string from the server.
     */
    private String getPath(final String path, final long offset) {
        final Response response = getClientForPath(path, offset).get();

        LOGGER.debug("status: " + response.getStatus());
        LOGGER.debug("headers: " + response.getHeaders());
        final String responseString = response.readEntity(String.class);
        LOGGER.trace(responseString);
        return responseString;
    }

    /**
     * Execute a post request to the specified path.
     *
     * @param path   the path to request.
     * @param entity the text containing the entity to store.
     * @return the response string from the server.
     */
    private String postPath(final String path, final String entity) {
        LOGGER.debug(entity);
        final Entity payload = Entity.json(entity);
        LOGGER.debug(payload);
        final Response response = getClientForPath(path).post(payload);
        LOGGER.debug("status: " + response.getStatus());
        LOGGER.debug("headers: " + response.getHeaders());
        final String responseString = response.readEntity(String.class);
        LOGGER.trace(responseString);
        return responseString;
    }

    /**
     * Execute a post request to the specified path.
     *
     * @param path the path to request.
     * @return the response string from the server.
     */
    private String deletePath(final String path) {
        final Response response = getClientForPath(path).delete();
        LOGGER.debug("status: " + response.getStatus());
        LOGGER.debug("headers: " + response.getHeaders());
        final String responseString = response.readEntity(String.class);
        LOGGER.trace(responseString);
        return responseString;
    }

    /**
     * Get a client to the orion context broker for the selected path.
     *
     * @param path the path to request.
     * @return a client to execute an http request.
     */
    private Invocation.Builder getClientForPath(final String path) {
        return getClientForPath(path, 0);
    }

    /**
     * Get a client to the orion context broker for the selected path.
     *
     * @param path   the path to request.
     * @param offset the offset for a list response.
     * @return a client to execute an http request.
     */
    private Invocation.Builder getClientForPath(final String path, final long offset) {
        Client client = ClientBuilder.newClient();

        LOGGER.debug("path: " + path);

        WebTarget webTarget = client.target(serverUrl)
                .path(path)
                .queryParam("limit", 100)
                .queryParam("details", "on");

        if (offset > 0) {
            LOGGER.debug("Offset:" + offset);
            webTarget = webTarget.queryParam("offset", offset);
        }

        final Invocation.Builder tmpClient = webTarget.request(MediaType.APPLICATION_JSON_TYPE)
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .header("X-Auth-Token", token);

        if (service != null) {
            tmpClient.header("Fiware-Service", service);
        }
        if (servicePath != null) {
            tmpClient.header("Fiware-ServicePath", servicePath);
        }

        return tmpClient;
    }
}
