package com.cdaniel.simplegameengine.plugins.physics.physicalProperties;

import com.cdaniel.simplegameengine.core.Vector;
import com.cdaniel.simplegameengine.plugins.physics.calculations.PHYS_VelocityCalculations;
import com.cdaniel.simplegameengine.plugins.physics.core.PhysicsWrapper;
import com.cdaniel.simplegameengine.utils.constructs.SimpleVector;

/**
 * Created by christopher.daniel on 5/23/16.
 */
public class PHYSPROP_Gravity extends AbstractPhysicalProperty implements PhysicalProperty {

    /* ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    * Vars / Construct
    *
    * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    private static final Vector acceleration = new SimpleVector(0f, 0f, 0f, 0f, -9.8f, 0f);
    private PhysicsWrapper wrapper;


    /* ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    * Attach
    *
    * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    public void attach(PhysicsWrapper wrapper){
        this.wrapper = wrapper;
    }

    /* ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    * Perform Physics
    *
    * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/

    public boolean apply(Vector currentVelocity, float duration){

        if(this.wrapper.getContent().get_minY() <= .01f){
            return true;
        }

        Vector applyV = PHYS_VelocityCalculations.applyAcceleration(currentVelocity, acceleration, duration);
        currentVelocity.transform(applyV);
        return true;
    }
}
