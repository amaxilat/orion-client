package com.amaxilatis.orion.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.codehaus.jackson.map.annotate.JsonSerialize;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class OrionContextElementWrapper {

    OrionContextElement contextElement;
    StatusCode statusCode;

    public OrionContextElement getContextElement() {
        return contextElement;
    }

    public void setContextElement(final OrionContextElement contextElement) {
        this.contextElement = contextElement;
    }

    public StatusCode getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(StatusCode statusCode) {
        this.statusCode = statusCode;
    }

    @Override
    public String toString() {
        return "OrionContextElementWrapper{" +
                "contextElement=" + contextElement.getId() +
                ", statusCode=" + statusCode +
                '}';
    }
}