package com.cdaniel.sgeexamples.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.cdaniel.sgeexamples.R;
import com.cdaniel.sgeexamples.examples.exampleview.ExampleRenderer;
import com.cdaniel.sgeexamples.examples.exampleview.ExampleView;
import com.cdaniel.sgeexamples.examples.manager.ExampleManager;
import com.cdaniel.simplegameviews.inputcontrols.JoystickSimple;

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

        attachListeners();
    }

    /* ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    * Other Android Stuff
    *
    * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    private void attachListeners() {}

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }
}
