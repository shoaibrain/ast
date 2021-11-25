package com.group7.astrobuff;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.group7.astrobuff.databinding.ActivityIssLocationBinding;

public class ISS_Location extends FragmentActivity implements OnMapReadyCallback {

    private static final int PERMISSIONS_FINE_LOCATION = 99;
    private GoogleMap mMap;
    private ActivityIssLocationBinding binding;
    //create IssModel object
    IssModel ISS = new IssModel();
    LatLng current = new LatLng(-34, 151);
    private FusedLocationProviderClient fusedLocationClient;




    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        //fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);

        binding = ActivityIssLocationBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());



        System.out.println("Hel");
        LocationRequest locationRequest;
        locationRequest = LocationRequest.create()
                .setInterval(100)
                .setFastestInterval(3000)
                .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
                .setMaxWaitTime(100);;
        locationRequest.setInterval(30000);

        FusedLocationProviderClient fusedLocationProviderClient;
        //updateGPS();

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(ISS_Location.this);
        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED)
        {
            //user provided permission
            fusedLocationProviderClient.getLastLocation().addOnSuccessListener(this, new OnSuccessListener<Location>() {
                @Override
                public void onSuccess(Location location)
                {
                    //we got permissions
                    System.out.println("Hellofghccgjfhgcjgcjgjgjg");

                    System.out.println(location.getLatitude());
                    current = new LatLng(location.getLatitude(), location.getLongitude());
                    updateUIValues(location);

                    //mapFragment.getMapAsync();

                }
            });
        }
        else
        {
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
            {
                requestPermissions(new String[] {Manifest.permission.ACCESS_FINE_LOCATION},PERMISSIONS_FINE_LOCATION);
            }
        }

        //Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        //updateGPS();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode)
        {
            case PERMISSIONS_FINE_LOCATION:
                if(grantResults[0] == PackageManager.PERMISSION_GRANTED)
                {
                    updateGPS();
                }
                else
                {
                    Toast.makeText(this,"This needs permission",Toast.LENGTH_SHORT).show();
                    finish();
                }
        }
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
    private void updateGPS()
    {
//        FusedLocationProviderClient fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(ISS_Location.this);
//        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED)
//        {
//            //user provided permission
//            fusedLocationProviderClient.getLastLocation().addOnSuccessListener(this, new OnSuccessListener<Location>() {
//                @Override
//                public void onSuccess(Location location)
//                {
//                    //we got permissions
//                    System.out.println("Hellofghccgjfhgcjgcjgjgjg");
//
//                    System.out.println(location.getLatitude());
//                    current = new LatLng(location.getLatitude(), location.getLongitude());
//                    updateUIValues(location);
//                }
//            });
//        }
//        else
//        {
//            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
//            {
//                requestPermissions(new String[] {Manifest.permission.ACCESS_FINE_LOCATION},PERMISSIONS_FINE_LOCATION);
//            }
//        }
    }

    private void updateUIValues(Location location)
    {
        current = new LatLng(-34, 151);
        //System.out.println("current: " + current.string());
        ISS.setLatitude(String.valueOf(location.getLatitude()));
        ISS.setLongitude(String.valueOf(location.getLongitude()));

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

//        //create IssModel object
//        IssModel ISS = new IssModel();
        //Update IssModel object's attributes with setters
        //We'll dynamically change these attributes once we fetch live data from the API
//        ISS.setLatitude("32.776665");
//        ISS.setLongitude("-96.796989");
//        ISS.setTimestamp(1636917881); //this is epoch time; can be changed later
//        ISS.setMessage("Message");

//imp
//        LocationRequest locationRequest;
//        locationRequest = LocationRequest.create()
//                .setInterval(100)
//                .setFastestInterval(3000)
//                .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
//                .setMaxWaitTime(100);;
//        locationRequest.setInterval(30000);
//imp

////////
//        FusedLocationProviderClient fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(ISS_Location.this);
//
//        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED)
//        {
//            //user provided permission
//            fusedLocationProviderClient.getLastLocation().addOnSuccessListener(this, new OnSuccessListener<Location>() {
//                @Override
//                public void onSuccess(Location location)
//                {
//                    //we got permissions
//                    System.out.println("Hellofghccgjfhgcjgcjgjgjg");
//
//                    System.out.println(location.getLatitude());
//                    System.out.println("Hgjfhgcjgcjgjgjg");
//                    current = new LatLng(location.getLatitude(), location.getLongitude());
//
//                    //updateUIValues(location);
//                    ISS.setLatitude(String.valueOf(location.getLatitude()));
//                    ISS.setLongitude(String.valueOf(location.getLongitude()));
//                }
//            });
//        }
//        else
//        {
//            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
//            {
//                requestPermissions(new String[] {Manifest.permission.ACCESS_FINE_LOCATION},PERMISSIONS_FINE_LOCATION);
//            }
//        }

////
        //google api location service
//       FusedLocationProviderClient fusedLocationProviderClient;
//       updateGPS();
        System.out.println("Hellofghccgjfhgcjgcjgjgjg433434");
        System.out.println(ISS.getLatitude());
        // Add a marker in Sydney and move the camera
        //LatLng sydney = new LatLng(-34, 151);
        if(ISS.getLatitude() != null && ISS.getLongitude() !=null )
        {
            //LatLng sydney = new LatLng(Double.parseDouble(ISS.getLatitude()), Double.parseDouble(ISS.getLongitude()));
            //LatLng sydney = new LatLng(location.getLatitude(), 151);
            mMap.addMarker(new MarkerOptions().position(current).title("Marker in Sydney"));
            mMap.moveCamera(CameraUpdateFactory.newLatLng(current));

        }
        System.out.println("Hellofghccgjfhgcjgcjgjgjg433434000000");



    }
}