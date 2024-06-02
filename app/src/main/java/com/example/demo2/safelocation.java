package com.example.demo2;

import androidx.fragment.app.FragmentActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.example.demo2.databinding.ActivitySafelocationBinding;
import com.google.maps.model.PlacesSearchResult;

public class safelocation extends FragmentActivity implements OnMapReadyCallback {

    private Context mContext;
    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        PlacesSearchResult[] placesSearchResults = new NearbySearch().run().results;
        Log.e("response1Tag", placesSearchResults[0].toString());
        Log.e("response2Tag", placesSearchResults[1].toString());
        double lat1 = placesSearchResults[0].geometry.location.lat;
        double lng1 = placesSearchResults[0].geometry.location.lng;
        double lat2 = placesSearchResults[1].geometry.location.lat;
        double lng2 = placesSearchResults[1].geometry.location.lng;
        mMap.addMarker(new MarkerOptions().position(new LatLng(lat1, lng1)));
        mMap.addMarker(new MarkerOptions().position(new LatLng(lat2, lng2)));
        mMap.setMinZoomPreference(14.0f);
        mMap.moveCamera(CameraUpdateFactory.newLatLng(new LatLng(lat1, lng1)));
    }
}