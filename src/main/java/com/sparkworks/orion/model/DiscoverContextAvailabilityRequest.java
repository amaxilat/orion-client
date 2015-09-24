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
    List<OrionQueryElement> entities;

    public DiscoverContextAvailabilityRequest() {
        entities = new ArrayList<OrionQueryElement>();
    }

    public List<OrionQueryElement> getEntities() {
        return entities;
    }

    public void setEntities(List<OrionQueryElement> entities) {
        this.entities = entities;
    }
}
