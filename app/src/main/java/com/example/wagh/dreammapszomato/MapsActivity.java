package com.example.wagh.dreammapszomato;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.common.SignInButton;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import static android.location.LocationManager.GPS_PROVIDER;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    EditText searchmaintext;
    Button searchmainbutton;

    String searchdata;

    //Location required variables

    Double Latitude, Longitude;

    //data required for a json request


    String zomatoapikey = "c72b6530292b5605047b7ed56789279e";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        searchmaintext = (EditText) findViewById(R.id.mainsearchedit);
        searchmainbutton = (Button) findViewById(R.id.mainsearchbutton);


        //button in use

        searchmainbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                locationcheck();

            }
        });


    }


    void locationcheck() {
        //location gps enabling
/**
 Intent gpson=new Intent("android.location.GPS_ENABLED_CHANGE");
 gpson.putExtra("enabled",true);
 sendBroadcast(gpson);

 **/
        //Location is used

        try {

            LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

            LocationListener locationListener1 = new LocationListener() {
                @Override
                public void onLocationChanged(Location location) {

                    Latitude = location.getLatitude();
                    Longitude = location.getLongitude();

                    Log.d("LOCATION Latitude", Latitude.toString());
                    Log.d("LOCATION Longitude", Longitude.toString());

                }

            };


            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,0,10,);

        }

        catch (Exception e)
        {
            Log.e("GPS","ERROR",e);
        }

        //location gps disable
/**
        Intent gpsoff =new Intent("android.location.GPS_ENABLED_CHANGE");
        gpsoff.putExtra("enabled",false);
        sendBroadcast(gpsoff);
**/
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
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }
}
