package com.cdaniel.sgeexamples.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.cdaniel.sgeexamples.R;
import com.cdaniel.sgeexamples.examples.exampleview.ExampleRenderer;
import com.cdaniel.sgeexamples.examples.exampleview.ExampleView;

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

        attachListeners();
    }

    /* ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    * Other Android Stuff
    *
    * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    private void attachListeners(){

       /* FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab_solarsystem_planets);
        if(fab!=null){
            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Interaction_Handler.fabClick();
                }
            });
        }*/
    }
    @Override
    protected void onResume()
    {
        super.onResume();
        if(exampleView !=null) {
        }
    }

    @Override
    protected void onPause()
    {
        super.onPause();
        if(exampleView !=null) {
        }
    }
}
