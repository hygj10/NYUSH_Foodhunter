package com.example.android.foodHunter;

import android.app.LoaderManager;
import android.content.Context;
import android.content.Intent;
import android.content.Loader;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Hoyoung Jun on 8/15/2017.
 * Events list for the NYU Shanghai Campus
 */

public class NyushEventsActivity
     extends AppCompatActivity
        implements LoaderManager.LoaderCallbacks<List<Event>>

    {


        private static final String LOG_TAG = NyunyEventsActivity.class.getName();
        /** URL for NYU Shanghai events in OrgSync */
        private static final String USGS_REQUEST_URL =
                "https://api.orgsync.com/api/v3/communities/719/events?key=loD27Kbj2xs-EnGPKh6XRHViW" +
                        "QA7Imq_bJqWfYVHWUk&upcoming=true&page=1&per_page=100&after=2017-08-16T00%3A03" +
                        "%3A42.479Z&before=2019-08-16T00%3A03%3A42.479Z&include_opportunities=true";

        private static final int EARTHQUAKE_LOADER_ID = 1;
        int index;
        int top;
        boolean filtercondition;
        // Adapter for the list of events
        private EventsAdapter mAdapter;
        // TextView that is displayed when the list is empty
        private TextView mEmptyStateTextView;
        //create adapter for filtered listview
        private FilteredAdapter filteredAdapter;



// ...

// restore index and position

        @Override
        protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.event_acticity);

            //set location as 1 if SH
            LocationState.location = 1;
        // Find a reference to the {@link ListView} in the layout
        final ListView earthquakeListView = (ListView) findViewById(R.id.list);

        //bring in boolean value for previous filtercondition value
        try{
            filtercondition = savedInstanceState.getBoolean("filtercondition");}
        catch (NullPointerException e){
            e.printStackTrace();
        }

        onCheckboxClicked(findViewById(R.id.filter));

        //if box is checked used the filter adapter, if not the event adapter.
        if (filtercondition){


            mEmptyStateTextView = (TextView) findViewById(R.id.empty_view);
            earthquakeListView.setEmptyView(mEmptyStateTextView);

            // Create a new adapter that takes an empty list of events as input

            filteredAdapter = new FilteredAdapter(this, new ArrayList<Event>());



            earthquakeListView.setAdapter(filteredAdapter);

            earthquakeListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                    //save scroll position of ListView
                    index = earthquakeListView.getFirstVisiblePosition();
                    View v = earthquakeListView.getChildAt(0);
                    top = (v == null) ? 0 : (v.getTop() - earthquakeListView.getPaddingTop());

                    // Find the current event that was clicked on
                    Event currentEvent = filteredAdapter.getItem(position);



                    Intent i = new Intent(NyushEventsActivity.this , Description.class);
                    i.putExtra("Title", currentEvent.getEventTitle());
                    i.putExtra("Description", currentEvent.getDescription());
                    i.putExtra("Time", currentEvent.getmStartTime());
                    i.putExtra("Time2", currentEvent.getmEndTime());
                    i.putExtra("Url", currentEvent.getUrl());
                    i.putExtra("Image", currentEvent.getmImage());
                    startActivity(i);


                }
            });
        }
        else {
            mEmptyStateTextView = (TextView) findViewById(R.id.empty_view);
            earthquakeListView.setEmptyView(mEmptyStateTextView);

            // Create a new adapter that takes an empty list of events as input
            mAdapter = new EventsAdapter(this, new ArrayList<Event>());


            earthquakeListView.setAdapter(mAdapter);

            earthquakeListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                    //save position of ListView
                    index = earthquakeListView.getFirstVisiblePosition();
                    View v = earthquakeListView.getChildAt(0);
                    top = (v == null) ? 0 : (v.getTop() - earthquakeListView.getPaddingTop());

                    // Find the current eavent that was clicked on
                    Event currentEvent = mAdapter.getItem(position);



                    Intent i = new Intent(NyushEventsActivity.this , Description.class);
                    i.putExtra("Title", currentEvent.getEventTitle());
                    i.putExtra("Description", currentEvent.getDescription());
                    i.putExtra("Time", currentEvent.getmStartTime());
                    i.putExtra("Time2", currentEvent.getmEndTime());
                    i.putExtra("Url", currentEvent.getUrl());
                    i.putExtra("Image", currentEvent.getmImage());
                    startActivity(i);


                }
            });
        }







        // Get a reference to the ConnectivityManager to check state of network connectivity
        ConnectivityManager connMgr = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);

        // Get details on the currently active default data network
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

        // If there is a network connection, fetch data
        if (networkInfo != null && networkInfo.isConnected()) {
            // Get a reference to the LoaderManager, in order to interact with loaders.
            LoaderManager loaderManager = getLoaderManager();

            // Initialize the loader. Pass in the int ID constant defined above and pass in null for
            // the bundle. Pass in this activity for the LoaderCallbacks parameter (which is valid
            // because this activity implements the LoaderCallbacks interface).
            loaderManager.initLoader(EARTHQUAKE_LOADER_ID, null, this);
        } else {
            // Otherwise, display error
            // First, hide loading indicator so error message will be visible
            View loadingIndicator = findViewById(R.id.loading_indicator);
            loadingIndicator.setVisibility(View.GONE);

            // Update empty state with no connection error message
            mEmptyStateTextView.setText(R.string.no_internet_connection);
        }

    }

        //Save boolean condition for checked sign so that boolean filtercondition is saved even after recreation
        @Override
        public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putBoolean("filtercondition", filtercondition);
    }

    public void onCheckboxClicked(View view) {
        // Is the view now checked?

        CheckBox isFiltered = (CheckBox) view;
        isFiltered.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View view) {

                if (filtercondition){
                    filtercondition = false;}
                else {filtercondition = true;}

                recreate();

            }


        });

    }


    @Override
    public Loader<List<Event>> onCreateLoader(int i, Bundle bundle) {
        // Create a new loader for the given URL
        return new EventLoader(this, USGS_REQUEST_URL);
    }

    @Override
    public void onLoadFinished(Loader<List<Event>> loader, List<Event> events) {
        // Hide loading indicator because the data has been loaded
        View loadingIndicator = findViewById(R.id.loading_indicator);
        loadingIndicator.setVisibility(View.GONE);

        // Set empty state text to display "No events found."
        mEmptyStateTextView.setText(R.string.no_events);

        // Clear the adapter of previous data
        //First check which adapter has been used
        if (filtercondition) {
            filteredAdapter.clear();
        }
        else {mAdapter.clear();}
        // If there is a valid list of {@link Event}s, then add them to the adapter's
        // data set. This will trigger the ListView to update.
        if (events != null && !events.isEmpty()) {

            if (filtercondition){
                filteredAdapter.addAll(events);}
            else {mAdapter.addAll(events);}
        }


        //call and scroll back to saved position of listview
        getListView().setSelectionFromTop(index, top);

    }

    @Override
    public void onLoaderReset(Loader<List<Event>> loader) {
        // Loader reset, so we can clear out our existing data.

        if (filtercondition) {
            filteredAdapter.clear();
        }
        else {mAdapter.clear();}
    }

    public ListView getListView() {return (ListView) findViewById(R.id.list);}

    public EventsAdapter getmAdapter(){
        return mAdapter;
    }

    public FilteredAdapter getFilteredAdapter() {return filteredAdapter; }


}

