package com.cdaniel.simplegameengine.plugins.tween;

import com.cdaniel.simplegameengine.engine.SGEContentWrapper;

/**
 * Created by christopher.daniel on 5/19/16.
 */
public interface TweenEffect {

    /* ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    * Contract
    *
    * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/

    void attach(SGEContentWrapper wrapper);
    void playEffect();

}
