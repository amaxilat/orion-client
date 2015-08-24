# orion-client

<<<<<<< HEAD
Java Client for the Orion Context Broker
=======
Java Client for the Orion Context Broker Publish/Subscribe Context Broker GE, providing the NGSI9 and NGSI10 interfaces. Using these interfaces, the clients can do several operations:
* Register context producer applications, e.g. a temperature sensor within a room
* Update context information, e.g. send updates of temperature
* Register for notifications when changes on context information take place (e.g. the temperature has changed) or with a given frequency (e.g. get the temperature each minute).
* Query context information. The Orion Context Broker stores context information updated from applications, so queries are resolved based on that information.
>>>>>>> 6221827e9a25e2fe5bcddd0332602104cf2727c4

## Setup an Orion Context Broker Client
    String serverUrl="http://orion.lab.fi-ware.org:1026/";
    String token="#your token here#";
    OrionClient client = new OrionClient(serverUrl, accessToken);

## Post Context Entity to Context Broker
    client.postContextEntity("urn:my:entity", objectString);

## Post Context Entity to Context Broker
    client.getContextEntity("urn:my:entity");
