package com.example.sig_hotel_kerinci;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.google.firebase.auth.FirebaseAuth;

import java.util.Map;

public class PhoneActivity extends AppCompatActivity {

    Button tambah,about,logout;
    ImageView home, maps;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone);

        //menu home
        home = findViewById(R.id.home);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =  new Intent(PhoneActivity.this, HomeActivity.class);
                startActivity(intent);
            }
        });
        //menu maps
        maps = findViewById(R.id.maps);
        maps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =  new Intent(PhoneActivity.this, MapsActivity.class);
                startActivity(intent);
            }
        });


        //about
        about = findViewById(R.id.about);
        about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =  new Intent(PhoneActivity.this, About.class);
                startActivity(intent);
            }
        });


        logout = findViewById(R.id.logout);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent loginActivity = new Intent(getApplicationContext(),StartActivity.class);
                startActivity(loginActivity);
                finish();
            }
        });

    }
    //exit
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(PhoneActivity.this,HomeActivity.class);
        startActivity(intent);
    }
}