package com.example.contactmanager;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class baseOrPerson extends AppCompatActivity {


    Button btn_addPerson, btn_addBusiness;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base_or_person);


        btn_addPerson = findViewById(R.id.btn_addPerson);
        btn_addBusiness = findViewById(R.id.btn_addBusiness);

        btn_addPerson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(), addPersonContact.class);
                startActivity(i);
            }
        });

        btn_addBusiness.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(), addBusinessContact.class);
                startActivity(i);
            }
        });


    }
}
