package com.cdaniel.simplegameengine.engine;

import com.cdaniel.simplegameengine.core.SimpleGameEngine;
import com.cdaniel.simplegameengine.core.SimpleGamePlugin;
import com.cdaniel.simplegameengine.plugins.construction.factory.SGPConstruction;
import com.cdaniel.simplegameengine.plugins.devtools.SGPDevTools;
import com.cdaniel.simplegameengine.plugins.director.SGPDirector;
import com.cdaniel.simplegameengine.plugins.physics.core.SGPPhysics;
import com.cdaniel.simplegameengine.plugins.tween.SGPTweening;

import java.util.ArrayList;
import java.util.HashMap;

import javax.microedition.khronos.opengles.GL10;

/**
 * Created by christopher.daniel on 5/4/16.
 */
public class SGE implements SimpleGameEngine {


    /*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~/
    * Context Set
    *
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    private static int contextCounter = 0;
    private static int contextId = 0;
    private static HashMap<Integer, HashMap<String, SGEPipeline>> contextPipeline = new HashMap<>();
    private static HashMap<Integer, HashMap<String, SimpleGamePlugin>> contextPlugins = new HashMap<>();
    private static ArrayList<SimpleGamePlugin> currPlugins = new ArrayList<>();
    private static ArrayList<SGEPipeline> currPipe = new ArrayList<>();

    public static int activeContextSet() {

        return SGE.contextId;
    }

    public static int newContextSet() {

        HashMap<String, SimpleGamePlugin> plugins = new HashMap<>();
        plugins.put(SGPTweening.class.getSimpleName(), new SGPTweening());
        plugins.put(SGPDevTools.class.getSimpleName(), new SGPDevTools());
        plugins.put(SGPDirector.class.getSimpleName(), new SGPDirector());
        plugins.put(SGPConstruction.class.getSimpleName(), new SGPConstruction());
        plugins.put(SGPPhysics.class.getSimpleName(), new SGPPhysics());

        HashMap<String, SGEPipeline> pipeline = new HashMap<>();
        pipeline.put(SGEProperties.class.getSimpleName(), new SGEProperties());
        pipeline.put(SGEContent.class.getSimpleName(), new SGEContent());
        pipeline.put(SGECamera.class.getSimpleName(), new SGECamera());
        pipeline.put(SGETextures.class.getSimpleName(), new SGETextures());
        pipeline.put(SGEDraw.class.getSimpleName(), new SGEDraw());
        pipeline.put(SGELighting.class.getSimpleName(), new SGELighting());
        pipeline.put(SGEOtherGL.class.getSimpleName(), new SGEOtherGL());

        SGE.contextCounter++;
        int nextContext = SGE.contextCounter;

        SGE.contextPlugins.put(nextContext, plugins);
        SGE.contextPipeline.put(nextContext, pipeline);

        return nextContext;
    }

    public static boolean activateContextSet(int contextSetId) {

        if (!contextPipeline.containsKey(contextSetId)) {

            return false;
        }

        SGE.contextId = contextSetId;

        HashMap<String, SimpleGamePlugin> plugins = SGE.contextPlugins.get(SGE.contextId);
        SGE.tweening   = (SGPTweening) plugins.get(SGPTweening.class.getSimpleName());
        SGE.devTools   = (SGPDevTools) plugins.get(SGPDevTools.class.getSimpleName());
        SGE.director   = (SGPDirector) plugins.get(SGPDirector.class.getSimpleName());
        SGE.construct  = (SGPConstruction) plugins.get(SGPConstruction.class.getSimpleName());
        SGE.physics        = (SGPPhysics) plugins.get(SGPPhysics.class.getSimpleName());

        HashMap<String, SGEPipeline> pipeline = SGE.contextPipeline.get(SGE.contextId);
        SGE.properties = (SGEProperties) pipeline.get(SGEProperties.class.getSimpleName());
        SGE.content    = (SGEContent) pipeline.get(SGEContent.class.getSimpleName());
        SGE.camera     = (SGECamera) pipeline.get(SGECamera.class.getSimpleName());
        SGE.textures   = (SGETextures) pipeline.get(SGETextures.class.getSimpleName());
        SGE.draw       = (SGEDraw) pipeline.get(SGEDraw.class.getSimpleName());
        SGE.lighting   = (SGELighting) pipeline.get(SGELighting.class.getSimpleName());
        SGE.otherGL    = (SGEOtherGL) pipeline.get(SGEOtherGL.class.getSimpleName());

        SGE.currPlugins.addAll(plugins.values());
        SGE.currPipe.addAll(pipeline.values());

        return true;
    }


    /*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~/
    * Construction
    *
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/

    //Pipeline Objects
    private static SGEProperties properties;
    private static SGEContent content;
    private static SGECamera camera;
    private static SGETextures textures;
    private static SGELighting lighting;
    private static SGEOtherGL otherGL;
    private static SGEDraw draw;


    //Plugins
    private static SGPConstruction construct;
    private static SGPTweening tweening;
    private static SGPDevTools devTools;
    private static SGPDirector director;
    private static SGPPhysics physics;

    private SGE() {

    }

    /*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~/
    * Access to Plugins
    *
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    public static SGPTweening tweening() {

        verifyEngineStarted();
        return tweening;
    }

    public static SGPConstruction construct() {

        verifyEngineStarted();
        return construct;
    }

    public static SGPDevTools devTools(){

        verifyEngineStarted();
        return devTools;
    }

    public static SGPDirector director(){

        verifyEngineStarted();
        return director;
    }

    public static SGPPhysics physics() {

        verifyEngineStarted();
        return physics;
    }

    /*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~/
    * Access to Pipeline
    *
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    public static SGEProperties properties() {

        verifyEngineStarted();
        return properties;
    }

    public static SGEContent contents() {

        verifyEngineStarted();
        return content;
    }

    public static SGECamera camera() {

        verifyEngineStarted();
        return camera;
    }

    public static SGETextures textures() {

        verifyEngineStarted();
        return textures;
    }

    public static SGELighting lighting() {

        verifyEngineStarted();
        return lighting;
    }

    public static SGEOtherGL otherGL(){

        verifyEngineStarted();
        return otherGL;
    }

    private static void verifyEngineStarted() {

        if (SGE.activeContextSet() < 1) {
            throw new RuntimeException("No Active Context");
        }
    }


    /*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~/
    * Engine
    *
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    public static int newContext(){
        int contextSet = SGE.newContextSet();
        SGE.activateContextSet(contextSet);
        return contextSet;
    }
    public static int initContextGL(GL10 gl){

        // SimpleGame Engine Context set
        int contextSet = SGE.newContextSet();
        SGE.activateContextSet(contextSet);

        // Settings for depth
        gl.glClearDepthf(1.0f);
        gl.glEnable(GL10.GL_DEPTH_TEST);
        gl.glDepthFunc(GL10.GL_LEQUAL);

        // Really nice perspective calculations.
        gl.glHint(GL10.GL_PERSPECTIVE_CORRECTION_HINT, GL10.GL_NICEST);

        // Face Culling
        return contextSet;
    }
    public static void freezeContent(){

        for(SimpleGamePlugin plugin : SGE.currPlugins){
            plugin.contentFreeze(true);
        }
        for(SGEPipeline pipe : SGE.currPipe){
            pipe.contentFreeze(true);
        }
    }
    public static void unFreezeContent(){

        for(SimpleGamePlugin plugin : SGE.currPlugins){
            plugin.contentFreeze(false);
        }
        for(SGEPipeline pipe : SGE.currPipe){
            pipe.contentFreeze(true);
        }
    }
    public static void draw(GL10 gl) {

        if(gl == null){
            return;
        }

        try {
            drawIt(gl);
        } catch (Exception e) {
            System.out.println("Draw Error!");
            e.printStackTrace();
        }
    }
    private static void drawIt(GL10 gl){

        //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~//
        // The
        // Draw Pipeline ... 0) before pipeline
        //               ... 1) properties before draw
        //               ... 2) plugins before draw
        //               ... 3) pipeline before draw ... in order ... content, camera, draw
        //               ... 4) execute draw
        //               ... 5) pipeline after draw
        //               ... 6) plugins after draw
        //               ... 7) properties after draw
        //               ... 8) after pipeline
        //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~//
        beforePipeline(gl);
        SGE.properties.beforeDraw();
        for (SimpleGamePlugin plugin : SGE.currPlugins) {
            plugin.beforeDraw();
        }
        SGE.content.beforeDraw();
        SGE.camera.beforeDraw(gl);
        SGE.draw.beforeDraw(gl);
        SGE.otherGL.beforeDraw(gl);
        //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~//
        SGE.lighting().enableLight(gl);
        SGE.lighting().enable_fullLighting(gl);
        SGE.draw.draw(gl);
        //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~//
        SGE.content.afterDraw();
        SGE.camera.afterDraw(gl);
        SGE.draw.afterDraw(gl);
        SGE.otherGL.beforeDraw(gl);
        for (SimpleGamePlugin plugin : SGE.currPlugins) {
            plugin.afterDraw();
        }
        SGE.properties().afterDraw();
        afterPipeline(gl);
    }

    private static void beforePipeline(GL10 gl) {

        // Draw background color
        gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);

        // Set GL_MODELVIEW transformation mode and reset the matrix to its default state
        gl.glMatrixMode(GL10.GL_MODELVIEW);
        gl.glLoadIdentity();
    }

    private static void afterPipeline(GL10 gl) {

    }
}