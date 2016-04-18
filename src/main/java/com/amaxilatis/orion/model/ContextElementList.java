package com.amaxilatis.orion.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import java.util.List;

/**
 * Maps a List of Orion Context Elements.
 *
 * @author Dimitrios Amaxilatis.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class ContextElementList {
    List<OrionContextElementWrapper> contextResponses;
    StatusCode statusCode;
    StatusCode errorCode;

    public List<OrionContextElementWrapper> getContextResponses() {
        return contextResponses;
    }

    public void setContextResponses(List<OrionContextElementWrapper> contextResponses) {
        this.contextResponses = contextResponses;
    }

    public StatusCode getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(StatusCode statusCode) {
        this.statusCode = statusCode;
    }

    public StatusCode getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(StatusCode errorCode) {
        this.errorCode = errorCode;
    }

    public boolean hasMore(final long offset) {
        try {
            System.out.println("Offset:" + offset + " count:" + getErrorCode().getCount());
            return getErrorCode().getCount() > offset;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public String toString() {
        return "ContextElementList{" +
                "statusCode=" + statusCode +
                '}';
    }
}
