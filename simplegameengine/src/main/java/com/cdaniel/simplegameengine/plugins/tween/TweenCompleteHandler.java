package com.cdaniel.simplegameengine.plugins.tween;

import com.cdaniel.simplegameengine.core.HasProperties;

/**
 * Created by christopher.daniel on 5/4/16.
 */
public interface TweenCompleteHandler extends HasProperties {

    /* ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    * Contract
    *
    * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    void onTweenComplete(int tweenId);
}
