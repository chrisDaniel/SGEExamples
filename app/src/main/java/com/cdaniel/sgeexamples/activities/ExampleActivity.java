package com.cdaniel.sgeexamples.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.cdaniel.sgeexamples.R;
import com.cdaniel.sgeexamples.examples.exampleview.ExampleRenderer;
import com.cdaniel.sgeexamples.examples.exampleview.ExampleView;
import com.cdaniel.sgeexamples.examples.manager.ExampleManager;
import com.cdaniel.simplegameviews.inputcontrols.FingerActivityArea;
import com.cdaniel.simplegameviews.inputcontrols.JoystickSimple;
import com.cdaniel.simplegameviews.inputcontrols.TwoButtons;

public class ExampleActivity extends AppCompatActivity {


    /* ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    * Reference to the GLSurface View
    *
    * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    private ExampleView exampleView;
    private ExampleRenderer exampleRenderer;


    /* ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    * Set up the GLSurfaceContent
    *
    * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_example);

        exampleView = (ExampleView) findViewById(R.id.example_gl_view);
        exampleRenderer = new ExampleRenderer();
        exampleView.setRenderer(exampleRenderer);

        JoystickSimple joystick = (JoystickSimple) findViewById(R.id.joystick);
        ExampleManager.getInstance().setJoystick(joystick);

        FingerActivityArea fingerArea = (FingerActivityArea) findViewById(R.id.fingerActivityArea);
        ExampleManager.getInstance().setFingerActivityArea(fingerArea);

        TwoButtons twoButtons = (TwoButtons) findViewById(R.id.twoButtons);
        ExampleManager.getInstance().setTwoButtons(twoButtons);
    }

    /* ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    * Other Android Stuff
    *
    * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }
}
