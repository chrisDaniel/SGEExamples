package com.cdaniel.simplegameengine.engine;

import com.cdaniel.simplegameengine.core.DrawableData;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by christopher.daniel on 5/4/16.
 */
public class SGEContent implements SGEPipeline {

    /*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~/
    * Construct and Variables
    *
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    private static int contentId = 0;

    private Map<Integer, SGEContentWrapper> contentMap;
    private Map<String, Map<Integer, SGEContentWrapper>> groupAwareContentMap;

    SGEContent(){

        contentMap = new HashMap<>();
        groupAwareContentMap = new HashMap<>();
    }


    /*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~/
    * Content
    *
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    public int add(DrawableData drawableData, Map<String, Object> contentProperties){

        SGEContent.contentId++;

        SGEContentWrapper wrapper = new SGEContentWrapper(SGEContent.contentId, drawableData, contentProperties);
        String groupingKey = this.buildContentGroupingKey(wrapper);

        contentMap.put(SGEContent.contentId, wrapper);
        groupAwareContentMap.get(groupingKey).put(SGEContent.contentId, wrapper);

        return SGEContent.contentId;
    }
    public SGEContentWrapper get(int contentId){

        return contentMap.get(contentId);
    }
    public void remove(int contentId){

        SGEContentWrapper cw = contentMap.get(contentId);

        if(cw == null){
            return;
        }

        String groupingKey = this.buildContentGroupingKey(cw);

        contentMap.remove(contentId);
        groupAwareContentMap.get(groupingKey).remove(contentId);
    }
    public void setVisibility(int contentId, boolean visible){

        this.get(contentId).setVisibility(visible);
    }

    /*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~/
    * Quick Accessors
    *
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    public ArrayList<SGEContentWrapper> getAllContent(){

        ArrayList<SGEContentWrapper> toReturn = new ArrayList<>();
        toReturn.addAll(contentMap.values());
        return toReturn;
    }
    public ArrayList<SGEContentWrapper> getVisibleContent(){

        ArrayList<SGEContentWrapper> toReturn = new ArrayList<>();
        for(SGEContentWrapper w : contentMap.values()){
            if(w.isVisible()){
                toReturn.add(w);
            }
        }
        return toReturn;
    }
    public ArrayList<SGEContentWrapper> getByGrouping(String groupName){

        ArrayList<SGEContentWrapper> toReturn = new ArrayList<>();

        String groupingKey = buildContentGroupingKey(groupName);
        toReturn.addAll(groupAwareContentMap.get(groupingKey).values());
        return toReturn;
    }

    /*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~/
    * Private Helpers
    *
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    private String buildContentGroupingKey(SGEContentWrapper w){

        return buildContentGroupingKey(w.getContentGroup());
    }
    private String buildContentGroupingKey(String grouping){

        String key = grouping;

        if(!this.groupAwareContentMap.containsKey(key)){
            this.groupAwareContentMap.put(key, new HashMap<Integer, SGEContentWrapper>());
        }

        return key;
    }

    /*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~/
    * Pipeline
    *
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    void beforeDraw(){

        for(SGEContentWrapper w : this.contentMap.values()){
            w.beforeDraw();
        }
    }

    void afterDraw(){

        for(SGEContentWrapper w : this.contentMap.values()){
            w.afterDraw();
        }
    }

    /*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~/
    * Pipeline Contract
    *
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    private boolean contentFreezeInPlace = false;

    @Override
    public void contentFreeze(boolean isFrozen){
        contentFreezeInPlace = isFrozen;
    }
    @Override
    public void pause(boolean isPaused){
    }
    @Override
    public void deleteEverything(){
    }
}
