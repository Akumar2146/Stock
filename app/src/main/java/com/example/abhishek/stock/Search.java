package com.example.abhishek.stock;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Search extends AppCompatActivity {

    EditText search;
    RecyclerView recyclerView;
    DatabaseReference databaseReference;
    ArrayList<String> nameList;
    ArrayList<String> brokerageList;
    ArrayList<String> intraList;
    ArrayList<String> accountList;
    ArrayList<String> annualList;
    SearchAdapter searchAdapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        search = (EditText)findViewById(R.id.search_edit_text);
        recyclerView = (RecyclerView)findViewById(R.id.recycleView);

        databaseReference = FirebaseDatabase.getInstance().getReference();

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));

        nameList = new ArrayList<>();
        brokerageList = new ArrayList<>();
        intraList = new ArrayList<>();
        accountList = new ArrayList<>();
        annualList = new ArrayList<>();

        search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                   if(!editable.toString().isEmpty()){
                       setAdapter(editable.toString());
                   } else {
                       nameList.clear();
                       brokerageList.clear();
                       intraList.clear();
                       accountList.clear();
                       annualList.clear();
                       recyclerView.removeAllViews();
                   }
            }
        });
    }

    private void setAdapter(final String s) {
        databaseReference.child("Member").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                nameList.clear();
                brokerageList.clear();
                intraList.clear();
                accountList.clear();
                annualList.clear();
                recyclerView.removeAllViews();

                int counter=0;

                for(DataSnapshot snapshot: dataSnapshot.getChildren()){
                    String uid = snapshot.getKey();
                    String name = snapshot.child("name").getValue(String.class);
                    String brokerage = snapshot.child("brokerage").getValue(String.class);
                    String intra = snapshot.child("intra").getValue(String.class);
                    String account = snapshot.child("account").getValue(String.class);
                    String annual = snapshot.child("annual").getValue(String.class);

                    if(name.toLowerCase().contains(s.toLowerCase())){
                        nameList.add(name);
                        brokerageList.add(brokerage);
                        intraList.add(intra);
                        accountList.add(account);
                        annualList.add(annual);
                        counter++;
                    } else if(brokerage.toLowerCase().contains(s.toLowerCase())){
                        nameList.add(name);
                        brokerageList.add(brokerage);
                        intraList.add(intra);
                        accountList.add(account);
                        annualList.add(annual);
                        counter++;
                    }

                    if(counter==1000)
                        break;
                }

                searchAdapter = new SearchAdapter(Search.this, nameList, brokerageList, intraList, accountList, annualList);
                recyclerView.setAdapter(searchAdapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
