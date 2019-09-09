package com.example.abhishek.stock;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {
    EditText tname,tbrokerage,tintraday,taccount,taanual;
    Button bsubmit,bsearch;
    DatabaseReference reff;
    Member member;
    long maxid=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tname=(EditText)findViewById(R.id.tname1);
        tbrokerage=(EditText)findViewById(R.id.txtbrokerage);
        tintraday=(EditText)findViewById(R.id.txtintraday);
        taccount=(EditText)findViewById(R.id.txtaccount);
        taanual=(EditText)findViewById(R.id.txtannual);
        bsubmit=(Button)findViewById(R.id.btnsubmit);
        bsearch=(Button)findViewById(R.id.btnsearch);
        member=new Member();
        reff=FirebaseDatabase.getInstance().getReference().child("Member");

        reff.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists())
                    maxid=(dataSnapshot.getChildrenCount());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        bsearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i1 = new Intent(MainActivity.this,Search.class);
                startActivity(i1);
            }
        });

        bsubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (TextUtils.isEmpty(tname.getText().toString()) || TextUtils.isEmpty(tbrokerage.getText().toString()) || TextUtils.isEmpty(tintraday.getText().toString()) || TextUtils.isEmpty(taccount.getText().toString()) || TextUtils.isEmpty(taanual.getText().toString())) {
                    tname.setError("Name is Empty");
                    tbrokerage.setError("Brokerage charge is Empty");
                    tintraday.setError("Intraday charge is Empty");
                    taccount.setError("Account is Empty");
                    taanual.setError("Annual charge is Empty");

                } else {
                    member.setName(tname.getText().toString().trim());
                    member.setBrokerage(tbrokerage.getText().toString().trim());
                    member.setIntra(tintraday.getText().toString().trim());
                    member.setAccount(taccount.getText().toString().trim());
                    member.setAnnual(taanual.getText().toString().trim());
                    reff.child(String.valueOf(maxid + 1)).setValue(member);
                    Toast.makeText(MainActivity.this, "Data Inserted", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(MainActivity.this, Search.class);
                    startActivity(i);
                }
            }
        });

    }
}
