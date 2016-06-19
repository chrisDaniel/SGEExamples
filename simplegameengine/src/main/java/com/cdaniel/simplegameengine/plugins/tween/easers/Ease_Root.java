package com.cdaniel.simplegameengine.plugins.tween.easers;

import com.cdaniel.simplegameengine.plugins.tween.TweenEase;

/**
 * Created by christopher.daniel on 5/11/16.
 */
public class Ease_Root implements TweenEase {

    /***********************************************
     * Uneased (linear) - xAxis uneased yAxis - eased
     *
     *   |             *
     *   |           *
     *   |         *
     *   |       *         eased = uneased
     *   |     *
     *   |   *
     *   | *
     *   +-------------------
     *
     *
     ******************************************************
     ******************************************************
     * * Eased (quad) - xAxis uneased yAxis - eased
     *
     *   |            *   *
     *   |        *
     *   |     *
     *   |   *             eased = uneased^(1/power)
     *   |  *
     *   | *
     *   | *
     *   +-------------------
     *
     *
     ******************************************************
     ******************************************************/


    /*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~/
    * Variables / Constructs
    *
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    private float fullyEased;
    private float power;

    public Ease_Root() {
        power = 1/3f;
        fullyEased = (float) Math.pow(100, power);
    }
    public Ease_Root(float power) {
        this.power = 1/power;
        fullyEased = (float) Math.pow(100, power);
    }

    /*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~/
    * Ease
    *
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    public float ease(float uneased){

        if(uneased < 1){
            uneased = 100 * uneased;
        }
        else if(uneased >= .99){
            return 1f;
        }

        double eased = (float) Math.pow(uneased, power);
        double easedPercent = eased / fullyEased;

        if(eased > fullyEased){
            return 1f;
        }
        return (float) easedPercent;
    }
}
