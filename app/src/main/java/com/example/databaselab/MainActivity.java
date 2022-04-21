package com.example.databaselab;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText name, contact, dob,surName,depart,inst,grad;
    Button insert,view;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        name = findViewById(R.id.Name);
        surName = findViewById(R.id.SurName);
        dob = findViewById(R.id.dob);
        depart = findViewById(R.id.Dept);
        inst = findViewById(R.id.ins);
        contact = findViewById(R.id.Contect);
        grad = findViewById(R.id.Grades);

        insert = findViewById(R.id.btnInsert);
        view = findViewById(R.id.btnShow);
        DB = new DBHelper(this);
        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nameTXT = name.getText().toString();
                String surNameTXT = surName.getText().toString();
                String depTXT = depart.getText().toString();
                String instTXT = inst.getText().toString();
                String gradeTXT = grad.getText().toString();
                String contactTXT = contact.getText().toString();
                String dobTXT = dob.getText().toString();

                Boolean checkinsertdata = DB.insertuserdata(nameTXT,surNameTXT, contactTXT,depTXT, dobTXT,instTXT,gradeTXT);
                if(checkinsertdata==true)
                    Toast.makeText(MainActivity.this, "New Entry Inserted", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(MainActivity.this, "New Entry Not Inserted", Toast.LENGTH_SHORT).show();
            }        });


        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor res = DB.getdata();
                if(res.getCount()==0){
                    Toast.makeText(MainActivity.this, "No Entry Exists", Toast.LENGTH_SHORT).show();
                    return;
                }
                StringBuffer buffer = new StringBuffer();
                while(res.moveToNext()){
                    buffer.append("Name :"+res.getString(0)+"\n");
                    buffer.append("Sur Name :"+res.getString(1)+"\n");
                    buffer.append("Contact :"+res.getString(2)+"\n");
                    buffer.append("Date of Birth :"+res.getString(3)+"\n");
                    buffer.append("Department :"+res.getString(4)+"\n");
                    buffer.append("Institute :"+res.getString(5)+"\n");
                    buffer.append("Address :"+res.getString(6)+"\n\n\n");
                }



                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setCancelable(true);
                builder.setTitle("User Entries");
                builder.setMessage(buffer.toString());
                builder.show();
            }        });
    }}