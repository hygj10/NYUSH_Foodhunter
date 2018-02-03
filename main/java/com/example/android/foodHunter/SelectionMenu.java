package com.example.android.foodHunter;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by Hoyoung Jun on 8/15/2017.
 * Select campus with your corresponding location to search for events.
 */

public class SelectionMenu extends AppCompatActivity {

    public int location_for_ny = 0;
    public int location_for_sh = 1;
    public int event_location;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.selection_menu);

        Button nyu_events = (Button) findViewById(R.id.nyu_events);

        //set on click listeners for the NYU New York Button

        nyu_events.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View view) {

                event_location = location_for_ny;
                //activate the NYU New York Activity for list of NYUNY events.

                Intent see_nyu_events = new Intent(SelectionMenu.this, NyunyEventsActivity.class);
                startActivity(see_nyu_events);

            }


        });

        Button nyush_events = (Button) findViewById(R.id.nyush_events);

        //set on click listener for the NYU Shanghai campus

        nyush_events.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View view) {

                event_location = location_for_sh;

                //activate the NYU Shanghai Activity for list of NYUSH events.

                Intent see_nyush_events = new Intent(SelectionMenu.this, NyushEventsActivity.class);
                startActivity(see_nyush_events);

            }


        });




    }


}
