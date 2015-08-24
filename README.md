# orion-client

Java Client for the Orion Context Broker

## Setup an Orion Context Broker Client
    String serverUrl="http://orion.lab.fi-ware.org:1026/";
    String token="#your token here#";
    OrionClient client = new OrionClient(serverUrl, accessToken);

## Post Context Entity to Context Broker
    client.postContextEntity("urn:my:entity", objectString);

## Post Context Entity to Context Broker
    client.getContextEntity("urn:my:entity");
