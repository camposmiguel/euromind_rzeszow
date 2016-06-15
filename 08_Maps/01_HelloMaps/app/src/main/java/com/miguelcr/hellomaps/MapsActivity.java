package com.miguelcr.hellomaps;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

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
        LatLng zstPosition = new LatLng(50.015141,21.978439);
        LatLng jakubPosition = new LatLng(37.380338,-6.006972);

        mMap.addMarker(new MarkerOptions()
                .position(zstPosition)
                .title("Zespół Szkół Technicznych")
                .snippet("(ZST for friends ;)")
        );

        mMap.addMarker(new MarkerOptions()
                .position(jakubPosition)
                .title("The overclok student is here")
                .snippet("(Not really overclock ;)")
        );

        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(zstPosition,5));

        PolylineOptions rectOptions = new PolylineOptions()
                .add(zstPosition)
                .add(jakubPosition)
                .width(20);

        // Get back the mutable Polyline
        Polyline polyline = mMap.addPolyline(rectOptions);

        // Enable the zoom controls in the map
        mMap.getUiSettings().setZoomControlsEnabled(true);

        // Circle
        CircleOptions circleOptions = new CircleOptions()
                .center(zstPosition)
                .strokeWidth(30)
                .strokeColor(R.color.colorPrimaryDark)
                .fillColor(R.color.colorPrimary)
                .radius(100); // In meters

        // Get back the mutable Circle
        Circle circle = mMap.addCircle(circleOptions);

        // Circle
        CircleOptions circleOptionsSeville = new CircleOptions()
                .center(jakubPosition)
                .radius(100); // In meters

        // Get back the mutable Circle
        Circle circleSeville = mMap.addCircle(circleOptionsSeville);




    }
}
