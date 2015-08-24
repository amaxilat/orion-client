package com.sparkworks.orion.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Dimitrios Amaxilatis.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class DiscoverContextAvailabilityRequest {
    List<OrionContextElement> entities;

    public DiscoverContextAvailabilityRequest() {
        entities = new ArrayList<OrionContextElement>();
    }

    public List<OrionContextElement> getEntities() {
        return entities;
    }

    public void setEntities(List<OrionContextElement> entities) {
        this.entities = entities;
    }
}
