package com.amaxilatis.orion.model.subscribe;

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
public class SubscribeContextAvailabilityRequest {
    List<OrionEntity> entities;
    List<String> attributes;
    String reference;
    String duration;
    List<NotifyConditions> notifyConditions;

    public SubscribeContextAvailabilityRequest() {
        this.entities = new ArrayList<OrionEntity>();
        this.attributes = new ArrayList<String>();
        this.duration = "P1M";
        this.notifyConditions = new ArrayList<NotifyConditions>();
    }

    public List<OrionEntity> getEntities() {
        return entities;
    }

    public void setEntities(List<OrionEntity> entities) {
        this.entities = entities;
    }

    public List<String> getAttributes() {
        return attributes;
    }

    public void setAttributes(List<String> attributes) {
        this.attributes = attributes;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public List<NotifyConditions> getNotifyConditions() {
        return notifyConditions;
    }

    public void setNotifyConditions(List<NotifyConditions> notifyConditions) {
        this.notifyConditions = notifyConditions;
    }
}
