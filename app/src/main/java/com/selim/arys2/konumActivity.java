package com.selim.arys2;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.maps.model.LatLng;
import com.google.firebase.auth.FirebaseAuth;


public class konumActivity extends AppCompatActivity {


    private LocationManager locationManager;
    private LocationListener locationListener;

    public LatLng userLocation;
    static double latitude;
    static double longitude;

    static String adres;
    private double latitude1;
    private double longitude1;



    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ikinciadim);
        View view = null;
        konumBul(view);

    }

    public void changeButton(View view) {
        Intent intent = new Intent(getApplicationContext(),YeniParolaActivity.class);
        startActivity(intent);
    }



    public void konumBul(View view){

        //System.out.println(m.userLocation.toString());
        locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);

        locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {

                userLocation = new LatLng(location.getLatitude(),location.getLongitude());
                System.out.println("location: "+location.toString());
                latitude1 = location.getLatitude();
                //setLatitude(location.getLatitude());
                longitude1 = location.getLongitude();
                //setLongitude(location.getLongitude());
                System.out.println("kübra2: "+latitude1+"_____"+longitude1);

            }
            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {

            }

            @Override
            public void onProviderEnabled(String provider) {

            }

            @Override
            public void onProviderDisabled(String provider) {

            }
        };


        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[] {android.Manifest.permission.ACCESS_FINE_LOCATION},1);
        } else {
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,0,0,locationListener);
            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, locationListener);
        }

        TextView textView1 = findViewById(R.id.editText3);
        TextView textView2 = findViewById(R.id.editText4);
        String s = String.valueOf(latitude1);
        textView1.setText(s);
        String s1 = String.valueOf(longitude1);
        textView2.setText(s1);

        Button button = findViewById(R.id.button4);
        button.setVisibility(1);

    }

    public void konumGir(View view){
        //Intent intent = new Intent(getApplicationContext(),MapsActivity.class);
        //startActivity(intent);

        Button button = findViewById(R.id.button6);
        button.setVisibility(1);
    }

    public void konumGetir(View view){

        TextView textView1 = findViewById(R.id.editText5);
        TextView textView2 = findViewById(R.id.editText6);
        TextView textView3 = findViewById(R.id.textView7);
        //String s = String.valueOf(latitudeKonumBul);
        //textView1.setText(s);
        //String s1 = String.valueOf(longitudeKonumBul);
        //textView2.setText(s1);
        textView3.setText(adres);
    }

    public void ilerle1(View view){
        Intent intent = new Intent(getApplicationContext(),reklamActivity.class);
        startActivity(intent);

        latitude=latitude1;
        longitude=longitude1;

    }

    public void ilerle2(View view){
        Intent intent = new Intent(getApplicationContext(),reklamActivity.class);
        startActivity(intent);

        //latitude=latitudeKonumBul;
        //longitude=longitudeKonumBul;
    }

}
