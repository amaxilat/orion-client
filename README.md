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
    
## Pagination in the Orion Context Broker requests
By default Orion CB provides only a limited number of context elements in each response for performance reasons. 
The Orion CB pagination works by providing an offset to the starting element to be delivered. As a result to request, for example, 34 elements from an Orion CB you need to make two requests, one for the first 20 and a second one for the rest. To achive this you need to pass the starting offset to the respective OrionClient method call.


    final ContextElementList elementListFirst = client.listContextEntities();
    final ContextElementList elementListRest = client.listContextEntities(20);
    
Additionally to check of there are any more elements that you have not received OrionClient offers the 'hasMore' method in the 'ContextElementList' that given an offset returns 'true' when there are more elements to be requested. 

To request all available elements of an Orion CB you need to implement something similar to the following:

    long offset = 0;
    ContextElementList elementList =  client.listContextEntities(offset);
    //do something
    while (elementList.hasMore(offset)) {
        elementList = client.listContextEntities(offset);
        //do something
        offset += elementList.getContextResponses().size();
    }

    
### Using this library from maven
    
    <repositories>
        <repository>
            <id>amaxilatis</id>
            <url>http://maven.amaxilatis.com/nexus/content/repositories/snapshots</url>
        </repository>
    </repositories>
    <dependencies>
        <dependency>
            <groupId>com.amaxilatis</groupId>
            <artifactId>orion-client</artifactId>
            <version>1.0-SNAPSHOT</version>
        </dependency>
    </dependencies>
