package com.example.sig_hotel_kerinci;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import java.util.HashMap;

public class AdminLokasiView extends AppCompatActivity {
    ImageView imageView;

    TextView txtnama, txtalamat;
    TextView txttipe1, txttipe2, txttipe3;
    TextView txtharga1, txtharga2, txtharga3;

    DatabaseReference ref,DataRef;
    StorageReference StorageRef;

    //rating
    TextView getRating;
    Button btnsubmit;
    RatingBar rate;
    float myRating = 0;

    String nomor;
    //maps metod
    Uri gmmIntentUri;
    String latitude;
    String longitude;

    Intent mapIntent;
    String goolgeMap = "com.google.android.apps.maps";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_lokasi_view);
        imageView = findViewById(R.id.img);

        txtnama = findViewById(R.id.nama);
        txtalamat = findViewById(R.id.alamat);

        txttipe1 = findViewById(R.id.tipe1);
        txttipe2 = findViewById(R.id.tipe2);
        txttipe3 = findViewById(R.id.tipe3);

        txtharga1 = findViewById(R.id.harga1);
        txtharga2 = findViewById(R.id.harga2);
        txtharga3 = findViewById(R.id.harga3);

        //rating
        getRating = findViewById(R.id.txtrate);
        btnsubmit = findViewById(R.id.btnsubmit);
        rate = findViewById(R.id.rateBar);



        final String LokasiKey = getIntent().getStringExtra("LokasiKey");
        ref = FirebaseDatabase.getInstance().getReference().child("lokasi");
        DataRef = FirebaseDatabase.getInstance().getReference().child("lokasi").child(LokasiKey);
        StorageRef = FirebaseStorage.getInstance().getReference().child("image_lokasi").child(LokasiKey);

        ref.child(LokasiKey).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if (dataSnapshot.exists()) {

                    String ImageUrl = dataSnapshot.child("imageUrl").getValue().toString();

                    String nama = dataSnapshot.child("nama").getValue().toString();
                    String alamat = dataSnapshot.child("alamat").getValue().toString();

                    String tipe1 = dataSnapshot.child("tipe1").getValue().toString();
                    String tipe2 = dataSnapshot.child("tipe2").getValue().toString();
                    String tipe3 = dataSnapshot.child("tipe3").getValue().toString();

                    String harga1 = dataSnapshot.child("harga1").getValue().toString();
                    String harga2 = dataSnapshot.child("harga2").getValue().toString();
                    String harga3 = dataSnapshot.child("harga3").getValue().toString();



                    Picasso.get().load(ImageUrl).into(imageView);
                    txtnama.setText(nama);
                    txtalamat.setText(alamat);

                    txttipe1.setText(tipe1);
                    txttipe2.setText(tipe2);
                    txttipe3.setText(tipe3);

                    txtharga1.setText(harga1);
                    txtharga2.setText(harga2);
                    txtharga3.setText(harga3);


                    nomor = dataSnapshot.child("nomor").getValue().toString();
                    latitude = dataSnapshot.child("latitude").getValue().toString();
                    longitude = dataSnapshot.child("longitude").getValue().toString();

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        rate.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {

                getRating.setText("" + rating);

                myRating = ratingBar.getRating();

            }
        });

        btnsubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View vi) {


                HashMap map = new HashMap();
                map.put("Rating",""+myRating);



                DataRef.updateChildren(map).addOnSuccessListener(new OnSuccessListener() {
                    @Override
                    public void onSuccess(Object o) {

                    }
                });
                Toast.makeText(AdminLokasiView.this, "Berhasil Memberi Rating!", Toast.LENGTH_LONG).show();
            }
        });


    }

    public void onNav(View view) {
        if (view.getId() == R.id.btn_nav) {
            gmmIntentUri = Uri.parse("google.navigation:q=" + latitude + "," + longitude);
            mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
            mapIntent.setPackage(goolgeMap);
            if (mapIntent.resolveActivity(getPackageManager()) != null) {
                startActivity(mapIntent);
            } else {
                Toast.makeText(AdminLokasiView.this, "Google Maps Belum Terinstall", Toast.LENGTH_SHORT).show();
            }
        }
    }
    public void onCall (View view){
        String number = nomor;
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:"+number));
        startActivity(intent);

    }
}