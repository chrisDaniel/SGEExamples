package com.cdaniel.sgeexamples.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.cdaniel.sgeexamples.R;
import com.cdaniel.sgeexamples.examples.manager.ExampleManager;

public class MainActivity extends AppCompatActivity {

    /* ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    * Create
    *
    * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setupTriggers();
    }

    /* ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    * Setup Listeners to the Example Triggers
    *
    * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    private void setupTriggers(){

        Button ex1_1 = (Button) findViewById(R.id.trigger_ex1_1);
        setupTriggers_event(ex1_1, ExampleManager.EX1_1);

        Button ex2_1 = (Button) findViewById(R.id.trigger_ex2_1);
        setupTriggers_event(ex2_1, ExampleManager.EX2_1);

        Button ex3_1 = (Button) findViewById(R.id.trigger_ex3_1);
        setupTriggers_event(ex3_1, ExampleManager.EX3_1);

        Button ex3_2 = (Button) findViewById(R.id.trigger_ex3_2);
        setupTriggers_event(ex3_2, ExampleManager.EX3_2);

        Button ex4_1 = (Button) findViewById(R.id.trigger_ex4_1);
        setupTriggers_event(ex4_1, ExampleManager.EX4_1);

        Button ex5_1 = (Button) findViewById(R.id.trigger_ex5_1);
        setupTriggers_event(ex5_1, ExampleManager.EX5_1);
    }
    private void setupTriggers_event(Button trigger, final int exampleId){

        trigger.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                launchExampleIntent(exampleId);
            }
        });
    }
    private void launchExampleIntent(int exampleId){

        ExampleManager.getInstance().activateExample(exampleId);
        final Intent launchIntent = new Intent(this, ExampleActivity.class);
        startActivity(launchIntent);
    }
}
