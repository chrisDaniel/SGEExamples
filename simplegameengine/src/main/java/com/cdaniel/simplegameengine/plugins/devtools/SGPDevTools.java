package com.cdaniel.simplegameengine.plugins.devtools;

import com.cdaniel.simplegameengine.core.SimpleGameEngine;
import com.cdaniel.simplegameengine.core.SimpleGamePlugin;
import com.cdaniel.simplegameengine.core.Vertex;
import com.cdaniel.simplegameengine.engine.SGE;
import com.cdaniel.simplegameengine.engine.SGEContentWrapper;
import com.cdaniel.simplegameengine.utils.calculations.Calc_VertexMath;
import com.cdaniel.simplegameengine.utils.constructs.SimpleColor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by christopher.daniel on 5/7/16.
 */
public class SGPDevTools implements SimpleGamePlugin {

    /* ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     * Convenience Methods
     *
     * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    public float calc_distanceFromCamera(int contentId){

        Vertex contentCenter = SGE.contents().get(contentId).getCenter();
        Vertex cameraPosition = SGE.camera().getEyePosition();

        float distance = Calc_VertexMath.distanceBetween(contentCenter, cameraPosition);
        return distance;
    }

    /* ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     * Axis Grid
     *
     * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    private boolean gridInitialized = false;
    private int gridXZ;
    private int gridAxis;
    private int gridPole;

    public void showAxisGrid(){

        if(gridInitialized) {

            Map<String, Object> vizibleProps = new HashMap<>();
            vizibleProps.put(SimpleGameEngine.CONTENT_STATE, SimpleGameEngine.CONTENT_STATE_Active);
            vizibleProps.put(SimpleGameEngine.CONTENT_VIZ, SimpleGameEngine.CONTENT_VIZ_Yes);

            SGE.contents().get(gridXZ).applyProperties(vizibleProps);
            SGE.contents().get(gridAxis).applyProperties(vizibleProps);
            SGE.contents().get(gridPole).applyProperties(vizibleProps);
        }
        else{
            initializeAxisGrid();
        }
    }
    public void hideAxisGrid(){

        Map<String, Object> vizibleProps = new HashMap<>();
        vizibleProps.put(SimpleGameEngine.CONTENT_STATE, SimpleGameEngine.CONTENT_STATE_Inactive);
        vizibleProps.put(SimpleGameEngine.CONTENT_VIZ, SimpleGameEngine.CONTENT_VIZ_No);

        SGE.contents().get(gridXZ).applyProperties(vizibleProps);
        SGE.contents().get(gridAxis).applyProperties(vizibleProps);
        SGE.contents().get(gridPole).applyProperties(vizibleProps);
    }
    private void initializeAxisGrid(){

        //Part 1....
        //XZ Grid Ground
        GridXZGround xz = new GridXZGround(10, 1);

        Map<String, Object> xzProps = new HashMap<>();
        xzProps.put(SimpleGameEngine.CONTENT_STATE, SimpleGameEngine.CONTENT_STATE_Active);
        xzProps.put(SimpleGameEngine.CONTENT_VIZ, SimpleGameEngine.CONTENT_VIZ_Yes);
        xzProps.put(SimpleGameEngine.CONTENT_COLOR, new SimpleColor(.3f, .3f, .3f, .8f));

        gridXZ = SGE.contents().add(xz, xzProps);


        //Part 2....
        //XZ Axis
        GridXZAxis axis = new GridXZAxis(10, 1);

        Map<String, Object> axisProps = new HashMap<>();
        axisProps.put(SimpleGameEngine.CONTENT_STATE, SimpleGameEngine.CONTENT_STATE_Active);
        axisProps.put(SimpleGameEngine.CONTENT_VIZ, SimpleGameEngine.CONTENT_VIZ_Yes);
        axisProps.put(SimpleGameEngine.CONTENT_COLOR, new SimpleColor(0f, 0f, 1f, .8f));

        gridAxis = SGE.contents().add(axis, axisProps);


        //Part 3....
        //Y Pole
        GridYPole pole = new GridYPole(10, 1);

        Map<String, Object> poleProps = new HashMap<>();
        poleProps.put(SimpleGameEngine.CONTENT_STATE, SimpleGameEngine.CONTENT_STATE_Active);
        poleProps.put(SimpleGameEngine.CONTENT_VIZ, SimpleGameEngine.CONTENT_VIZ_Yes);
        poleProps.put(SimpleGameEngine.CONTENT_COLOR, new SimpleColor(0f, 1f, 0f, .8f));

        gridPole = SGE.contents().add(pole, poleProps);
    }


    /* ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     * Logging
     *
     * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    private boolean logOn = false;
    private boolean log_contentDetail = false;

    public void logging_turnOn(){this.logOn = true; }
    public void logging_turnOff(){
        this.logOn = false;
    }
    public void logging_includeContentDetail(boolean showContentDetail){this.log_contentDetail = showContentDetail;}

    private void log(){

        if(!logOn){
            return;
        }

        if(!SGE.properties().isFrameRateDirty()){
            return;
        }

        String log = "+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++" +"\n" +
                     "DevTools Logging. (Frame: " + SGE.properties().totalFrames() +")" + "\n" +
                     "Context Id: " + SGE.activeContextSet() + ", " +
                     "Frames Per Second: " + SGE.properties().frameRate() + "\n" +
                     "Camera: (" + SGE.camera().toString() + "\n" +
                     "Content: (" + SGE.contents().getAllContent().size() + ")" + "\n";

        if(log_contentDetail){
            for(SGEContentWrapper w : SGE.contents().getAllContent()){
                log = log + "   " + w.toString() + "\n";
            }
        }

        log = log + "+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++" +"\n";
        System.out.print(log);
    }




    /*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~/
    * Plugin Contract
    *
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    private ArrayList<Integer> toRemove = new ArrayList<>();

    @Override
    public void beforeDraw() {
    }
    @Override
    public void afterDraw(){
        log();
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
