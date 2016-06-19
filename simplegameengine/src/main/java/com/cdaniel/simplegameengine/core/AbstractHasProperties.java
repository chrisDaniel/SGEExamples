package com.cdaniel.simplegameengine.core;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by christopher.daniel on 5/7/16.
 */
public abstract class AbstractHasProperties implements HasProperties {

    /*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~/
    * Variables
    *
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    private HashMap<String, Object> props = new HashMap<>();


    /*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~/
    * Standard Properties Management
    *
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    @Override
    public void applyProperties(Map<String, Object> props) {

        for (String property : props.keySet()) {
            applyProperty(property, props.get(property));
        }
    }

    @Override
    public void applyProperty(String propertyName, Object propertyValue) {
        beforePropertyApply(propertyName, propertyValue);
        this.props.put(propertyName, propertyValue);
    }

    @Override
    public HashMap<String, Object> getAllProperties() {

        return props;
    }

    @Override
    public Object getProperty(String propertyName) {

        return props.get(propertyName);
    }


    /*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~/
    * In case custom work needs to happen before apply
    *
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    abstract protected void beforePropertyApply(String propertyName, Object propertyValue);
}