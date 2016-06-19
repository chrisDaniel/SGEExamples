package com.cdaniel.simplegameengine.plugins.tween.easers;

import com.cdaniel.simplegameengine.plugins.tween.TweenEase;

/**
 * Created by christopher.daniel on 5/11/16.
 */
public class Ease_Quadratic implements TweenEase {

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
     *   |          *
     *   |         *
     *   |        *
     *   |       *         eased = uneased^power (default 1.2)
     *   |      *
     *   |    *
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

    public Ease_Quadratic() {
        power = 3f;
        fullyEased = (float) Math.pow(100, power);
    }
    public Ease_Quadratic(float power) {
        this.power = power;
        fullyEased = (float) Math.pow(100, power);
    }

    /*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~/
    * Ease
    *
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    public float ease(float uneased){
        float in = uneased;

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
