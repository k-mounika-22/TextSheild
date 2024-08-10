package com.example.textshield;

import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Arrays;
import java.util.Random;

public class Encyption extends AppCompatActivity {
    private EditText number, Message, key;
    private TextView textView;
    private Button send;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_encyption);
        number = findViewById(R.id.Phone);
        Message = findViewById(R.id.message);
        key = findViewById(R.id.Key);
        send = findViewById(R.id.sendbutton);
        textView = findViewById(R.id.encryptMessage);

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (number.getText().toString().trim().isEmpty() ||
                        Message.getText().toString().trim().isEmpty() ||
                        key.getText().toString().trim().isEmpty()) {
                    Toast.makeText(Encyption.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                } else {
                    if (checkSelfPermission(android.Manifest.permission.SEND_SMS) == PackageManager.PERMISSION_GRANTED) {
                        sendSMS();
                    } else {
                        requestPermissions(new String[]{android.Manifest.permission.SEND_SMS}, 1);
                    }
                }
            }
        });

    }
    private void sendSMS(){
        String phoneNo = number.getText().toString().trim();
        String sms = Message.getText().toString().trim();
        CipherAlgorithm ob = new CipherAlgorithm();
        int keys = Integer.parseInt(key.getText().toString());
        String encyptedText = ob.encryption(sms, keys);
        Random rand = new Random();
        int randomNumber = 10 + rand.nextInt(90);
        encyptedText = encyptedText + keys + randomNumber + (char)('A' + (keys % 26));



        try {
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(phoneNo,null,encyptedText,null,null);
            Toast.makeText(this, "Message Sent", Toast.LENGTH_SHORT).show();
            textView.setText(encyptedText);

        }catch (Exception e){
            e.printStackTrace();
            Toast.makeText(this, "Failed to send message", Toast.LENGTH_SHORT).show();
        }

    }
}