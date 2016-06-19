package com.cdaniel.simplegameengine.plugins.tween;

import com.cdaniel.simplegameengine.core.HasProperties;
import com.cdaniel.simplegameengine.engine.SGEContentWrapper;

/**
 * Created by christopher.daniel on 5/4/16.
 */
public interface Tween extends HasProperties {

    /* ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     * Tweens Properties
     *
     * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    String TWEEN_REVERSE = "Will the tween reverse upon completion...(take the content back to start)";
    String TWEEN_REVERSE_No  = "Will not reverse";
    String TWEEN_REVERSE_Yes = "Will reverse";

    String TWEEN_LOOP = "The Loop property ... integer number of loops (repeats) ... 0 is default";
    int TWEEN_LOOP_None                  = 0;
    int TWEEN_LOOP_Repeat_Infinite       = Integer.MAX_VALUE;

    String TWEEN_COMPLETE_ACT = "When the Tween completes, perform this action";
    String TWEEN_COMPLETE_ACT_none   = "No special action ... default";
    String TWEEN_COMPLETE_ACT_remove = "Remove the object when complete";

    /* ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    * Contract
    *
    * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    int getContentId();
    void attach(SGEContentWrapper wrapper);

    void setGlobalId(int id);
    int getGlobalId();
    void setPauseState(boolean pauseStatus);

    boolean executeTween();

}
