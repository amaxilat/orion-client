package com.amaxilatis.orion.model.subscribe;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by amaxilatis on 5/7/2015.
 */
public class NotifyConditions {
    String type;
    List<String> condValues;

    public NotifyConditions(String type, String condValues) {
        this.type = type;
        this.condValues = new ArrayList<String>();
        if (condValues != null) {
            this.condValues.add(condValues);
        }
    }

    public NotifyConditions() {
        this.type = "ONTIMEINTERVAL";
        this.condValues = new ArrayList<String>();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<String> getCondValues() {
        return condValues;
    }

    public void setCondValues(List<String> condValues) {
        this.condValues = condValues;
    }
}
