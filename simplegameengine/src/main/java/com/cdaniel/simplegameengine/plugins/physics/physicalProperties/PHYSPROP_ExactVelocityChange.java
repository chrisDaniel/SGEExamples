package com.cdaniel.simplegameengine.plugins.physics.physicalProperties;

import com.cdaniel.simplegameengine.core.Vector;
import com.cdaniel.simplegameengine.plugins.physics.calculations.PHYS_VelocityCalculations;
import com.cdaniel.simplegameengine.plugins.physics.core.PhysicsWrapper;

/**
 * Created by christopher.daniel on 5/23/16.
 */
public class PHYSPROP_ExactVelocityChange extends AbstractPhysicalProperty implements PhysicalProperty {

    /* ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    * Vars / Construct
    *
    * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    private final Vector veloChange;

    public PHYSPROP_ExactVelocityChange(Vector veloChange){
        this.veloChange = veloChange;
    }


    /* ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    * Attach
    *
    * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    public void attach(PhysicsWrapper wrapper){
        //dont care
    }

    /* ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    * Perform Physics
    *
    * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    public boolean apply(Vector currentVelocity, float duration){

        PHYS_VelocityCalculations.addVelocity(currentVelocity, veloChange);
        return false;
    }
}
