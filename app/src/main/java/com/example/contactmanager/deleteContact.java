package com.example.contactmanager;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.contactmanager.model.AddressBook;

public class deleteContact extends AppCompatActivity {

    ListView lv_contacts;


    personAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_contact);

        lv_contacts = findViewById(R.id.lv_contacts);



        adapter = new personAdapter(deleteContact.this, GlobalList.getGlobalList());

        lv_contacts.setAdapter(adapter);
        Toast.makeText(GlobalList.getAppContext(), "listsize=" + GlobalList.getGlobalList().getTheList().size(), Toast.LENGTH_SHORT).show();


        lv_contacts.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(deleteContact.this, "contact deleted: " + GlobalList.getGlobalList().getTheList().get(position).getName() , Toast.LENGTH_SHORT).show();
                deleteContact(position);

            }
        });
    }

    public void deleteContact(int position){
        GlobalList.getGlobalList().getTheList().remove(position);
        Toast.makeText(GlobalList.getAppContext(), "listsize=" + GlobalList.getGlobalList().getTheList().size(), Toast.LENGTH_SHORT).show();

        Intent i = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(i);
    }

}
