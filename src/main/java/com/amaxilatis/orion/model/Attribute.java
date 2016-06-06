package com.amaxilatis.orion.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Maps an Orion Context Element Attribute property.
 *
 * @author Dimitrios Amaxilatis
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class Attribute implements Serializable {
    String name;
    String type;
    String value;
    List<Metadata> metadatas;

    public Attribute() {
        //required for object mapper
    }

    public Attribute(final String name, final String type, final String value) {
        this.name = name;
        this.type = type;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public String getValue() {
        return value;
    }

    public List<Metadata> getMetadatas() {
        return metadatas;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public void setType(final String type) {
        this.type = type;
    }

    public void setValue(final String value) {
        this.value = value;
    }


    public void setMetadatas(final List<Metadata> metadatas) {
        this.metadatas = metadatas;
    }

    @Override
    public String toString() {
        return "Attribute{" +
                "name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", value='" + value + '\'' +
                '}';
    }
}