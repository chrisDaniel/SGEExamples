package com.cdaniel.simplegameengine.plugins.physics.physicalProperties;

import com.cdaniel.simplegameengine.core.Vector;
import com.cdaniel.simplegameengine.core.Vertex;
import com.cdaniel.simplegameengine.plugins.physics.calculations.PHYS_VelocityCalculations;
import com.cdaniel.simplegameengine.plugins.physics.core.PhysicsWrapper;
import com.cdaniel.simplegameengine.utils.calculations.Calc_VectorMath;
import com.cdaniel.simplegameengine.utils.constructs.SimpleVector;

/**
 * Created by christopher.daniel on 5/26/16.
 */
public class PHYSPROP_Pendulum extends AbstractPhysicalProperty implements PhysicalProperty{

    /* ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    * Vars / Construct
    *
    * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    private static final Vector g = new SimpleVector(0f, 0f, 0f, 0f, -9.8f, 0f);
    private Vertex anchor;
    private SimpleVector anchorDown;
    private PhysicsWrapper wrapper;

    public PHYSPROP_Pendulum(Vertex anchor){
        this.anchor = anchor;
        this.anchorDown = new SimpleVector(this.anchor.getX(), this.anchor.getY(), this.anchor.getZ(),
                this.anchor.getX(), this.anchor.getY()-1f, this.anchor.getZ());
    }

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
    * Reference: http://hyperphysics.phy-astr.gsu.edu/hbase/pend.html#c1
    * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/

    public boolean apply(Vector currentVelocity, float duration){

        SimpleVector cord = new SimpleVector(anchor, this.wrapper.getContent().getCenter());
        float alpha = Calc_VectorMath.angleBetween(cord, anchorDown);

        float pendulumForce = -1 * 9.8f * (float) Math.sin(alpha);

        if(this.wrapper.getContent().getCenter().getX() < anchor.getX()){
           pendulumForce = -1 * pendulumForce;
        }

        float accX = pendulumForce * (float) Math.cos(alpha);
        float accY = pendulumForce * (float) Math.sin(alpha);
        float accZ = 0f;

        System.out.println("Alpha: " + alpha);
        System.out.println("Forces: " + accX + "   " + accY);

        SimpleVector acceleration = new SimpleVector(0f, 0f, 0f, accX, accY, accZ);

        Vector applyV = PHYS_VelocityCalculations.applyAcceleration(currentVelocity, acceleration, duration);
        currentVelocity.transform(applyV);
        return true;
    }
}
