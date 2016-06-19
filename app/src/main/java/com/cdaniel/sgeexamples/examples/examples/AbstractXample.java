package com.cdaniel.sgeexamples.examples.examples;

import com.cdaniel.simplegameengine.engine.SGE;
import com.cdaniel.simplegameengine.plugins.director.directors_focus.DIR_PanLeftRight;
import com.cdaniel.simplegameengine.plugins.director.directors_focus.DIR_PanUpDown;
import com.cdaniel.simplegameengine.utils.constants.Constants;

/**
 * Created by christopher.daniel on 6/18/16.
 */
public abstract class AbstractXample {


    /* ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    * The main Draw Method
    *
    * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    public abstract void onFrame();


    /* ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    * Handle User Actions
    *
    * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    public void handleUserAction_move(float dx, float dy){

        float full360 = 1200;
        float angle = Constants.PI * (-1 * dx / full360);
        if (angle < Constants.PI / -6f) {
            angle = -1 * Constants.PI / 6f;
        }
        if (angle > Constants.PI / 6f) {
            angle = Constants.PI / 6f;
        }

        SGE.director().queueDirector(DIR_PanLeftRight.builder().left(angle).build());

        float fullUpDown = 600;
        float delta = Constants.PI * (-1 * dy / fullUpDown);
        if (delta < Constants.PI / -6f) {
            delta = -1 * Constants.PI / 6f;
        }
        if (delta > Constants.PI / 6f) {
            delta = Constants.PI / 6f;
        }

        SGE.director().queueDirector(DIR_PanUpDown.builder().up(delta).build());
    }
}
