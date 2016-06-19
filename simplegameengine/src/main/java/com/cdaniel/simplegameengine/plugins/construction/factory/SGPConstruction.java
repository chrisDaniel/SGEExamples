package com.cdaniel.simplegameengine.plugins.construction.factory;

import com.cdaniel.simplegameengine.core.SimpleGamePlugin;
import com.cdaniel.simplegameengine.plugins.construction.infrastructure.builders.InfrastructureBuilder;
import com.cdaniel.simplegameengine.plugins.construction.signage.builders.SignBuilder;
import com.cdaniel.simplegameengine.plugins.construction.simpleshapes.builders.SimpleShapeBuilder;

/**
 * Created by christopher.daniel on 5/7/16.
 */
public class SGPConstruction implements SimpleGamePlugin {


    /*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~/
    * Builders
    *
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    private InfrastructureBuilder infrastructureBuilder = new InfrastructureBuilder();
    private SimpleShapeBuilder shapeBuilder = new SimpleShapeBuilder();
    private SignBuilder signBuilder = new SignBuilder();

    /*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~/
    * Access to Builders
    *
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    public InfrastructureBuilder infrastructure(){
        return infrastructureBuilder;
    }
    public SimpleShapeBuilder shapes(){return shapeBuilder;}
    public SignBuilder signs(){return signBuilder; }

    /*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~/
    * Plugin Contract
    *
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    @Override
    public void beforeDraw() {
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
}
