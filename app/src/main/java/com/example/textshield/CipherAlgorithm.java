package com.example.textshield;

import android.widget.EditText;

public class CipherAlgorithm{


    public  String encryption(String plaintext, int shift) {
        StringBuilder ciphertext = new StringBuilder();
        for (int i = 0; i < plaintext.length(); i++) {
            char ch = plaintext.charAt(i);
            if (Character.isLetter(ch)) {
                char base = Character.isUpperCase(ch) ? 'A' : 'a';
                ch = (char) ((ch - base + shift) % 26 + base);
            }
            ciphertext.append(ch);
        }
        return ciphertext.toString();
    }

    public  String decryption(String ciphertext, int shift) {
        return encryption(ciphertext, 26-shift);
    }
}
