package com.example.contactmanager;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class emailOrTextContact extends AppCompatActivity {


    Button btn_send;

    EditText et_message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_contact);

        btn_send = findViewById(R.id.btn_send);
        et_message = findViewById(R.id.et_message);

        Bundle incomingMessages = getIntent().getExtras();

        if(incomingMessages != null) {
            String phoneNumber = incomingMessages.getString("phoneNumber");
            String textOrEmail = incomingMessages.getString("key");


            if(textOrEmail.equals("text")){
                et_message.setHint("Enter a message");
            }
            else if (textOrEmail.equals("email")){
                et_message.setHint("Enter a subject");
            }
        }











        btn_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String[] addresses = new String[1];
                addresses[0] = et_message.getText().toString();

                Bundle incomingMessages = getIntent().getExtras();

                if(incomingMessages != null) {
                    String phoneNumber = incomingMessages.getString("phoneNumber");
                    String textOrEmail = incomingMessages.getString("key");

                    if(textOrEmail.equals("text")){
                        composeMmsMessage(phoneNumber, et_message.getText().toString());
                    }
                    else if(textOrEmail.equals("email")){
                        composeEmail(addresses, et_message.getText().toString());
                    }
                }





            }
        });
    }


    private void composeEmail(String[] addresses, String subject) {
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_EMAIL, addresses);
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    public void composeMmsMessage(String phoneNumber, String message) {
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("smsto:" + phoneNumber));  // This ensures only SMS apps respond
        intent.putExtra("sms_body", message);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }
}
