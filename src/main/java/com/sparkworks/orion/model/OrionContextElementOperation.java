package com.sparkworks.orion.model;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Dimitrios Amaxilatis.
 */
public class OrionContextElementOperation {
    List<OrionContextElement> contextElements;
    String updateAction;

    public OrionContextElementOperation() {
        contextElements=new ArrayList<OrionContextElement>();
    }

    public List<OrionContextElement> getContextElements() {
        return contextElements;
    }

    public void setContextElements(List<OrionContextElement> contextElements) {
        this.contextElements = contextElements;
    }

    public String getUpdateAction() {
        return updateAction;
    }

    public void setUpdateAction(String updateAction) {
        this.updateAction = updateAction;
    }
}
