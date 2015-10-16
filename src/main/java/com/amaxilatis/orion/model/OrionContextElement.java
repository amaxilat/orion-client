package com.amaxilatis.orion.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 * Maps an OrionContext Element.
 *
 * @author Dimitrios Amaxilatis
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class OrionContextElement {
    String type;
    String isPattern;
    String id;
    List<Map<String, Object>> attributes;

    public OrionContextElement() {
        attributes = new ArrayList<Map<String, Object>>();
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

    public List<Map<String, Object>> getAttributes() {
        return attributes;
    }

    public void setAttributes(List<Map<String, Object>> attributes) {
        this.attributes = attributes;
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

