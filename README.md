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

## Connecting to an Orion Context Broker Client

To connect to a specific Orion CB you need to create and instance of the OrionClient class and provide the url of the server and the access token if security is used. When you have no security setup you can pass an empty String to it.

    String serverUrl="http://orion.lab.fi-ware.org:1026/";
    String token="#your token here#";
    OrionClient client = new OrionClient(serverUrl, accessToken);

### Connecting to a specific Service and Service Path

When you run multiple services on the same Orion CB you need to specify the Service and ServicePath headers you  need to connect to. To do this you need to use a different OrionClient constructor, where the last two arguments are the Service name and Service path to be used in every request.

    String serverUrl="http://orion.lab.fi-ware.org:1026/";
    String token="#your token here#";
    OrionClient client = new OrionClient(serverUrl, accessToken,"MyService","/myServicePath");

## Post Context Entity to Context Broker
    client.postContextEntity("urn:my:entity", objectString);

## Get Context Entity from Context Broker
    client.getContextEntity("urn:my:entity");
    
### Using this library from maven
    
    <repositories>
        <repository>
            <id>sparkworks</id>
            <url>http://nexus.sparkworks.net/nexus/content/repositories/snapshots</url>
        </repository>
    </repositories>
    <dependencies>
        <dependency>
            <groupId>com.amaxilatis</groupId>
            <artifactId>orion-client</artifactId>
            <version>1.0-SNAPSHOT</version>
        </dependency>
    </dependencies>
