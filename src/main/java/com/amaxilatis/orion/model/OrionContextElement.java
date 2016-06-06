package com.amaxilatis.orion.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


/**
 * Maps an Orion Context Element.
 *
 * @author Dimitrios Amaxilatis
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class OrionContextElement implements Serializable {
    String type;
    String isPattern;
    String id;
    List<Attribute> attributes;

    public OrionContextElement() {
        attributes = new ArrayList<>();
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
        this.id = id;
    }

    public List<Attribute> getAttributes() {
        return attributes;
    }

    public void setAttributes(List<Attribute> attributes) {
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

