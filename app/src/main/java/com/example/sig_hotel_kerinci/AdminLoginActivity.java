package com.example.sig_hotel_kerinci;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class AdminLoginActivity extends AppCompatActivity {

    EditText et_username,et_password;
    TextView txt_username, txt_password;
    Button btn_login;

    DatabaseReference ref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);
        ref= FirebaseDatabase.getInstance().getReference().child("admin");

        txt_username = findViewById(R.id.txt_username);
        txt_password = findViewById(R.id.txt_password);

        et_username = findViewById(R.id.et_username);
        et_password = findViewById(R.id.et_password);

        btn_login = findViewById(R.id.btn_login);


        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                String userid = dataSnapshot.child("username").getValue().toString();
                String userpw = dataSnapshot.child("password").getValue().toString();
                txt_username.setText(userid);
                txt_password.setText(userpw);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    public void login (View v)
    {
        String setid = et_username.getText().toString();
        String setpw = et_password.getText().toString();
        String userid = txt_username.getText().toString();
        String userpw = txt_password.getText().toString();

        if (setid.equals(userid) && setpw.equals(userpw)){
            Intent intent = new Intent(AdminLoginActivity.this,AdminHome.class);
            startActivity(intent);
        }

        else if (setid.equals("") || setpw.equals(""))
        {
            Toast.makeText(this, "HARAP MASUKAN USERNAME DAN PASSWORD", Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(this, "USERNAME DAN PASSWORD SALAH", Toast.LENGTH_SHORT).show();
        }

    }
}