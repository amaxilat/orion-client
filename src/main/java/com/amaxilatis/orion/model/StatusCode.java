package com.amaxilatis.orion.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import java.io.Serializable;

/**
 * Maps the OrionContextBroker Response Status.
 *
 * @author Dimitrios Amaxilatis.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class StatusCode implements Serializable {

    String code;

    String reasonPhrase;
    String details;

    public String getCode() {
        return code;
    }

    public void setCode(final String code) {
        this.code = code;
    }

    public String getReasonPhrase() {
        return reasonPhrase;
    }

    public void setReasonPhrase(final String reasonPhrase) {
        this.reasonPhrase = reasonPhrase;
    }

    public String getDetails() {
        return details;
    }

    public Long getCount() {
        if (details != null && details.contains("Count: ")) {
            return Long.parseLong(details.replaceAll("Count: ", ""));
        }
        return null;
    }


    public void setDetails(String details) {
        this.details = details;
    }

    @Override
    public String toString() {
        return "StatusCode{" +
                "code='" + code + '\'' +
                ", reasonPhrase='" + reasonPhrase + '\'' +
                ", details='" + details + '\'' +
                '}';
    }
}
