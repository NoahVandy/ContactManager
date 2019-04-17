package com.example.contactmanager;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.contactmanager.model.AddressBook;
import com.example.contactmanager.model.BaseContact;
import com.example.contactmanager.model.BusinessContact;
import com.example.contactmanager.model.PersonContact;

public class searchContact extends AppCompatActivity {


    EditText et_search;
    Button btn_submit;
    ListView lv_searchContacts;
    AddressBook addressBook;
    AddressBook searchBook = new AddressBook(true);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_contact);

        et_search = findViewById(R.id.et_search);
        btn_submit = findViewById(R.id.btn_submit);
        lv_searchContacts = findViewById(R.id.lv_searchContacts);

        addressBook = ((GlobalList) this.getApplication()).getGlobalList();





        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //this sets the listview to listen for the searchBook
                personAdapter adapter = new personAdapter(searchContact.this, searchBook);
                lv_searchContacts.setAdapter(adapter);
                String search = et_search.getText().toString();

                //this goes through the list and searches every contact with the "search" string
                for (int i = 0; i < addressBook.getTheList().size(); i++){
                    searchContact(search, i);
                }

                adapter.notifyDataSetChanged();
            }
        });


        lv_searchContacts.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                editContact(position);
            }
        });



    }


    public BaseContact searchContact(String search, int i){
        //sets this int to what has been returned by the search function in the addressBook
        int searchPosition = addressBook.searchFor(search, i);
        //error checking
        if (searchPosition != -1){
            searchBook.getTheList().add(addressBook.getTheList().get(searchPosition));

            return addressBook.getTheList().get(searchPosition);
        }else{
            return null;
        }
    }


    public void editContact(int position){


        // get contents of person
        BaseContact p = searchBook.getTheList().get(position);

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
