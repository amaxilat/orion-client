package com.amaxilatis.orion.model.subscribe;

/**
 * @author Dimitrios Amaxilatis.
 */
public class SubscribeResponse {

    String subscriptionId;
    String duration;
    String throttling;

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

    @Override
    public String toString() {
        return "SubscribeResponse{" +
                "subscriptionId='" + subscriptionId + '\'' +
                ", duration='" + duration + '\'' +
                ", throttling='" + throttling + '\'' +
                '}';
    }
}
