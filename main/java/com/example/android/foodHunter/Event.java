
package com.example.android.foodHunter;


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
