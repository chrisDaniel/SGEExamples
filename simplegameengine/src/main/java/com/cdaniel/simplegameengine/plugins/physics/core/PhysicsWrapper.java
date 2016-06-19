package com.cdaniel.simplegameengine.plugins.physics.core;

import com.cdaniel.simplegameengine.core.Vector;
import com.cdaniel.simplegameengine.engine.SGEContentWrapper;
import com.cdaniel.simplegameengine.plugins.physics.collisionDetection.CollisionBoundary;
import com.cdaniel.simplegameengine.plugins.physics.constants.ColliderType;
import com.cdaniel.simplegameengine.plugins.physics.physicalProperties.PhysicalProperty;
import com.cdaniel.simplegameengine.utils.constructs.SimpleVector;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by christopher.daniel on 5/23/16.
 */
public class PhysicsWrapper {

    /* ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    * Variables
    *
    * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/

    final SGEContentWrapper content;

    //------------------------//
    //content properties      //
    //------------------------//
    Vector currentVelocity = new SimpleVector();
    float mass = 0f;


    //------------------------//
    //collision properties    //
    //------------------------//
    CollisionBoundary collisionBoundary;
    ColliderType colliderType = ColliderType.None;
    Vector collisionNormal;
    float elasticity = .5f;


    //------------------------//
    //physics properties      //
    //------------------------//
    private HashMap<Integer, PhysicalProperty> physicalProperties;


    /* ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    * Constructor
    *
    * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    PhysicsWrapper(SGEContentWrapper content){
        this.content = content;
        this.physicalProperties = new HashMap<>();
    }


    /*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~/
    * Add / Modify Properties
    *
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    public void applyPhysicalProperty(Integer id, PhysicalProperty property){

        this.physicalProperties.put(id,property);
    }
    public void killPhysicalProperty(int id){

        this.physicalProperties.remove(id);
    }
    public void enableCollisions(CollisionBoundary boundary, ColliderType type){

        this.collisionBoundary = boundary;
        this.colliderType = type;
    }
    public void killEverything(){
        this.physicalProperties.clear();
        this.colliderType = null;
    }


    /*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~/
    * Accessors
    *
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    ArrayList<PhysicalProperty> activeProps = new ArrayList<>();
    ArrayList<PhysicalProperty> getActiveProperties(){
        activeProps.clear();
        activeProps.addAll(this.physicalProperties.values());
        return activeProps;
    }

    public SGEContentWrapper getContent(){
        return this.content;
    }



}
