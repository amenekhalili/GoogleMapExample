package com.example.googlemapexample;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;

import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.widget.SearchView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;

public class MainActivity extends FragmentActivity implements OnMapReadyCallback {

    GoogleMap mGoogleMap ;
    SupportMapFragment mMapFragment;
    SearchView mSearchView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSearchView = findViewById(R.id.sv_location);
        mMapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.google_map);

        mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                String location = mSearchView.getQuery().toString();
                List<Address> addressList = null;

                if(location != null || location.equals("")){
                    Geocoder geocoder = new Geocoder(MainActivity.this);
                    try {
                        addressList = geocoder.getFromLocationName(location,1);
                    }catch (IOException e){
                        e.printStackTrace();
                    }
                    Address address = addressList.get(0);
                    LatLng latLng = new LatLng(address.getLatitude() , address.getLongitude());
                    mGoogleMap.addMarker(new MarkerOptions().position(latLng).title(location));
                    mGoogleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng , 10));
                }

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        mMapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady( GoogleMap googleMap) {
        mGoogleMap = googleMap;

    }
}