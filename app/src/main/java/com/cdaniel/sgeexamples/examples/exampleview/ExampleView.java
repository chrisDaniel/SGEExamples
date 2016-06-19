package com.cdaniel.sgeexamples.examples.exampleview;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.util.AttributeSet;
import android.view.MotionEvent;

import com.cdaniel.sgeexamples.examples.manager.ExampleManager;

/**
 * Created by christopher.daniel on 4/10/16.
 */
public class ExampleView extends GLSurfaceView{


    /* ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    * Constructors
    *
    * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    public ExampleView(Context context){
        super(context);
        ExampleManager.getInstance().setContext(context);
        ExampleManager.getInstance().setView(this);
    }

    public ExampleView(Context context, AttributeSet attrs){
        super(context, attrs);
        ExampleManager.getInstance().setContext(context);
        ExampleManager.getInstance().setView(this);
    }



    /* ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    * Pass Events to the Manager
    *
    * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    @Override
    public boolean onTouchEvent(MotionEvent e) {

        ExampleManager.getInstance().interactHandler().handleTouch(e);
        return true;
    }

}
