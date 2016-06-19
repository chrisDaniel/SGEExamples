package com.cdaniel.simplegameengine.plugins.physics.core;

import com.cdaniel.simplegameengine.core.Vector;
import com.cdaniel.simplegameengine.core.Vertex;
import com.cdaniel.simplegameengine.engine.SGE;
import com.cdaniel.simplegameengine.plugins.physics.calculations.PHYS_VelocityCalculations;
import com.cdaniel.simplegameengine.plugins.physics.physicalProperties.PhysicalProperty;
import com.cdaniel.simplegameengine.utils.constructs.SimpleVector;
import com.cdaniel.simplegameengine.utils.transformers.Transform_Move;

import java.util.ArrayList;

/**
 * Created by christopher.daniel on 5/24/16.
 */
class PhysicsExecutor {


    /*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~/
    * Variables
    *
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    private PhysicsCollisions collisionHandler = new PhysicsCollisions();

    private boolean onPause    = false;
    private boolean wasOnPause = true;

    private long lastUpdate   = (long) -1.1;
    private long thisUpdate   = (long) -1.1;
    private long currMilis    = (long) 0;
    private float currSec = 0f;
    private float physicsTime = 0f;

    /*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~/
    * Engine
    *
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    void executePhysics(ArrayList<PhysicsWrapper> wrappers){

        if(wrappers == null || wrappers.size() == 0){
            return;
        }

        this.updateTiming();

        this.applyPhysicalProperties(wrappers);

        collisionHandler.applyCollisions(wrappers);

        this.apply(wrappers);

        this.finalizeExecution();
    }

    private void updateTiming(){

        if(wasOnPause){
            lastUpdate = SGE.properties().currentMilliseconds();
            thisUpdate = lastUpdate;
            currMilis = 0;
            currSec = 0f;
            wasOnPause = false;
        }
        else {
            thisUpdate = SGE.properties().currentMilliseconds();
            currMilis = thisUpdate - lastUpdate;
            currSec = (float) currMilis / 1000f;
            physicsTime += currSec;
        }
    }

    Transform_Move t = Transform_Move.builder().build();
    private void apply(ArrayList<PhysicsWrapper> wrappers){

        for(PhysicsWrapper w : wrappers){

            apply_II(w);
        }
    }
    private void apply_II(PhysicsWrapper w){

        if(w.currentVelocity.getEx() == 0 && w.currentVelocity.getEy() == 0 && w.currentVelocity.getEz() == 0){
            return;
        }

        Vertex currCenter = w.content.getCenter();
        Vertex newCenter = PHYS_VelocityCalculations.applyVelocity(currCenter, w.currentVelocity, currSec);

        t.updateValues(newCenter);
        w.content.applyTransform(t);

        //log_activity(w);
    }

    private void finalizeExecution(){

        //log_paramaters();
        lastUpdate = thisUpdate;
    }

    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    //Step 1 -> Physical Properties
    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    private void applyPhysicalProperties(ArrayList<PhysicsWrapper> wrappers){

        for(PhysicsWrapper w : wrappers){

            updatePhysicalProperties_II(w);
        }
    }
    private void updatePhysicalProperties_II(PhysicsWrapper w){

        Vector safeVelocity = new SimpleVector(w.currentVelocity);

        for(PhysicalProperty prop : w.getActiveProperties()){

            //apply property ... if complete (returns false) ... remove
            if(!prop.apply(safeVelocity, currSec)){
                w.killPhysicalProperty(prop.getGlobalId());
            }
        }

        w.currentVelocity.transform(safeVelocity);
    }


    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    //Logging
    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    private void log_paramaters(){

        String s = "Executing Physics!" +"\n" +
                "Iteration Duration (Sec): " + currSec + "\t" + ", Total Physics Time: " + physicsTime + "\n" +
                "Curr Update: " + thisUpdate + ", Previous Update: " + lastUpdate + ", Global Time: " + SGE.properties().currentMilliseconds();
        System.out.println(s);
    }
    private void log_activity(PhysicsWrapper w){

        String s = "Applying Physics To:" +
                "Object: " + w.content.toString() + "\n" +
                "Velocity: " + w.currentVelocity +"\n";
        System.out.println(s);
    }
}
