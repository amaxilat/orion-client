package com.amaxilatis.orion.model;

import java.io.Serializable;
import java.util.List;

/**
 * Maps the OrionContextBroker Response Status.
 *
 * @author Dimitrios Amaxilatis.
 */
public class SubscriptionResponse implements Serializable {
    String subscriptionId;
    String originator;
    List<OrionContextElementWrapper> contextResponses;

    public String getSubscriptionId() {
        return subscriptionId;
    }

    public void setSubscriptionId(String subscriptionId) {
        this.subscriptionId = subscriptionId;
    }

    public String getOriginator() {
        return originator;
    }

    public void setOriginator(String originator) {
        this.originator = originator;
    }

    public List<OrionContextElementWrapper> getContextResponses() {
        return contextResponses;
    }

    public void setContextResponses(List<OrionContextElementWrapper> contextResponses) {
        this.contextResponses = contextResponses;
    }

    @Override
    public String toString() {
        return "SubscriptionResponse{" +
                "subscriptionId='" + subscriptionId + '\'' +
                ", originator='" + originator + '\'' +
                '}';
    }
}
