package com.cdaniel.simplegameengine.plugins.tween;

import com.cdaniel.simplegameengine.core.SimpleGamePlugin;
import com.cdaniel.simplegameengine.engine.SGE;
import com.cdaniel.simplegameengine.engine.SGEContentWrapper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by christopher.daniel on 5/7/16.
 */
public class SGPTweening implements SimpleGamePlugin {

    /*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~/
    * Construct and Variables
    *
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    static private int globalTweenId = 0;
    private Map<Integer, Tween> tweenIdMap;
    private Map<Integer, TweenEffect> effectIdMap;
    private ArrayList<DelayedTweenWrapper> delayedTweens;

    public SGPTweening(){

        tweenIdMap = new HashMap<>();
        effectIdMap = new HashMap<>();
        delayedTweens = new ArrayList<>();
    }


    /*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~/
    * Tween Effect Collections
    *
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    public int tweenEffect(int contentId, TweenEffect e){

        SGEContentWrapper w = SGE.contents().get(contentId);
        if(e == null || w == null){
            return -1;
        }

        e.attach(w);

        globalTweenId++;
        effectIdMap.put(globalTweenId, e);

        return globalTweenId;
    }


    /*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~/
    * Tween Application / Management
    *
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    public int applyDelayedTween(int contentId, Tween t, int delay) {

        SGEContentWrapper w = SGE.contents().get(contentId);
        if(t == null || w == null){
            return -1;
        }

        globalTweenId++;
        t.setGlobalId(globalTweenId);

        DelayedTweenWrapper dtw = new DelayedTweenWrapper(contentId, t, globalTweenId, delay);
        this.delayedTweens.add(dtw);
        return globalTweenId;
    }
    public int applyTween(int contentId, Tween t) {

        SGEContentWrapper w = SGE.contents().get(contentId);
        if(t == null || w == null){
            return -1;
        }

        globalTweenId++;
        t.setGlobalId(globalTweenId);

        actuallyApplyTween(contentId, t, globalTweenId);
        return globalTweenId;
    }
    private void actuallyApplyTween(int contentId, Tween t, int tweenId){

        SGEContentWrapper w = SGE.contents().get(contentId);
        if(w==null){
            return;
        }
        t.attach(w);
        tweenIdMap.put(t.getGlobalId(), t);
    }
    public boolean isTweenActive(int contentId){
        return tweenIdMap.containsKey(contentId);
    }
    public void pauseTween(int tweenId){

        tweenIdMap.get(tweenId).setPauseState(true);
    }
    public void unpauseTween(int tweenId){

        tweenIdMap.get(tweenId).setPauseState(false);
    }
    public void killTween(int tweenId){

        tweenIdMap.remove(tweenId);
    }
    public void killAll(){
        tweenIdMap.clear();
        effectIdMap.clear();
    }
    public void killTweenForContent(int contentId){

        ArrayList<Integer> toKill = new ArrayList<>();
        for(Integer i : this.tweenIdMap.keySet()){
            if(this.tweenIdMap.get(i).getContentId() == contentId){
                toKill.add(i);
            }
        }

        for(Integer i : toKill){
            killTween(i);
        }
    }


    /*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~/
    * Plugin Contract
    *
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    private ArrayList<Integer> toRemove = new ArrayList<>();
    private ArrayList<DelayedTweenWrapper> stillDelayed = new ArrayList<>();

    private boolean contentFreezeInPlace = false;

    @Override
    public void beforeDraw() {

        if(contentFreezeInPlace){
            return;
        }

        for (int tweenId : tweenIdMap.keySet()){

            if (!tweenIdMap.get(tweenId).executeTween()) {
                toRemove.add(tweenId);
            }
        }
        for (int rId : toRemove){
            tweenIdMap.remove(rId);
        }
        toRemove.clear();

        for (int effectId : effectIdMap.keySet()){
            effectIdMap.get(effectId).playEffect();
        }
        effectIdMap.clear();
    }
    @Override
    public void afterDraw(){

        if(contentFreezeInPlace){
            return;
        }
        if(delayedTweens.isEmpty()){
            return;
        }

        for(DelayedTweenWrapper dtw : delayedTweens){

            if(dtw.reduceDelay()){
                actuallyApplyTween(dtw.contentId, dtw.tween, dtw.tweenId);
            }
            else{
                stillDelayed.add(dtw);
            }
        }

        this.delayedTweens.clear();
        this.delayedTweens.addAll(stillDelayed);
        this.stillDelayed.clear();
    }
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


    /*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~/
    * Delayed Tween Pergatory
    *
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    private class DelayedTweenWrapper{

        final int contentId;
        final Tween tween;
        final int tweenId;
        int delayLeft;

        DelayedTweenWrapper(int contentId, Tween t, int tweenId, int delay){
            this.contentId = contentId;
            this.tween = t;
            this.tweenId = tweenId;
            this.delayLeft = delay;
        }

        boolean reduceDelay(){

            delayLeft--;

            if(delayLeft == 0) {
                return true;
            }
            return false;
        }
    }
}
