package com.example.ticket_bookingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.RatingBar;
import android.widget.VideoView;

public class shaw extends AppCompatActivity {

    private VideoView vv;
    RatingBar rr;
    Button b;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fast10);
        vv = findViewById(R.id.video);
        b = findViewById(R.id.book);
        rr = findViewById(R.id.ratingBar);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(shaw.this,ticketbooking.class);
                startActivity(intent);
            }
        });

        android.widget.MediaController mediaController = new MediaController(this);
        mediaController.setAnchorView(vv);
        vv.setMediaController(mediaController);
// Set the URI for the VideoView and start playing the video
        vv.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.shaw));
        vv.start();
    }
}