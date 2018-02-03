//package com.example.android.foodHunter;
//
///**
// * Created by Hoyoung Jun on 8/11/2017.
// */
//import android.content.Context;
//import android.support.v4.content.ContextCompat;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ArrayAdapter;
//import android.widget.TextView;
//
//import java.text.ParseException;
//import java.text.SimpleDateFormat;
//import java.util.Date;
//import java.util.List;
//import java.util.TimeZone;
//
//import static android.graphics.Color.YELLOW;
//import static android.graphics.Color.parseColor;
//
//public class FilteredAdapter extends ArrayAdapter<Event>{
//    /**
//     * The part of the location string from the USGS service that we use to determine
//     * whether or not there is a location offset present ("5km N of Cairo, Egypt").
//     */
//    private static final String LOCATION_SEPARATOR = " of ";
//
//    /**
//     * Constructs a new {@link FilteredAdapter}.
//     *  @param context of the app
//     * @param earthquakes is the list of earthquakes, which is the data source of the adapter
//     */
//    public FilteredAdapter(Context context, List<Event> earthquakes) {
//        super(context, 0, earthquakes);
//    }
//
//    /**
//     * Returns a list item view that displays information about the earthquake at the given position
//     * in the list of earthquakes.
//     */
//    @Override
//    public View getView(int position, View convertView, ViewGroup parent) {
//        // Check if there is an existing list item view (called convertView) that we can reuse,
//        // otherwise, if convertView is null, then inflate a new list item layout.
//        View listItemView = convertView;
//        if (listItemView == null) {
//            listItemView = LayoutInflater.from(getContext()).inflate(
//                    R.layout.event_list_item, parent, false);
//        }
//
//
//        // Find the earthquake at the given position in the list of earthquakes
//        Event currentEarthquake = getItem(position);
//
//        // Find the TextView with view ID magnitude
//        TextView magnitudeView = (TextView) listItemView.findViewById(R.id.magnitude);
//        // Format the magnitude to show 1 decimal place
//        String formattedMagnitude = currentEarthquake.getEventTitle();
//        // Display the magnitude of the current earthquake in that TextView
//
//
//        // Set the proper background color on the magnitude circle.
//        // Fetch the background from the TextView, which is a GradientDrawable.
//
//
//        // Get the original location string from the Event object,
//        // which can be in the format of "5km N of Cairo, Egypt" or "Pacific-Antarctic Ridge".
//        String[] food = {"meal", "pizza", "refreshment", "cookie", "beverage", "tea ",
//                "tea.", "tea!", "sandwich", "fruit", "food", "lunch", "breakfast", "snack", "dinner"};
//        String originalLocation = currentEarthquake.getDescription();
//
//
//
//        // If the original location string (i.e. "5km N of Cairo, Egypt") contains
//        // a primary location (Cairo, Egypt) and a location offset (5km N of that city)
//        // then store the primary location separately from the location offset in 2 Strings,
//        // so they can be displayed in 2 TextViews.
//
//
//        // Find the TextView with view ID location
//        TextView primaryLocationView = (TextView) listItemView.findViewById(R.id.primary_location);
//
//
//
//
//
//        // Display the location of the current earthquake in that TextView
//
//
//        // Find the TextView with view ID location offset
//
//
//
//        String myDate = currentEarthquake.getmStartTime();
//        String myDate2 = currentEarthquake.getmEndTime();
//
//
//        // Find the TextView with view ID date
//        TextView dateView = (TextView) listItemView.findViewById(R.id.date);
//
//
//        // Find the TextView with view ID date
//        // TextView dateView = (TextView) listItemView.findViewById(R.id.date);
//        try {
//            Date dateObj = formatter(myDate);
//            Date dateObj2 = formatter(myDate2);
//            myDate = formatDate(dateObj);
//            myDate2 = formatDate(dateObj2);
//
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//        magnitudeView.setText(formattedMagnitude);
//        primaryLocationView.setText(originalLocation);
//        dateView.setText(myDate.substring(0, 10) + " " + myDate.substring(11, myDate.length() - 4) +
//                "\nto " + myDate2.substring(11, myDate2.length() - 4));
//        // Format the date string (i.e. "Mar 3, 1984")
//
//        for (int i = 0; i < food.length; i++){
//            if(originalLocation.toLowerCase().contains(food[i])){
//                primaryLocationView.setBackgroundColor(YELLOW);
//
//                break;
//            }
//            else {
//                if (i == food.length - 1){
//
//                    primaryLocationView.setVisibility(View.GONE);
//                    magnitudeView.setVisibility(View.GONE);
//                    dateView.setVisibility(View.GONE);
//                }
//            }
//
//
//        }
//        // Return the list item view that is now showing the appropriate data
//        return listItemView;
//    }
//
//
//    /**
//     * Return the formatted date string (i.e. "Mar 3, 1984") from a Date object.
//     */
//    private String formatDate(Date dateObject) {
//        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd'T'HH:mm:ss'Z'");
//        return dateFormat.format(dateObject);
//    }
//
//    public Date formatter(String time) throws ParseException {
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
//        sdf.setTimeZone(TimeZone.getTimeZone("Australia/Sydney"));
//        return sdf.parse(time);
//    }
//
//    /**
//     * Return the formatted date string (i.e. "4:30 PM") from a Date object.
//     */
//    private String formatTime(Date dateObject) {
//        SimpleDateFormat timeFormat = new SimpleDateFormat("h:mm a");
//        return timeFormat.format(dateObject);
//    }
//}

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

