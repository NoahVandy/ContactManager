package com.example.contactmanager;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;
import java.nio.file.Path;


import com.example.contactmanager.businesServices.BusinessService;
import com.example.contactmanager.model.AddressBook;
import com.example.contactmanager.model.BaseContact;
import com.example.contactmanager.model.BusinessContact;
import com.example.contactmanager.model.PersonContact;

public class MainActivity extends AppCompatActivity {

    Button btn_addContact, btn_delete, btn_search, btn_save, btn_load;
    ListView lv_contactList;

    personAdapter adapter;

    GlobalList addressBook;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_addContact = findViewById(R.id.btn_addContact);
        btn_delete = findViewById(R.id.btn_delete);
        btn_search = findViewById(R.id.btn_search);
        btn_load = findViewById(R.id.btn_load);
        btn_save = findViewById(R.id.btn_save);
        lv_contactList = findViewById(R.id.lv_contactList);




        adapter = new personAdapter(MainActivity.this, GlobalList.getGlobalList());

        lv_contactList.setAdapter(adapter);

        Toast.makeText(this, "listsize=" + GlobalList.getGlobalList().getTheList().size(), Toast.LENGTH_SHORT).show();




        btn_addContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(new Intent(v.getContext(), baseOrPerson.class));
                startActivity(i);
            }
        });




        Bundle incomingMessages = getIntent().getExtras();

        if(incomingMessages != null) {
            //capture incoming data
            int positionToEdit = incomingMessages.getInt("edit");


            if(positionToEdit > -1){
                GlobalList.getGlobalList().getTheList().remove(positionToEdit);
            }


            adapter.notifyDataSetChanged();
        }

        lv_contactList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                editContact(position);
            }
        });


        btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), deleteContact.class);
                startActivity(i);
            }
        });

        btn_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), searchContact.class);
                startActivity(i);
            }
        });

        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BusinessService bs = new BusinessService();
                Log.d("noah", bs.getList().toString());
                bs.saveAllLists(GlobalList.getGlobalList());
            }
        });

        btn_load.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BusinessService bs = new BusinessService();

                //bs.setList(bs.loadAllLists());
                GlobalList.setGlobalList(bs.loadAllLists());
                Log.d("noah", GlobalList.getGlobalList().toString());


                adapter = new personAdapter(MainActivity.this, GlobalList.getGlobalList());
                lv_contactList.setAdapter(adapter);
                adapter.notifyDataSetChanged();
                Toast.makeText(GlobalList.getAppContext(), "listsize=" + GlobalList.getGlobalList().getTheList().size(), Toast.LENGTH_SHORT).show();





            }
        });



    }
    public void editContact(int position){


        // get contents of person
            BaseContact p = GlobalList.getGlobalList().getTheList().get(position);
            Toast.makeText(GlobalList.getAppContext(), "listsize=" + GlobalList.getGlobalList().getTheList().size(), Toast.LENGTH_SHORT).show();

            if (p instanceof PersonContact) {
                Intent i = new Intent(getApplicationContext(), addPersonContact.class);
                i.putExtra("edit",  position);
                i.putExtra("name", p.getName());
                i.putExtra("streetName", p.getStreetName());
                i.putExtra("city", p.getCity());
                i.putExtra("state", p.getState());
                i.putExtra("zip", p.getPostalCode());
                i.putExtra("country", p.getCountry());
                i.putExtra("phoneNumber", p.getPhoneNumber());
                i.putExtra("email", p.getEmail());
                i.putExtra("photoName", p.getPhotoName());
                i.putExtra("relationship", ((PersonContact) p).getRelationship());
                i.putExtra("birthday", ((PersonContact) p).getBirthday());

                startActivity(i);
            }
            else if( p instanceof BusinessContact){
                Intent i = new Intent(getApplicationContext(), addBusinessContact.class);

                i.putExtra("edit",  position);
                i.putExtra("name", p.getName());
                i.putExtra("streetName", p.getStreetName());
                i.putExtra("city", p.getCity());
                i.putExtra("state", p.getState());
                i.putExtra("zip", p.getPostalCode());
                i.putExtra("country", p.getCountry());
                i.putExtra("phoneNumber", p.getPhoneNumber());
                i.putExtra("email", p.getEmail());
                i.putExtra("photoName", p.getPhotoName());
                i.putExtra("open", ((BusinessContact) p).getOpen());
                i.putExtra("close", ((BusinessContact) p).getClose());
                i.putExtra("desc", ((BusinessContact) p).getDesc());
                i.putExtra("daysOfWeekOpen", ((BusinessContact) p).getDaysOfWeekOpen());
                i.putExtra("URL", ((BusinessContact) p).getUrl());

                startActivity(i);
        }




    }



}
