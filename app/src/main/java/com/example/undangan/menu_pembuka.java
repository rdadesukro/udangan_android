package com.example.undangan;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.github.squti.guru.Guru;

public class menu_pembuka extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_pembuka);


        star();
    }

    void star(){
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                Intent intent  = new Intent(menu_pembuka.this, menu_login.class);
                startActivity(intent);

            }
        }, 3000L); //3000 L = 3 detik
    }
}