/**
 * An {@link EventsAdapter} knows how to create a list item layout for each earthquake
 * in the data source (a list of {@link Event} objects).
 *
 * These list item layouts will be provided to an adapter view like ListView
 * to be displayed to the user.
 */
public class FilteredAdapter extends ArrayAdapter<Event> {

    /**
     * The part of the location string from the USGS service that we use to determine
     * whether or not there is a location offset present ("5km N of Cairo, Egypt").
     */
    private static final String LOCATION_SEPARATOR = " of ";
    SelectionMenu location = new SelectionMenu();

    /**
     * Constructs a new {@link EventsAdapter}.
     *
     * @param context of the app
     * @param events is the list of events, which is the data source of the adapter
     */
    public FilteredAdapter(Context context, List<Event> events) {
        super(context, 0, events);
    }

    /**
     * Returns a list item view that displays information about the earthquake at the given position
     * in the list of earthquakes.
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Check if there is an existing list item view (called convertView) that we can reuse,
        // otherwise, if convertView is null, then inflate a new list item layout.
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.event_list_item, parent, false);
        }

        // Find the earthquake at the given position in the list of earthquakes
        Event currentEvent = getItem(position);

        // Find the TextView with view ID magnitude
        TextView magnitudeView = (TextView) listItemView.findViewById(R.id.magnitude);
        // Format the magnitude to show 1 decimal place
        String formattedMagnitude = currentEvent.getEventTitle();
        // Display the magnitude of the current earthquake in that TextView
        magnitudeView.setText(formattedMagnitude);

        // Set the proper background color on the magnitude circle.
        // Fetch the background from the TextView, which is a GradientDrawable.


        // Get the original location string from the Event object,
        // which can be in the format of "5km N of Cairo, Egypt" or "Pacific-Antarctic Ridge".
        String[] food = {"meal", "pizza", "refreshment", "cookie", "beverage", "tea ",
                "tea.", "tea!", "sandwich", "fruit", "food", "lunch", "breakfast", "snack", "dinner"};
        String originalLocation = currentEvent.getDescription();



        // If the original location string (i.e. "5km N of Cairo, Egypt") contains
        // a primary location (Cairo, Egypt) and a location offset (5km N of that city)
        // then store the primary location separately from the location offset in 2 Strings,
        // so they can be displayed in 2 TextViews.


        // Find the TextView with view ID location
        TextView primaryLocationView = (TextView) listItemView.findViewById(R.id.primary_location);



        for (int i = 0; i < food.length; i++){
            if(originalLocation.toLowerCase().contains(food[i])){
                magnitudeView.setTextColor(parseColor("#ff99cc00"));
                primaryLocationView.setTextColor(parseColor("#ff99cc00"));
                break;
            }
            else {
                primaryLocationView.setTextColor(parseColor("#EDE7F6"));
                magnitudeView.setTextColor(parseColor("#EDE7F6"));
            }
        }

        // Display the location of the current earthquake in that TextView
        primaryLocationView.setText(originalLocation);

        // Find the TextView with view ID location offset


        // Create a new Date object from the time in milliseconds of the earthquake
        //  String date = currentEvent.getmStartTime();
        String myDate = currentEvent.getmStartTime();
        String myDate2 = currentEvent.getmEndTime();
        //SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ", Locale.US);
        //  Date  date = sdf.parse(myDate);

        // long millis = date.getTime();
        // Date dateObject = new Date(currentEvent.getmStartTime());

        // Find the TextView with view ID date
        TextView dateView = (TextView) listItemView.findViewById(R.id.date);
        // Format the date string (i.e. "Mar 3, 1984")
        //String formattedDate = formatDate(date);
        // Display the date of the current earthquake in that TextView
        //dateView.setText(formattedDate);

        // Find the TextView with view ID date
        // TextView dateView = (TextView) listItemView.findViewById(R.id.date);
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


        //find background color of the description view and setVisibility gone if text color is not
        //yellow (description does not contain words in list)
        int colorCode = primaryLocationView.getCurrentTextColor();



       listItemView.findViewById(R.id.itemview);

        View line = listItemView.findViewById(R.id.line);

        if (colorCode != parseColor("#ff99cc00")){
            //set visibility of the layout and line to gone
            line.setVisibility(View.GONE);
            listItemView.findViewById(R.id.itemview).setVisibility(View.GONE);
        }


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

    public Date formatter(String time) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        //check if location is Shanghai. If not, use NY time
        if (LocationState.location == 1){
            sdf.setTimeZone(TimeZone.getTimeZone("Australia/Sydney"));
        }
        else {
            sdf.setTimeZone(TimeZone.getTimeZone("America/Sao_Paulo"));
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
