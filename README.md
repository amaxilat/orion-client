[![Build Status](https://travis-ci.org/amaxilat/orion-client.svg?branch=master)](https://travis-ci.org/amaxilat/orion-client)

[pmd](https://amaxilat.github.io/orion-client/javadoc/pmd.html)

[checkstyle](https://amaxilat.github.io/orion-client/javadoc/checkstyle.html)

# orion-client

Java Client for the Orion Context Broker Publish/Subscribe Context Broker GE, providing the NGSI9 and NGSI10 interfaces. Using these interfaces, the clients can do several operations:
* Register context producer applications, e.g. a temperature sensor within a room
* Update context information, e.g. send updates of temperature
* Register for notifications when changes on context information take place (e.g. the temperature has changed) or with a given frequency (e.g. get the temperature each minute).
* Query context information. The Orion Context Broker stores context information updated from applications, so queries are resolved based on that information.

### Javadoc

The latest Javadoc is available [here](https://amaxilat.github.io/orion-client/javadoc/apidocs/).

## Setup an Orion Context Broker Client
    String serverUrl="http://orion.lab.fi-ware.org:1026/";
    String token="#your token here#";
    OrionClient client = new OrionClient(serverUrl, accessToken);

## Post Context Entity to Context Broker
    client.postContextEntity("urn:my:entity", objectString);

## Post Context Entity to Context Broker
    client.getContextEntity("urn:my:entity");
