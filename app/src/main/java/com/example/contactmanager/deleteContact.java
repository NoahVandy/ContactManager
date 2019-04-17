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

    AddressBook addressBook;

    personAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_contact);

        lv_contacts = findViewById(R.id.lv_contacts);

        addressBook = ((GlobalList) this.getApplication()).getGlobalList();

        adapter = new personAdapter(deleteContact.this, addressBook);

        lv_contacts.setAdapter(adapter);

        lv_contacts.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(deleteContact.this, "contact deleted: " + addressBook.getTheList().get(position).getName() , Toast.LENGTH_SHORT).show();
                deleteContact(position);

            }
        });
    }

    public void deleteContact(int position){
        addressBook.getTheList().remove(position);

        Intent i = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(i);
    }

}
