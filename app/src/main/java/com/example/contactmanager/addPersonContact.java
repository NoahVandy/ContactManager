package com.example.contactmanager;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.contactmanager.model.AddressBook;
import com.example.contactmanager.model.PersonContact;

public class addPersonContact extends AppCompatActivity {

    Button btn_addPerson, btn_call, btn_text, btn_navigate, btn_email;

    EditText et_name, et_streetName, et_city, et_state, et_zip, et_country, et_phoneNumber, et_email, et_photoName, et_relationship, et_birthday;


    int positionToEdit = -1;

    AddressBook addressBook;


    private final static int REQUEST_CALL = 1;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_person_contact);

        addressBook =  GlobalList.getGlobalList();


        btn_addPerson = findViewById(R.id.btn_addPerson);
        btn_call = findViewById(R.id.btn_call);
        btn_text = findViewById(R.id.btn_text);
        btn_navigate = findViewById(R.id.btn_navigate);
        btn_email = findViewById(R.id.btn_email);

        et_name = findViewById(R.id.et_name);
        et_streetName = findViewById(R.id.et_streetName);
        et_city = findViewById(R.id.et_city);
        et_state = findViewById(R.id.et_state);
        et_zip = findViewById(R.id.et_zip);
        et_country = findViewById(R.id.et_country);
        et_phoneNumber = findViewById(R.id.et_phoneNumber);
        et_email = findViewById(R.id.et_email);
        et_photoName = findViewById(R.id.et_photoName);
        et_relationship = findViewById(R.id.et_relationship);
        et_birthday = findViewById(R.id.et_birthday);

        //making the android functions


        btn_call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callPhoneNumber(et_phoneNumber.getText().toString());
            }
        });

        btn_navigate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mapsQuery ="geo:0,0?q=" + et_streetName.getText().toString() + " "  + et_city.getText().toString() + " " + et_state.getText().toString() + " " + et_zip.getText().toString() + " " + et_country.getText().toString();
                Uri mapUri = Uri.parse(mapsQuery);
                showMap(mapUri);
            }
        });

        btn_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(), emailOrTextContact.class);

                i.putExtra("phoneNumber", et_phoneNumber.getText().toString());
                i.putExtra("key", "text");

                startActivity(i);
            }
        });

        btn_email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(), emailOrTextContact.class);

                i.putExtra("phoneNumber", et_email.getText().toString());
                i.putExtra("key", "email");

                startActivity(i);
            }
        });











        Bundle incomingMessages = getIntent().getExtras();

        if(incomingMessages != null){
            String name = incomingMessages.getString("name");
            String streetName = incomingMessages.getString("streetName");
            String city = incomingMessages.getString("city");
            String state = incomingMessages.getString("state");
            String zip = incomingMessages.getString("zip");
            String country = incomingMessages.getString("country");
            String phoneNumber = incomingMessages.getString("phoneNumber");
            String email = incomingMessages.getString("email");
            String photo = incomingMessages.getString("photoName");
            String relationship = incomingMessages.getString("relationship");
            String birthday = incomingMessages.getString("birthday");
            positionToEdit = incomingMessages.getInt("edit");
            Toast.makeText(this, "position: " + positionToEdit, Toast.LENGTH_SHORT).show();


            if(positionToEdit > -1){
                addressBook.getTheList().remove(positionToEdit);
            }

            et_name.setText(name);
            et_streetName.setText(streetName);
            et_city.setText(city);
            et_state.setText(state);
            et_zip.setText(zip);
            et_country.setText(country);
            et_phoneNumber.setText(phoneNumber);
            et_email.setText(email);
            et_photoName.setText(photo);
            et_relationship.setText(relationship);
            et_birthday.setText(birthday);
        }




        btn_addPerson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               // BusinessService bs = new BusinessService();
                //get strings from et's
                String newName = et_name.getText().toString();
                String newStreet = et_streetName.getText().toString();
                String newCity = et_city.getText().toString();
                String newState = et_state.getText().toString();
                String newZip = et_zip.getText().toString();
                String newCountry = et_country.getText().toString();
                String newNumber = et_phoneNumber.getText().toString();
                String newEmail = et_email.getText().toString();
                String newPhoto = et_photoName.getText().toString();
                String newRelationship = et_relationship.getText().toString();
                String newBirthday = et_birthday.getText().toString();

                //put the strings into a message


                //switch back to main activity


                PersonContact p = new PersonContact(newName, newStreet, newCity, newState, newZip, newCountry, newNumber, newEmail, newPhoto, newRelationship, newBirthday);

                addressBook = GlobalList.getGlobalList();
                Toast.makeText(GlobalList.getAppContext(), "listsize=" + addressBook.getTheList().size(), Toast.LENGTH_SHORT).show();

                addressBook.getTheList().add(p);
                Log.d("noah", addressBook.toString());

                Intent i = new Intent(v.getContext(), MainActivity.class);
                startActivity(i);
            }
        });










    }

    //making the functions for the android functions



    public void callPhoneNumber(String phoneNumber) {
        Intent intent = new Intent(Intent.ACTION_CALL);
        intent.setData(Uri.parse("tel:" + phoneNumber));
        if (ActivityCompat.checkSelfPermission(addPersonContact.this,
                Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(addPersonContact.this,
                    new String[]{Manifest.permission.CALL_PHONE}, REQUEST_CALL);
        } else{

            startActivity(intent);
        }
    }


    public void showMap(Uri geoLocation) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(geoLocation);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }


}
