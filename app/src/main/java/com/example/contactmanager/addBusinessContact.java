package com.example.contactmanager;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.contactmanager.model.AddressBook;
import com.example.contactmanager.model.BusinessContact;
import com.example.contactmanager.model.PersonContact;

import java.util.Collections;

public class addBusinessContact extends AppCompatActivity {

    EditText et_name, et_streetName, et_city, et_state, et_zip, et_country, et_phoneNumber, et_email, et_photoName, et_open, et_close, et_desc, et_URL;

    CheckBox cb_sunday, cb_monday, cb_tuesday, cb_wednesday, cb_thursday, cb_friday, cb_saturday;

    Button btn_addBusiness, btn_call, btn_text, btn_navigate, btn_email;

    AddressBook addressBook;
    int positionToEdit = -1;

    private final static int REQUEST_CALL = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_business_contact);

        addressBook =  GlobalList.getGlobalList();



        btn_addBusiness = findViewById(R.id.btn_addBusiness);
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
        et_open = findViewById(R.id.et_open);
        et_close = findViewById(R.id.et_close);
        et_desc = findViewById(R.id.et_desc);
        et_URL = findViewById(R.id.et_URL);
        cb_sunday = findViewById(R.id.cb_Sunday);
        cb_monday = findViewById(R.id.cb_Monday);
        cb_tuesday = findViewById(R.id.cb_tuesday);
        cb_wednesday = findViewById(R.id.cb_wednesday);
        cb_thursday = findViewById(R.id.cb_thursday);
        cb_friday = findViewById(R.id.cb_friday);
        cb_saturday = findViewById(R.id.cb_saturday);




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

        if (incomingMessages != null) {
            String name = incomingMessages.getString("name");
            String streetName = incomingMessages.getString("streetName");
            String city = incomingMessages.getString("city");
            String state = incomingMessages.getString("state");
            String zip = incomingMessages.getString("zip");
            String country = incomingMessages.getString("country");
            String phoneNumber = incomingMessages.getString("phoneNumber");
            String email = incomingMessages.getString("email");
            String photo = incomingMessages.getString("photoName");
            String open = incomingMessages.getString("open");
            String close = incomingMessages.getString("close");
            String desc = incomingMessages.getString("desc");
            boolean[] newDaysOfWeekOpen = incomingMessages.getBooleanArray("daysOfWeekOpen");
            String URL = incomingMessages.getString("URL");

            positionToEdit = incomingMessages.getInt("edit");



            if(positionToEdit > -1){
                addressBook.getTheList().remove(positionToEdit);
            }

            Toast.makeText(this, "array: " + newDaysOfWeekOpen[0], Toast.LENGTH_SHORT).show();

            et_name.setText(name);
            et_streetName.setText(streetName);
            et_city.setText(city);
            et_state.setText(state);
            et_zip.setText(zip);
            et_country.setText(country);
            et_phoneNumber.setText(phoneNumber);
            et_email.setText(email);
            et_photoName.setText(photo);
            et_open.setText(open);
            et_close.setText(close);
            et_desc.setText(desc);
            cb_sunday.setChecked(newDaysOfWeekOpen[0]);
            cb_monday.setChecked(newDaysOfWeekOpen[1]);
            cb_tuesday.setChecked(newDaysOfWeekOpen[2]);
            cb_wednesday.setChecked(newDaysOfWeekOpen[3]);
            cb_thursday.setChecked(newDaysOfWeekOpen[4]);
            cb_friday.setChecked(newDaysOfWeekOpen[5]);
            cb_saturday.setChecked(newDaysOfWeekOpen[6]);
            et_URL.setText(URL);
        }



        btn_addBusiness.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
                String open = et_open.getText().toString();
                String close = et_close.getText().toString();
                String desc = et_desc.getText().toString();
                String URL = et_URL.getText().toString();

                boolean[] daysOfWeekOpen ={ cb_sunday.isChecked(), cb_monday.isChecked(), cb_tuesday.isChecked(), cb_wednesday.isChecked(), cb_thursday.isChecked(), cb_friday.isChecked(), cb_saturday.isChecked()};

                //put the strings into a message


                //switch back to main activity


                BusinessContact p = new BusinessContact(newName, newStreet, newCity, newState, newZip, newCountry, newNumber, newEmail, newPhoto, open, close, desc, daysOfWeekOpen, URL);

                addressBook.getTheList().add(p);
                Collections.sort(addressBook.getTheList());


                Intent i = new Intent(v.getContext(), MainActivity.class);
                startActivity(i);
            }
        });

    }


    public void callPhoneNumber(String phoneNumber) {
        Intent intent = new Intent(Intent.ACTION_CALL);
        intent.setData(Uri.parse("tel:" + phoneNumber));
        if (ActivityCompat.checkSelfPermission(addBusinessContact.this,
                Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(addBusinessContact.this,
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
