package com.example.ticket_bookingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;

import android.os.Bundle;

import android.view.View;

import android.widget.ImageButton;

public class ticket_homepage extends AppCompatActivity {
    ImageButton btbook,btprofile,batdark,batrise,fast,shaw;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
           setContentView(R.layout.activity_ticket_homepage);
           btbook=findViewById(R.id.imagebook);
           btprofile=findViewById(R.id.imageprofile);
           batdark=findViewById(R.id.dark);
           batrise=findViewById(R.id.rise);
           fast=findViewById(R.id.fast10);
           btbook.setOnClickListener(new View.OnClickListener() {
@Override
public void onClick(View v) {
        Intent i=new Intent(getApplicationContext(),ticketbooking.class);
        startActivity(i);
}
        });
      batrise.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              Intent i=new Intent(ticket_homepage.this,batmandark.class);
              startActivity(i);
          }
      });
      batrise.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              Intent i=new Intent(ticket_homepage.this,batmandark.class);
              startActivity(i);
          }
      });
      fast.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              Intent i=new Intent(ticket_homepage.this,fast10.class);
              startActivity(i);
          }
      });
    }
}



