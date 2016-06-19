package com.cdaniel.simplegameengine.plugins.physics.core;

import com.cdaniel.simplegameengine.core.SimpleGamePlugin;
import com.cdaniel.simplegameengine.core.Vector;
import com.cdaniel.simplegameengine.engine.SGE;
import com.cdaniel.simplegameengine.engine.SGEContentWrapper;
import com.cdaniel.simplegameengine.plugins.physics.collisionDetection.CollisionBound_Rectangle;
import com.cdaniel.simplegameengine.plugins.physics.collisionDetection.CollisionBoundary;
import com.cdaniel.simplegameengine.plugins.physics.constants.ColliderType;
import com.cdaniel.simplegameengine.plugins.physics.physicalProperties.PhysicalProperty;
import com.cdaniel.simplegameengine.utils.constructs.SimpleVector;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by christopher.daniel on 5/11/16.
 */
public class SGPPhysics implements SimpleGamePlugin {

    /*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~/
    * Bounds
    *
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    float world_minY = 0f;


    /*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~/
    * Construct and Variables
    *
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    private static int globalPhysicPropId = 0;

    HashMap<Integer, PhysicsWrapper> wrappedMap;
    HashMap<Integer, PhysicsWrapper> physicalPropertyMap;

    private final PhysicsExecutor executor;

    public SGPPhysics(){

        this.wrappedMap = new HashMap<>();
        this.physicalPropertyMap = new HashMap<>();

        this.executor = new PhysicsExecutor();
    }


    /*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~/
    * Enable Physics on Objects
    *
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    public void pausePhysics(int contentId){

    }
    public void removePhysics(int contentId){

    }

    /*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~/
    * Apply Property
    *
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    public int applyPhysicalProperty(int contentId, PhysicalProperty property){

        if(!wrappedMap.containsKey(contentId)){
            return -1;
        }

        globalPhysicPropId++;
        property.setGlobalId(globalPhysicPropId);

        PhysicsWrapper wrapper = wrappedMap.get(contentId);
        wrapper.applyPhysicalProperty(contentId, property);
        property.attach(wrapper);

        physicalPropertyMap.put(globalPhysicPropId, wrapper);
        return globalPhysicPropId;
    }

    /*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~/
    * Direct Testing
    *
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    private PhysicsLookTest plt = new PhysicsLookTest();
    public Integer performLookTest(){

        PhysicsWrapper w = plt.performLookAtTest(this.wrappedMap.values());

        if(w==null){
            return 0;
        }
        else{
            return w.getContent().getContentId();
        }
    }

    /*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~/
   * Plugin Contract
   *
   *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    @Override
    public void beforeDraw() {

        ArrayList wrappers = new ArrayList();
        wrappers.addAll(this.wrappedMap.values());
        this.executor.executePhysics(wrappers);
    }
    @Override
    public void afterDraw(){
    }
    @Override
    public void contentFreeze(boolean isFrozen){
    }
    @Override
    public void pause(boolean isPaused){
    }
    @Override
    public void deleteEverything(){
    }


    /*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~/
   * Builder
   *
   *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    public Activator activator(){
        return new Activator(this);
    }
    public class Activator{

        //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~/
        //Construct with Ref to the plugin
        //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~/
        Activator(SGPPhysics sgpP){
            this.sgpP = sgpP;
        }


        //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~/
        //Build Variables
        //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~/
        SGPPhysics sgpP;
        int contentId;
 
        Vector currentVelocity = new SimpleVector();
        float mass = 0f;

        CollisionBoundary collisionBoundary = new CollisionBound_Rectangle();
        ColliderType colliderType = ColliderType.None;
        float elasticity = 0f;

        Vector collisionNormal = new SimpleVector(0f, 0f, 0f, 0f, 1f, 0f);

        //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~/
        //Set Variables
        //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~/
        public Activator contentId(int contentId){
            this.contentId = contentId;
            return this;
        }
        public Activator mass(float mass){
            this.mass = mass;
            return this;
        }
        public Activator initialVelocity(Vector initalVelocity){
            this.currentVelocity = initalVelocity;
            return this;
        }
        public Activator collisionBoundary(CollisionBoundary cb){
            this.collisionBoundary = cb;
            return this;
        }
        public Activator elasticity(float elasticity){
            this.elasticity = elasticity;
            return this;
        }
        public Activator colliderType(ColliderType ct){
            this.colliderType = ct;
            return this;
        }
        public Activator collisionNormal(Vector collisionNormal){
            this.collisionNormal = collisionNormal;
            return this;
        }
        public void activate(){

            if(sgpP.wrappedMap.containsKey(contentId)){
                return;
            }

            SGEContentWrapper content = SGE.contents().get(contentId);
            if(content == null){
                return;
            }

            PhysicsWrapper wrapper = new PhysicsWrapper(content);
            wrapper.currentVelocity = this.currentVelocity;
            wrapper.mass = mass;
            wrapper.elasticity = this.elasticity;
            wrapper.colliderType = this.colliderType;
            wrapper.collisionBoundary = this.collisionBoundary;

            wrapper.collisionNormal = this.collisionNormal;

            sgpP.wrappedMap.put(contentId, wrapper);
        }

    }
}
