/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.android.foodHunter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import static android.graphics.Color.parseColor;


public class EventsAdapter extends ArrayAdapter<Event> {
    NyushEventsActivity location = new NyushEventsActivity();


    private static final String LOCATION_SEPARATOR = " of ";

    /**
     * Constructs a new {@link EventsAdapter}.
     *
     * @param context of the app
     * @param events is the list of events, which is the data source of the adapter
     */
    public EventsAdapter(Context context, List<Event> events) {
        super(context, 0, events);
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Check if there is an existing list item view (called convertView) that we can reuse,
        // otherwise, if convertView is null, then inflate a new list item layout.
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.event_list_item, parent, false);
        }


        Event currentEvent = getItem(position);

        // Find the TextView with corresponding id
        TextView magnitudeView = (TextView) listItemView.findViewById(R.id.magnitude);

        String formattedMagnitude = currentEvent.getEventTitle();

        magnitudeView.setText(formattedMagnitude);


        String[] food = {"meal", "pizza", "refreshment", "cookie", "beverage", "tea ",
                "tea.", "tea!", "sandwich", "fruit", "food", "lunch", "breakfast", "snack", "dinner"};
        String originalLocation = currentEvent.getDescription();


        // Find the TextView with view ID location
        TextView primaryLocationView = (TextView) listItemView.findViewById(R.id.primary_location);



        for (int i = 0; i < food.length; i++){
            if(originalLocation.toLowerCase().contains(food[i])){
                primaryLocationView.setTextColor(parseColor("#ff99cc00"));
                magnitudeView.setTextColor(parseColor("#ff99cc00"));
                break;
            }
            else {
                primaryLocationView.setTextColor(parseColor("#EDE7F6"));
                magnitudeView.setTextColor(parseColor("#EDE7F6"));

            }
        }

        // Display the location of the current earthquake in that TextView
        primaryLocationView.setText(originalLocation);


        String myDate = currentEvent.getmStartTime();
        String myDate2 = currentEvent.getmEndTime();


        // Find the TextView with view ID date
        TextView dateView = (TextView) listItemView.findViewById(R.id.date);

        try {
            Date dateObj = formatter(myDate);
            Date dateObj2 = formatter(myDate2);
            myDate = formatDate(dateObj);
            myDate2 = formatDate(dateObj2);

        } catch (ParseException e) {
            e.printStackTrace();
        }
        dateView.setText(myDate.substring(0, 10) + " " + myDate.substring(11, myDate.length() - 4) +
                " to " + myDate2.substring(11, myDate2.length() - 4));
        // Format the date string (i.e. "Mar 3, 1984")


        // Find the TextView with view ID time
        //TextView timeView = (TextView) listItemView.findViewById(R.id.time);
        // Format the time string (i.e. "4:30PM")



        // Return the list item view that is now showing the appropriate data
        return listItemView;
    }





    /**
     * Return the formatted date string (i.e. "Mar 3, 1984") from a Date object.
     */
    private String formatDate(Date dateObject) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd'T'HH:mm:ss'Z'");
        return dateFormat.format(dateObject);
    }

//format date from string and change it into a date object
    public Date formatter(String time) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");

        //check if location is Shanghai. If not, use NY time
    if (LocationState.location == 1){
        sdf.setTimeZone(TimeZone.getTimeZone("Australia/Sydney"));
    }
    else {
        sdf.setTimeZone(TimeZone.getTimeZone("GMT-2"));
    }
        return sdf.parse(time);
    }

    /**
     * Return the formatted date string (i.e. "4:30 PM") from a Date object.
     */
    private String formatTime(Date dateObject) {
        SimpleDateFormat timeFormat = new SimpleDateFormat("h:mm a");
        return timeFormat.format(dateObject);
    }

}
