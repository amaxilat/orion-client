package com.amaxilatis.orion.model.subscribe;

/**
 * @author Dimitrios Amaxilatis.
 */
public class SubscribeResponse {

    String subscriptionId;
    String duration;

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

    @Override
    public String toString() {
        return "SubscribeResponse{" +
                "subscriptionId='" + subscriptionId + '\'' +
                ", duration='" + duration + '\'' +
                '}';
    }
}
