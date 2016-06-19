package com.cdaniel.simplegameengine.core;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by christopher.daniel on 5/7/16.
 */
public interface HasProperties {

    void applyProperties(Map<String, Object> props);

    void applyProperty(String propertyName, Object propertyValue);

    HashMap<String, Object> getAllProperties();

    Object getProperty(String propertyName);
}
