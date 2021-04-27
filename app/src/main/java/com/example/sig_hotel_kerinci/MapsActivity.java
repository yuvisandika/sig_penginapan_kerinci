package com.example.sig_hotel_kerinci;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    FirebaseDatabase ref;
    DatabaseReference dataRef;
    String nama;
    Double lat, lng;

    ImageView home,phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        //menu home
        home = findViewById(R.id.home);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MapsActivity.this, HomeActivity.class);
                startActivity(intent);
            }
        });
        //menu phone
        phone = findViewById(R.id.phone);
        phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MapsActivity.this, HomeActivity.class);
                startActivity(intent);
            }
        });

        ref = FirebaseDatabase.getInstance();
        dataRef = ref.getReference().child("lokasi");
        nama = dataRef.child("nama").toString();

    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        dataRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                //Create an array of markers
                int size = (int) dataSnapshot.getChildrenCount(); //
                Marker[] allMarkers = new Marker[size];
                mMap.clear();//Assuming you're using mMap
                for (DataSnapshot ds: dataSnapshot.getChildren()){
                    //Specify your model class here
                    AdapterLokasi l = new AdapterLokasi();
                    //lets create a loop
                    for(int i=0;i<=size;i++){
                        try{
                            l.setLatitude(ds.getValue(AdapterLokasi.class).getLatitude());
                            l.setLongitude(ds.getValue(AdapterLokasi.class).getLongitude());
                            l.setNama(ds.getValue(AdapterLokasi.class).getNama());

                            String lat = l.getLatitude();
                            String lng = l.getLongitude();
                            double lati = Double.valueOf(lat);
                            double longi = Double.valueOf(lng);

                            String nama = l.getNama();

                            LatLng lokasi = new LatLng(lati, longi);
                            mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);

                            allMarkers[i] = mMap.addMarker(new MarkerOptions().position(lokasi).title(nama));
                            mMap.moveCamera(CameraUpdateFactory.newLatLng(lokasi));

                        }catch (Exception ex){}
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}