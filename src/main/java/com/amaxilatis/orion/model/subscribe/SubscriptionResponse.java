package com.amaxilatis.orion.model.subscribe;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @author Dimitrios Amaxilatis.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class SubscriptionResponse {

    SubscribeResponse subscribeResponse;
    String subscribeError;


    public SubscribeResponse getSubscribeResponse() {
        return subscribeResponse;
    }

    public void setSubscribeResponse(SubscribeResponse subscribeResponse) {
        this.subscribeResponse = subscribeResponse;
    }

    public String getSubscribeError() {
        return subscribeError;
    }

    public void setSubscribeError(String subscribeError) {
        this.subscribeError = subscribeError;
    }

    @Override
    public String toString() {
        return "SubscriptionResponse{" +
                "subscribeResponse=" + subscribeResponse +
                '}';
    }
}
