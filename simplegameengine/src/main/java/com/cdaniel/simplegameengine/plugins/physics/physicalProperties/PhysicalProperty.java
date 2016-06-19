package com.cdaniel.simplegameengine.plugins.physics.physicalProperties;

import com.cdaniel.simplegameengine.core.Vector;
import com.cdaniel.simplegameengine.plugins.physics.core.PhysicsWrapper;

/**
 * Created by christopher.daniel on 5/23/16.
 */
public interface PhysicalProperty {


    /* ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    * Global Id
    *
    * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    void setGlobalId(int id);
    int getGlobalId();

    /* ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    * Attach to its Wrapper
    *
    * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    void attach(PhysicsWrapper wrapper);

    /* ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    * Perform Physics
    *
    * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    boolean apply(Vector currentVelocity, float duration);
}
