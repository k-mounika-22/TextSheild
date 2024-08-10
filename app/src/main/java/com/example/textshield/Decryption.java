package com.example.textshield;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Arrays;

public class Decryption extends AppCompatActivity {
    EditText original;
    TextView decrypted;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_decryption);
        original = findViewById(R.id.encryptedMessage);
        decrypted = findViewById(R.id.decryptedMessage);
        button = findViewById(R.id.decryptbutton);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String originalMessage = original.getText().toString().trim();
                if(originalMessage.isEmpty()){
                    Toast.makeText(Decryption.this, "Can't decrypt empty text", Toast.LENGTH_SHORT).show();
                }
                else {
                    int k = Character.getNumericValue(originalMessage.charAt(originalMessage.length() - 4));

                    originalMessage = originalMessage.substring(0,originalMessage.length() - 4);
                    CipherAlgorithm ob = new CipherAlgorithm();
                    String decryp = "Original Message : " + ob.decryption(originalMessage, k);
                    decrypted.setText(decryp);
                }


            }
        });



    }
}