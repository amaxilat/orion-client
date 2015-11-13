package com.amaxilatis.orion.model.subscribe;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @author Dimitrios Amaxilatis.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class SubscribeResponse {

    String subscriptionId;
    String duration;
    String throttling;
    String subscribeError;

    public SubscribeResponse() {
    }

    public String getSubscriptionId() {
        return subscriptionId;
    }

    public void setSubscriptionId(String subscriptionId) {
        this.subscriptionId = subscriptionId;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getThrottling() {
        return throttling;
    }

    public void setThrottling(String throttling) {
        this.throttling = throttling;
    }

    public String getSubscribeError() {
        return subscribeError;
    }

    public void setSubscribeError(String subscribeError) {
        this.subscribeError = subscribeError;
    }

    @Override
    public String toString() {
        return "SubscribeResponse{" +
                "subscriptionId='" + subscriptionId + '\'' +
                ", duration='" + duration + '\'' +
                ", throttling='" + throttling + '\'' +
                '}';
    }
}
