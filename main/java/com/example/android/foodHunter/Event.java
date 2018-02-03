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

/**
 * An {@link Event} object contains information related to a single earthquake.
 */
public class Event {

    /** Title of the event */
    private String mEventTitle;

    /** Location of the event */
    private String mRoomNumber;

    /** Description of the event */
    private String mDescription;

    /** Start time of the event */
    private String mStartTime;

    private String mEndTime;

    /** Website URL of the event RSVP page at OrgSync */
    private String mUrl;

    private String mImage;
    /**
     * Constructs a new {@link Event} object.
     *
     * @param roomNumber is the classroom where the event takes place at
     * @param description is the event description and the food available
     * @param startTime is the time when the
     *                           event starts
     * @param url is the website URL to find more details about the event
     */
    public Event(String eventTitle, String roomNumber, String description, String startTime,
                 String endTime, String url, String image_url) {
        mEventTitle = eventTitle.trim();
        mRoomNumber = roomNumber.trim();
        mDescription = description.trim();
        mStartTime = startTime;//
        mEndTime = endTime;//.substring(11, endTime.length() - 4);
        mUrl = url;
        mImage = image_url;
    }

    /**
     * Returns the title of the event.
     */
    public String getEventTitle(){return mEventTitle; }

    /**
     * Returns the location of the event.
     */
    public String getRoomNumber() {
        return mRoomNumber;
    }

    /**
     * Returns the description of the event.
     */
    public String getDescription() {
        return mDescription;
    }

    /**
     * Returns the start time of the event.
     */
    public String getmStartTime() {
        return mStartTime;
    }

    /**
     * Returns the website URL for the rsvp page of the event.
     */
    public String getUrl() {
        return mUrl;
    }


    public String getmImage() {return mImage;}


    public String getmEndTime() {return mEndTime; }
}
