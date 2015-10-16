package com.amaxilatis.orion.model.subscribe;

/**
 * @author Dimitrios Amaxilatis.
 */
public class SubscriptionResponse {

    SubscribeResponse subscribeResponse;

    public SubscribeResponse getSubscribeResponse() {
        return subscribeResponse;
    }

    public void setSubscribeResponse(SubscribeResponse subscribeResponse) {
        this.subscribeResponse = subscribeResponse;
    }

    @Override
    public String toString() {
        return "SubscriptionResponse{" +
                "subscribeResponse=" + subscribeResponse +
                '}';
    }
}
