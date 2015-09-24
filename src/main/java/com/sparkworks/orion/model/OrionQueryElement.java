package com.sparkworks.orion.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.codehaus.jackson.map.annotate.JsonSerialize;


/**
 * Maps an OrionContext Element.
 *
 * @author Dimitrios Amaxilatis
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class OrionQueryElement {
    String type;
    String isPattern;
    String id;

    public OrionQueryElement() {
        isPattern = "false";
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getIsPattern() {
        return isPattern;
    }

    public void setIsPattern(String isPattern) {
        this.isPattern = isPattern;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id.replaceAll("/", ":");
    }

    @Override
    public String toString() {
        return "OrionContextElement{" +
                "id='" + id + '\'' +
                ", isPattern='" + isPattern + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}

