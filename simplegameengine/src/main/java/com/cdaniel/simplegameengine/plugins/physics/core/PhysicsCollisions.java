package com.cdaniel.simplegameengine.plugins.physics.core;

import com.cdaniel.simplegameengine.core.Vector;
import com.cdaniel.simplegameengine.plugins.physics.calculations.PHYS_VectorReflection;
import com.cdaniel.simplegameengine.plugins.physics.collisionDetection.CollisionBound_Rectangle;
import com.cdaniel.simplegameengine.plugins.physics.constants.ColliderType;
import com.cdaniel.simplegameengine.utils.calculations.Calc_VectorMath;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by christopher.daniel on 5/25/16.
 */
public class PhysicsCollisions {


    /*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~/
    * Variables
    *
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    HashMap<String, CollidingPair> collisions = new HashMap<>();


    /*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~/
    * Engine
    *
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    public void applyCollisions(ArrayList<PhysicsWrapper> wrappers){

        ArrayList<PhysicsWrapper> initiators = new ArrayList<>();
        ArrayList<PhysicsWrapper> listeners = new ArrayList<>();

        for(PhysicsWrapper w : wrappers){

            if(w.colliderType.equals(ColliderType.Initiator)){
                initiators.add(w);
            }
            if(w.colliderType.equals(ColliderType.Listener)){
                listeners.add(w);
            }
        }

        HashMap<String, CollidingPair> newcollisions = collisions_findCollisions(initiators, listeners);

        for(CollidingPair pair : newcollisions.values()){
            collisions_applyCollisions(pair);
        }

        HashMap<String, CollidingPair> stillColliding = new HashMap<>();
        for(String pairKey : collisions.keySet()){
            if(collisions.get(pairKey).stillColliding()){
                stillColliding.put(pairKey, collisions.get(pairKey));
            }
        }
        collisions.clear();
        collisions.putAll(stillColliding);
        collisions.putAll(newcollisions);
    }

    private HashMap<String, CollidingPair> collisions_findCollisions(ArrayList<PhysicsWrapper> initiators, ArrayList<PhysicsWrapper> listeners){

        HashMap<String, CollidingPair> newCollisions = new HashMap<>();
        HashMap<String, Integer> misses     = new HashMap<>();

        for(PhysicsWrapper i : initiators){

            for(PhysicsWrapper l : listeners){

                String pairKey = processCollisions_pairKey(i, l);

                if(pairKey == null){
                    continue;
                }
                if(collisions.containsKey(pairKey)){
                    continue;
                }
                if(newCollisions.containsKey(pairKey) || misses.containsKey(pairKey)){
                    continue;
                }


                if(processCollisions_CheckForCollision(i, l)){
                    newCollisions.put(pairKey, new CollidingPair(i, l));
                }
                else{
                    misses.put(pairKey, 1);
                }
            }
        }

        return newCollisions;
    }
    private boolean processCollisions_CheckForCollision(PhysicsWrapper i, PhysicsWrapper l){

        CollisionBound_Rectangle bound = new CollisionBound_Rectangle();
        return bound.detectCollision(i, l);
    }


    /*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~/
     * Apply a Collision
     *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    //todo: bug -> example: if you have two 'floors' then the collision reflection happens twice and it reflects back to original tract
    private void collisions_applyCollisions(CollidingPair pair){

        Vector iVelocity = pair.i.currentVelocity;
        float ei = pair.i.elasticity;

        Vector reflected = PHYS_VectorReflection.reflectVector(iVelocity, pair.l.collisionNormal);
        Vector iReflected = Calc_VectorMath.scaleVector(reflected, ei);

        pair.i.currentVelocity = iReflected;
    }


    /*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~/
     * Utils
     *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    private String processCollisions_pairKey(PhysicsWrapper w1, PhysicsWrapper w2){

        if(w1.content.getContentId() < w2.content.getContentId()) {
            return "ids-" + w1.content.getContentId() + "-" + w2.content.getContentId();
        }
        else if(w1.content.getContentId() > w2.content.getContentId()) {
            return "ids-" + w2.content.getContentId() + "-" + w1.content.getContentId();
        }
        else{
            return "";
        }
    }

    /*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~/
    * Private Class -- Colliding Pairs
    * To track Collision data
    *
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    private class CollidingPair{

        final PhysicsWrapper i;
        final PhysicsWrapper l;


        private CollidingPair(PhysicsWrapper initiator, PhysicsWrapper listener){

            this.i = initiator;
            this.l = listener;
        }

        private boolean stillColliding(){

            boolean stillColliding = processCollisions_CheckForCollision(i, l);
            return stillColliding;
        }
    }
}
