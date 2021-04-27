package com.example.sig_hotel_kerinci;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.example.sig_hotel_kerinci.Comment;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class LokasiViewActivity extends AppCompatActivity {

    ImageView img;
    TextView nama, alamat,
            tipe1, tipe2, tipe3,
            harga1, harga2, harga3;

    String PostKey;

    Button btnAddComment;
    EditText editTextComment;

    //video
    VideoView video;

    //rate
    RatingBar dataRate;
    TextView getRating;
    float myRating = 0;

    //maps and call
    Uri gmmIntentUri;
    String lokasi, notelp, lat, lng;
    Intent mapIntent;
    String goolgeMap = "com.google.android.apps.maps";
    //------

    //firebase
    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;
    FirebaseDatabase firebaseDatabase;
    //komen
    RecyclerView RvComment;
    CommentAdapter commentAdapter;
    List<Comment> listComment;
    static String COMMENT_KEY = "Comment";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lokasi_view);

        editTextComment = findViewById(R.id.post_detail_comment);
        btnAddComment = findViewById(R.id.post_detail_add_comment_btn);

        img = findViewById(R.id.img);
        video = findViewById(R.id.video);

        nama = findViewById(R.id.nama);
        alamat = findViewById(R.id.alamat);

        tipe1 = findViewById(R.id.tipe1);
        tipe2 = findViewById(R.id.tipe2);
        tipe3 = findViewById(R.id.tipe3);

        harga1 = findViewById(R.id.harga1);
        harga2 = findViewById(R.id.harga2);
        harga3 = findViewById(R.id.harga3);

        RvComment = findViewById(R.id.rv_comment);
        getRating = findViewById(R.id.rate);
        dataRate = findViewById(R.id.btnreting);

        dataRate.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {

                getRating.setText("" + rating);

                myRating = ratingBar.getRating();
            }
        });

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();
        firebaseDatabase = FirebaseDatabase.getInstance();

        //btn komentar
        btnAddComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                btnAddComment.setVisibility(View.INVISIBLE);
                DatabaseReference commentReference = firebaseDatabase.getReference(COMMENT_KEY).child(PostKey).push();

                String comment_content = editTextComment.getText().toString();
                String uid = firebaseUser.getUid();
                String uname = firebaseUser.getDisplayName();
                String rate = getRating.getText().toString();
                Comment comment = new Comment(comment_content,uid,uname, rate);

                commentReference.setValue(comment).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        showMessage("comment added");
                        editTextComment.setText("");
                        btnAddComment.setVisibility(View.VISIBLE);
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        showMessage("fail to add comment : " + e.getMessage());
                    }
                });
            }
        });

        //mempush data dari viewholder lokasi
        PostKey = getIntent().getExtras().getString("lokasiid");

        String Image = getIntent().getExtras().getString("imageUrl") ;
        Glide.with(this).load(Image).into(img);

        String Nama = getIntent().getExtras().getString("nama");
        nama.setText(Nama);
        String Alamat = getIntent().getExtras().getString("alamat");
        alamat.setText(Alamat);

        String Video = getIntent().getExtras().getString("video");
        Uri uri = Uri.parse(Video);
        video.setVideoURI(uri);
        video.start();
        video.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mp.setLooping(true);
            }
        });

        String Tipe1 = getIntent().getExtras().getString("tipe1");
        tipe1.setText(Tipe1);
        String Tipe2 = getIntent().getExtras().getString("tipe2");
        tipe2.setText(Tipe2);
        String Tipe3 = getIntent().getExtras().getString("tipe3");
        tipe3.setText(Tipe3);

        String Harga1 = getIntent().getExtras().getString("harga1");
        harga1.setText(Harga1);
        String Harga2 = getIntent().getExtras().getString("harga2");
        harga2.setText(Harga2);
        String Harga3 = getIntent().getExtras().getString("harga3");
        harga3.setText(Harga3);

        String Nomor = getIntent().getExtras().getString("nomor");
        notelp = Nomor;

        String Lat = getIntent().getExtras().getString("latitude");
        String Lon = getIntent().getExtras().getString("longitude");
        lokasi = Lat + ", " + Lon;

        iniRvComment();
    }
    //ini komen
    private void iniRvComment() {
        RvComment.setLayoutManager(new LinearLayoutManager(this));
        DatabaseReference commentRef = firebaseDatabase.getReference(COMMENT_KEY).child(PostKey);
        commentRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                listComment = new ArrayList<>();
                for (DataSnapshot snap : dataSnapshot.getChildren()) {

                    Comment comment = snap.getValue(Comment.class);
                    listComment.add(comment);
                }

                commentAdapter = new CommentAdapter(getApplicationContext(), listComment);
                RvComment.setAdapter(commentAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
    }
    //menampilkan komen

    private void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }
    //tiemstamp komen

    private String timestampToString(long time) {
        Calendar calendar = Calendar.getInstance(Locale.ENGLISH);
        calendar.setTimeInMillis(time);
        String date = DateFormat.format("dd-MM-yyyy", calendar).toString();
        return date;
    }

    //telpon
    public void onCall(View v) {
        String number = notelp;
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:" + number));
        startActivity(intent);

    }

    //navigasi
    public void onNav(View v) {
        if (v.getId() == R.id.btn_nav) {
            gmmIntentUri = Uri.parse("google.navigation:q=" + lokasi);
            mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
            mapIntent.setPackage(goolgeMap);
            if (mapIntent.resolveActivity(getPackageManager()) != null) {
                startActivity(mapIntent);
            } else {
                Toast.makeText(LokasiViewActivity.this, "Google Maps Belum Terinstall", Toast.LENGTH_SHORT).show();
            }

        }
    }
}