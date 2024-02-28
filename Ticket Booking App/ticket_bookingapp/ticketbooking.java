package com.example.ticket_bookingapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;

public class ticketbooking extends AppCompatActivity {
    SQLiteDatabase db;
    AlertDialog.Builder builder;
    ImageButton ib1;
    Spinner sp;
    boolean invalid = false;
    String Colector="";
    TextView txtalertName;
    EditText Name,Password,Contact,Address;
    Button SubmitSave ,paybt;
    CheckBox Runing,Jumping,Speed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ticketbooking);
        ib1=findViewById(R.id.imageButton);
        sp=findViewById(R.id.SpGames);
        Name=findViewById(R.id.userName);
        Password=findViewById(R.id.userPassword);
        Contact=findViewById(R.id.userContact);
        Address=findViewById(R.id.useraddress);
        txtalertName=findViewById(R.id.userAlert);
        Runing=findViewById(R.id.running);
        Jumping=findViewById(R.id.jumping);
        Speed=findViewById(R.id.speed);
        SubmitSave=findViewById(R.id.btnSubmit);
        paybt=findViewById(R.id.btnSubmit1);
        paybt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(ticketbooking.this,ticket_paymentpage.class);
                startActivity(intent);
            }
        });
        builder = new AlertDialog.Builder(this);
        db = openOrCreateDatabase("db2", Context.MODE_PRIVATE,null);
        db.execSQL("Create table if not exists form(name varchar, password varchar,contact varchar,address varchar)");
        SubmitSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(ticketbooking.this,ticket_paymentpage.class);
                startActivity(intent);
                String name = Name.getText().toString();
                String password=Password.getText().toString();
                String contact=Contact.getText().toString();
                String address=Address.getText().toString();
                db.execSQL("Insert into form values ('"+name+"','"+password+"','"+contact+"','"+address+"')");
                builder.setMessage("Successfully Inserted into Database");
                builder.show();
                if (name.isEmpty()){
                    Toast.makeText(ticketbooking.this,"Pleas fill the name field",Toast.LENGTH_SHORT).show();
                }
                else if (name.equals("Sameh") ||name.equals("UlHaq")){
                    invalid=true;
                    txtalertName.setText("Name Already exist");
                }

                else if(password.isEmpty()){
                    Toast.makeText(ticketbooking.this,"Pleas fill the password field",Toast.LENGTH_SHORT).show();
                }


                else if (contact.isEmpty()){
                    Toast.makeText(ticketbooking.this,"Pleas fill the Contact field",Toast.LENGTH_SHORT).show();
                }

                else if (address.isEmpty()){
                    Toast.makeText(ticketbooking.this,"Pleas fill the address field",Toast.LENGTH_SHORT).show();
                }



                else{

                    Colector+=name+"\n";
                    Colector+=password+"\n";
                    Colector+=contact+"\n";
                    Colector+=address+"\n";
                    if (Runing.isChecked()){
                        Colector+="running"+"\n";
                        if (Jumping.isChecked()){
                            Colector+="jumping"+"\n";
                        }
                        if (Speed.isChecked()){
                            Colector+="Speed"+"\n";
                        }
                    }
                    Toast.makeText(ticketbooking.this,"Successfully submited \n:"+Colector,Toast.LENGTH_SHORT).show();
                }

            }
        });

        List<String> categoryGames=new ArrayList<>();
        categoryGames.add("Select Your Games");
        categoryGames.add("Volleyball");
        categoryGames.add("Throwball");
        categoryGames.add("Basketball");
        categoryGames.add("Cricket");
        categoryGames.add("Badminton");
        categoryGames.add("Football");
        ArrayAdapter<String> arrayAdapter;
        arrayAdapter=new ArrayAdapter<>(this,android.R.layout.simple_spinner_item,categoryGames);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp.setAdapter(arrayAdapter);
        sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long l) {
                if(parent.getItemAtPosition(position).equals("Select Your Games")){
                    //Do Nothing

                }
                else{
                    String item=parent.getItemAtPosition(position).toString();
                    Colector+=item+"\n";
                    Toast.makeText(ticketbooking.this, "Selected Games: "+item, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {


            }
        });
        ib1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor c=db.rawQuery("Select * from form",null);
                StringBuffer ab=new StringBuffer();
                while (c.moveToNext()){
                    ab.append("Name:" + c.getString(0) + "\n");
                    ab.append("Password:" + c.getString(1) + "\n");
                    ab.append("Contact:" + c.getString(2) + "\n");
                    ab.append("Address:" + c.getString(3) + "\n");

                }
                builder.setMessage(ab.toString());
                builder.show();
            }
        });

    }
}