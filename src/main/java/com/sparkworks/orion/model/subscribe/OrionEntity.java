package com.sparkworks.orion.model.subscribe;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.codehaus.jackson.map.annotate.JsonSerialize;

/**
 * @author Dimitrios Amaxilatis.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class OrionEntity {

    String type;
    String isPattern;
    String id;

    public OrionEntity() {
        //required for mapping.
    }

    public OrionEntity(final String type, final String isPattern, final String id) {
        this.type = type;
        this.isPattern = isPattern;
        this.id = id;
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
}
