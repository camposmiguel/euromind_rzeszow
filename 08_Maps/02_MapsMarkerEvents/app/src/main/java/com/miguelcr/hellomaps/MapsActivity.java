package com.miguelcr.hellomaps;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, GoogleMap.OnMarkerDragListener, GoogleMap.OnMapClickListener {

    private GoogleMap mMap;
    private int counter = 1;
    LatLng lastPosition = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
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

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(-34, 151);

        Marker marker = mMap.addMarker(new MarkerOptions()
                .position(sydney)
                .title("Marker in Sydney")
                .draggable(true)
        );


        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));

        mMap.setOnMarkerDragListener(this);

        mMap.setOnMapClickListener(this);


    }

    @Override
    public void onMarkerDragStart(Marker marker) {

        marker.hideInfoWindow();
    }

    @Override
    public void onMarkerDrag(Marker marker) {
        LatLng currentPosition = marker.getPosition();
        Log.i("LOC","LOC >> lat:"+currentPosition.latitude
                +",lon:"+currentPosition.longitude );
    }

    @Override
    public void onMarkerDragEnd(Marker marker) {
        marker.showInfoWindow();
        LatLng currentPosition = marker.getPosition();
        marker.setSnippet("lat,lon:"+currentPosition.latitude
                +",:"+currentPosition.longitude);
    }

    @Override
    public void onMapClick(LatLng latLng) {
        Marker marker = mMap.addMarker(new MarkerOptions()
                .position(latLng)
                .title("Marker "+counter)
                .draggable(true)
        );

        mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));

        counter++;

        marker.showInfoWindow();

        if(lastPosition!=null) {

            PolylineOptions rectOptions = new PolylineOptions()
                    .add(lastPosition)
                    .add(latLng);

            Polyline polyline = mMap.addPolyline(rectOptions);
        }

        lastPosition = latLng;

    }
}
