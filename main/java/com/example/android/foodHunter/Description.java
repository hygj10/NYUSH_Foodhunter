package com.example.android.foodHunter;


import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.LabeledIntent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.telephony.SmsManager;
import android.text.Html;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import static java.security.AccessController.getContext;

/**
 * Created by Hoyoung Jun on 8/7/2017.
 */

public class Description extends AppCompatActivity{
    String url;
    String title;
    String description;
    String time;
    String time2;
    String image;
    ArrayList used_words;


    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.description);

        String[] food = {"pizza", "refreshment", "cookie", "beverage", "tea ",
                "tea.", "tea!", "sandwich", "fruit", "meal", "food", "lunch", "breakfast", "snack", "dinner"};
        Intent i = getIntent();
        description = i.getExtras().getString("Description");
        title = i.getExtras().getString("Title");
        time = i.getExtras().getString("Time");
        time2 = i.getExtras().getString("Time2");
        image = i.getExtras().getString("Image");

        ImageView imageView = (ImageView) findViewById(R.id.event_image) ;
        URL image_url = null;
        try {
            image_url = new URL(image);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        //upload image of the given url (event image)
        Picasso.with(this).load(image).into(imageView);

        try {
            Date time_ = formatter(time);
            Date time_2 = formatter(time2);
            time = formatDate(time_);
            time2 = formatDate(time_2);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        String rsvpUrl = i.getExtras().getString("Url");
        url = rsvpUrl;

//        String gcurl = i.getExtras().getString("Gcal");
//        gc = gcurl;

        TextView titleView = (TextView) findViewById(R.id.title_text);
        titleView.setText(title);

        TextView descriptionView = (TextView) findViewById(R.id.description_text);
        descriptionView.setText(description + "\n\n");
        for (int w = 0; w < food.length; w++){

        }

        TextView timeView = (TextView) findViewById(R.id.time_text);
        timeView.setText(time.substring(0, 10) + " " + time.substring(11, time.length() - 4) +" to " + time2.substring(11, time.length() - 4) );

        int used_counter = 0;
        used_words = new ArrayList();
        String[] parts = description.split(" ");
        for (int j = 0; j < parts.length; j++){
            for (int k = 0; k < food.length; k++)
                if (parts[j].toLowerCase().contains(food[k]) && !Arrays.asList(used_words).contains(food[k])){
                    descriptionView.append("* " + parts[j] + "\n");
                    //use used words arraylist to not display same food more than once on screen.
                    used_words.add(food[k]);
                    used_counter++;
                    ;
                }
        }
//        Arrays.fill(used_words, null);
//        used_counter = 0;


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }

    public void rsvp(View view) {
        Uri rsvpUri = Uri.parse(url);
        Intent websiteIntent = new Intent(Intent.ACTION_VIEW, rsvpUri);
        startActivity(websiteIntent);
        }

    public void send_to(View view) {
//        SmsManager smsManager = SmsManager.getDefault();
//        smsManager.sendTextMessage("phoneNo", null, "sms message", null, null);
//
//        Intent sendIntent = new Intent(Intent.ACTION_VIEW);
//        sendIntent.putExtra("sms_body",title + "\n" + used_words + "\n"+ time.substring(0, 10) + " " +
//                time.substring(11, time.length() - 4) +" to " + time2.substring(11, time.length() - 4) );
//        sendIntent.setType("vnd.android-dir/mms-sms");
//        startActivity(sendIntent);
        Resources resources = getResources();

        Intent emailIntent = new Intent();
        emailIntent.setAction(Intent.ACTION_SEND);
        // Native email client doesn't currently support HTML, but it doesn't hurt to try in case they fix it
        emailIntent.putExtra(Intent.EXTRA_TEXT, title + "\n" + used_words + "\n"+ time.substring(0, 10) + " " +
                time.substring(11, time.length() - 4) +" to " + time2.substring(11, time.length() - 4));
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, title + "\n" + used_words + "\n"+ time.substring(0, 10) + " " +
                time.substring(11, time.length() - 4) +" to " + time2.substring(11, time.length() - 4));
        emailIntent.setType("message/rfc822");

        PackageManager pm = getPackageManager();
        Intent sendIntent = new Intent(Intent.ACTION_SEND);
        sendIntent.setType("text/plain");


        Intent openInChooser = Intent.createChooser(emailIntent, title);

        List<ResolveInfo> resInfo = pm.queryIntentActivities(sendIntent, 0);
        List<LabeledIntent> intentList = new ArrayList<LabeledIntent>();
        for (int i = 0; i < resInfo.size(); i++) {
            // Extract the label, append it, and repackage it in a LabeledIntent
            ResolveInfo ri = resInfo.get(i);
            String packageName = ri.activityInfo.packageName;
            //all the popular social media apps  that can be used to forward events to friends.
            if(packageName.contains("android.email")) {
                emailIntent.setPackage(packageName);
            } else if(packageName.contains("twitter") || packageName.contains("facebook") || packageName.contains("mms")
                    || packageName.contains("android.gm") || packageName.contains("com.tencent.mm") ||
                    packageName.contains("com.whatsapp")  || packageName.contains( "naver.line.android")
                    || packageName.contains( "com.facebook.orca") || packageName.contains( "com.kakao.talk")
                    || packageName.contains("com.viber.voip")) {
                Intent intent = new Intent();
                intent.setComponent(new ComponentName(packageName, ri.activityInfo.name));
                intent.setAction(Intent.ACTION_SEND);
                intent.setType("text/plain");
                if(packageName.contains("twitter")) {
                    intent.putExtra(Intent.EXTRA_TEXT, title + "\n" + used_words + "\n"+ time.substring(0, 10) + " " +
                time.substring(11, time.length() - 4) +" to " + time2.substring(11, time.length() - 4)
                            + "\nClick here to RSVP: " + url);
                } else if(packageName.contains("facebook")) {
                    // Warning: Facebook IGNORES our text. They say "These fields are intended for users to express themselves. Pre-filling these fields erodes the authenticity of the user voice."
                    // One workaround is to use the Facebook SDK to post, but that doesn't allow the user to choose how they want to share. We can also make a custom landing page, and the link
                    // will show the <meta content ="..."> text from that page with our link in Facebook.
                    intent.putExtra(Intent.EXTRA_TEXT, title + "\n" + used_words + "\n"+ time.substring(0, 10) + " " +
                time.substring(11, time.length() - 4) +" to " + time2.substring(11, time.length() - 4)
                            + "\nClick here to RSVP: " + url);
                } else if(packageName.contains("mms")) {
                    intent.putExtra(Intent.EXTRA_TEXT, title + "\n" + used_words + "\n"+ time.substring(0, 10) + " " +
                time.substring(11, time.length() - 4) +" to " + time2.substring(11, time.length() - 4)
                            + "\nClick here to RSVP: " + url);
                } else if(packageName.contains("android.gm")) { // If Gmail shows up twice, try removing this else-if clause and the reference to "android.gm" above
                    intent.putExtra(Intent.EXTRA_TEXT, title + "\n\n" + description + "\n\n" +used_words + "\n"
                            + time.substring(0, 10) + " " + time.substring(11, time.length() - 4) +" to " +
                            time2.substring(11, time.length() - 4) + "\nClick here to RSVP: " + url);
                    intent.putExtra(Intent.EXTRA_SUBJECT, title + "\n" + used_words + "\n"+ time.substring(0, 10) + " " +
                                    time.substring(11, time.length() - 4) +" to " + time2.substring(11, time.length() - 4));
                    intent.setType("message/rfc822");
                }
                else {
                    intent.putExtra(Intent.EXTRA_TEXT, title + "\n" + used_words + "\n"+ time.substring(0, 10) + " " +
                            time.substring(11, time.length() - 4) +" to " + time2.substring(11, time.length() - 4)
                            + "\nClick here to RSVP: " + url);}

                intentList.add(new LabeledIntent(intent, packageName, ri.loadLabel(pm), ri.icon));
            }
        }

        // convert intentList to array
        LabeledIntent[] extraIntents = intentList.toArray( new LabeledIntent[ intentList.size() ]);

        openInChooser.putExtra(Intent.EXTRA_INITIAL_INTENTS, extraIntents);
        startActivity(openInChooser);


    }

    private String formatDate(Date dateObject) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd'T'HH:mm:ss'Z'");
        return dateFormat.format(dateObject);
    }

    public Date formatter(String time) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        if (LocationState.location == 1){
            sdf.setTimeZone(TimeZone.getTimeZone("Australia/Sydney"));
        }
        else {
            sdf.setTimeZone(TimeZone.getTimeZone("GMT-2"));
        }
        return sdf.parse(time);
    }
}
