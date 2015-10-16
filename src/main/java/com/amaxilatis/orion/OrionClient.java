package com.amaxilatis.orion;

import com.amaxilatis.orion.model.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.amaxilatis.orion.model.subscribe.NotifyConditions;
import com.amaxilatis.orion.model.subscribe.OrionEntity;
import com.amaxilatis.orion.model.subscribe.SubscribeContextAvailabilityRequest;
import com.amaxilatis.orion.model.subscribe.SubscriptionResponse;
import org.apache.log4j.Logger;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    private final String token;
    private String serverUrl;

    public static Map<String, Object> createAttribute(String name, String type, String value) {
        Map<String, Object> attribute = new HashMap<String, Object>();
        attribute.put("name", name);
        attribute.put("type", type);
        attribute.put("value", value);
        return attribute;
    }

    public static Map<String, Object> createAttributeWithMetadata(String name, String type, String value, final String id) {
        final Map<String, Object> attribute = new HashMap<String, Object>();
        attribute.put("name", name);
        attribute.put("type", type);
        attribute.put("value", value);

        final List<Map<String, String>> metadatas = new ArrayList<Map<String, String>>();
        metadatas.add(createMetadata("ID", "string", id));
        attribute.put("metadatas", metadatas);
        return attribute;
    }

    public static Map<String, Object> createAttributeWithTimeInstant(String name, String type, String value, final String timeInstant) {
        final Map<String, Object> attribute = new HashMap<String, Object>();
        attribute.put("name", name);
        attribute.put("type", type);
        attribute.put("value", value);

        final List<Map<String, String>> metadatas = new ArrayList<Map<String, String>>();
        metadatas.add(createMetadata("TimeInstant", "ISO8601", timeInstant));
        attribute.put("metadatas", metadatas);
        return attribute;
    }


    public static Map<String, Object> createAttributeWithCode(String name, String type, String value, final String code) {
        final Map<String, Object> attribute = new HashMap<String, Object>();
        attribute.put("name", name);
        attribute.put("type", type);
        attribute.put("value", value);

        final List<Map<String, String>> metadatas = new ArrayList<Map<String, String>>();
        metadatas.add(createMetadata("code", "", code));
        attribute.put("metadatas", metadatas);
        return attribute;
    }

    private static Map<String, String> createMetadata(String name, String type, String value) {
        Map<String, String> metadata = new HashMap<String, String>();
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
    public final String postContextEntity(final String uri, final String entity) {
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
        OrionContextElementOperation operation = new OrionContextElementOperation();
        operation.getContextElements().add(entity);
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

        final String resp = getPath2("/v1/subscribeContext", request);
        LOGGER.debug(resp);
        return new ObjectMapper().readValue(resp, SubscriptionResponse.class);
    }

    public ContextElementList listContextEntities() throws IOException {
        final String response = getPath("/v1/contextEntities");
        LOGGER.debug(response);
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
    private String getPath2(final String path, final SubscribeContextAvailabilityRequest request) throws IOException {
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
        final Response response = getClientForPath(path).get();

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
        Client client = ClientBuilder.newClient();
        LOGGER.debug("path: " + path);
        return client.target(serverUrl)
                .path(path)
                .request(MediaType.APPLICATION_JSON_TYPE)
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
//                .header("Accept", "application/xml")
                .header("X-Auth-Token", token);
    }
}
