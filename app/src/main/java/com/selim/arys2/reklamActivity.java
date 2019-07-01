package com.selim.arys2;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import static com.selim.arys2.konumActivity.latitude;
import static com.selim.arys2.konumActivity.longitude;


public class reklamActivity extends AppCompatActivity {

    FirebaseDatabase database;
    DatabaseReference myRef;

    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reklam);

        database = FirebaseDatabase.getInstance();
        myRef = database.getReference();

        //addDataFromFirebase();
        //getDataFromFirebase();
    }

    public void addDataFromFirebase(){

        UUID uuıd = UUID.randomUUID();
        String uuidString = uuıd.toString();

        DatabaseReference mRef =  database.getReference().child("Reklam").child(uuidString);
        mRef.child("FirmaID").setValue("1");
        mRef.child("FirmaAdi").setValue("teknosa");
        mRef.child("FirmaLat").setValue("40.694715");
        mRef.child("FirmaLog").setValue("29.625718");
        mRef.child("KampanyaIcerik").setValue("elektronik");
        mRef.child("KampanyaSuresi").setValue("2 gün");

        mRef =  database.getReference().child("Reklam").child(uuidString+"1");
        mRef.child("FirmaID").setValue("2");
        mRef.child("FirmaAdi").setValue("LC Waikiki");
        mRef.child("FirmaLat").setValue("40.695433");
        mRef.child("FirmaLog").setValue("29.625549");
        mRef.child("KampanyaIcerik").setValue("giyim");
        mRef.child("KampanyaSuresi").setValue("5 gün");

        mRef =  database.getReference().child("Reklam").child(uuidString+"2");
        mRef.child("FirmaID").setValue("3");
        mRef.child("FirmaAdi").setValue("dominos");
        mRef.child("FirmaLat").setValue("40.695672");
        mRef.child("FirmaLog").setValue("29.625047");
        mRef.child("KampanyaIcerik").setValue("yemek");
        mRef.child("KampanyaSuresi").setValue("1 gün");

    }

    /*public void getDataFromFirebase(){
        DatabaseReference newReference = database.getReference("Reklam");
        newReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for(DataSnapshot ds : dataSnapshot.getChildren()){
                    HashMap<String,String> hashMap =(HashMap<String, String>) ds.getValue();

                    System.out.println(distance(40.6936682,29.6243801, Double.parseDouble(hashMap.get("FirmaLat")), Double.parseDouble(hashMap.get("FirmaLog")), 'K') + " gerçek mesafe(q7)\n");
                    double mesafe=distance(latitude,longitude, Double.parseDouble(hashMap.get("FirmaLat")), Double.parseDouble(hashMap.get("FirmaLog")), 'K');
                    System.out.println(mesafe + " hesaplanan mesafe(q7)\n");
                    System.out.println(latitude+"   "+longitude+" hesaplanan(q7)");
                    System.out.println("40.6936682   29.6243801 gerçek(q7)");

                    if(true){
                        System.out.println("bjk:"+hashMap.get("FirmaID"));
                        System.out.println("bjk:"+hashMap.get("FirmaAdi"));
                        System.out.println("bjk:"+hashMap.get("FirmaLat"));
                        System.out.println("bjk:"+hashMap.get("FirmaLog"));
                        System.out.println("bjk:"+hashMap.get("KampanyaIcerik"));
                        System.out.println("bjk:"+hashMap.get("KampanyaSuresi"));

                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }*/

    List<String> kampanyalar = new ArrayList<String>();
    public void yemekButon(View view){

        textView=findViewById(R.id.editText2);
        kampanyalar.clear();

        DatabaseReference newReference = database.getReference("Reklam");
        newReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot ds : dataSnapshot.getChildren()){
                    HashMap<String,String> hashMap =(HashMap<String, String>) ds.getValue();

                    System.out.println(distance(40.6943393,29.6242943, Double.parseDouble(hashMap.get("FirmaLat")), Double.parseDouble(hashMap.get("FirmaLog")), 'K') + " gerçek mesafe(q7)\n");
                    double mesafe=distance(latitude,longitude, Double.parseDouble(hashMap.get("FirmaLat")), Double.parseDouble(hashMap.get("FirmaLog")), 'K');
                    System.out.println(mesafe + " hesaplanan mesafe(q7)\n");
                    System.out.println(latitude+"   "+longitude+" hesaplanan(q7)");
                    System.out.println("40.6936682   29.6243801 gerçek(q7)");

                    if(hashMap.get("KampanyaIcerik").equalsIgnoreCase("yemek") && mesafe<=Integer.parseInt(textView.getText().toString())){
                        System.out.println("bjk:"+hashMap.get("FirmaID"));
                        System.out.println("bjk:"+hashMap.get("FirmaAdi"));
                        System.out.println("bjk:"+hashMap.get("FirmaLat"));
                        System.out.println("bjk:"+hashMap.get("FirmaLog"));
                        System.out.println("bjk:"+hashMap.get("KampanyaIcerik"));
                        System.out.println("bjk:"+hashMap.get("KampanyaSuresi"));
                        System.out.println("bjk_________________________________");
                        kampanyalar.add(hashMap.get("FirmaAdi")+"  "+hashMap.get("KampanyaIcerik")+"  "+hashMap.get("KampanyaSuresi"));
                    }
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }


        });
    }

    public void deneme(View view){
        ListView listView = findViewById(R.id.listView);
        ArrayAdapter<String> veriAdaptoru=new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1 , kampanyalar);
        listView.setAdapter(veriAdaptoru);
        System.out.println("abcc::::"+kampanyalar);
    }


    public void giyimButon(View view){
        textView=findViewById(R.id.editText2);
        kampanyalar.clear();
        DatabaseReference newReference = database.getReference("Reklam");
        newReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for(DataSnapshot ds : dataSnapshot.getChildren()){
                    HashMap<String,String> hashMap =(HashMap<String, String>) ds.getValue();

                    System.out.println(distance(40.6943393,29.6242943, Double.parseDouble(hashMap.get("FirmaLat")), Double.parseDouble(hashMap.get("FirmaLog")), 'K') + " gerçek mesafe(q7)\n");
                    double mesafe=distance(latitude,longitude, Double.parseDouble(hashMap.get("FirmaLat")), Double.parseDouble(hashMap.get("FirmaLog")), 'K');
                    System.out.println(mesafe + " hesaplanan mesafe(q7)\n");
                    System.out.println(latitude+"   "+longitude+" hesaplanan(q7)");
                    System.out.println("40.6936682   29.6243801 gerçek(q7)");

                    if(hashMap.get("KampanyaIcerik").equalsIgnoreCase("giyim") && mesafe<=Integer.parseInt(textView.getText().toString())){
                        System.out.println("bjk:"+hashMap.get("FirmaID"));
                        System.out.println("bjk:"+hashMap.get("FirmaAdi"));
                        System.out.println("bjk:"+hashMap.get("FirmaLat"));
                        System.out.println("bjk:"+hashMap.get("FirmaLog"));
                        System.out.println("bjk:"+hashMap.get("KampanyaIcerik"));
                        System.out.println("bjk:"+hashMap.get("KampanyaSuresi"));
                        System.out.println("bjk_________________________________");
                        kampanyalar.add(hashMap.get("FirmaAdi")+"  "+hashMap.get("KampanyaIcerik")+"  "+hashMap.get("KampanyaSuresi"));
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
    public void elektronikButon(View view){
        textView=findViewById(R.id.editText2);
        kampanyalar.clear();
        DatabaseReference newReference = database.getReference("Reklam");
        newReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for(DataSnapshot ds : dataSnapshot.getChildren()){
                    HashMap<String,String> hashMap =(HashMap<String, String>) ds.getValue();

                    System.out.println(distance(40.6943393,29.6242943, Double.parseDouble(hashMap.get("FirmaLat")), Double.parseDouble(hashMap.get("FirmaLog")), 'K') + " gerçek mesafe(q7)\n");
                    double mesafe=distance(latitude,longitude, Double.parseDouble(hashMap.get("FirmaLat")), Double.parseDouble(hashMap.get("FirmaLog")), 'K');
                    System.out.println(mesafe + " hesaplanan mesafe(q7)\n");
                    System.out.println(latitude+"   "+longitude+" hesaplanan(q7)");
                    System.out.println("40.6936682   29.6243801 gerçek(q7)");

                    if(hashMap.get("KampanyaIcerik").equalsIgnoreCase("elektronik") && mesafe<=Integer.parseInt(textView.getText().toString())){
                        System.out.println("bjk:"+hashMap.get("FirmaID"));
                        System.out.println("bjk:"+hashMap.get("FirmaAdi"));
                        System.out.println("bjk:"+hashMap.get("FirmaLat"));
                        System.out.println("bjk:"+hashMap.get("FirmaLog"));
                        System.out.println("bjk:"+hashMap.get("KampanyaIcerik"));
                        System.out.println("bjk:"+hashMap.get("KampanyaSuresi"));
                        System.out.println("bjk_________________________________");
                        kampanyalar.add(hashMap.get("FirmaAdi")+"  "+hashMap.get("KampanyaIcerik")+"  "+hashMap.get("KampanyaSuresi"));
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
    public void kozmetikButon(View view){
        textView=findViewById(R.id.editText2);
        kampanyalar.clear();
        DatabaseReference newReference = database.getReference("Reklam");
        newReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for(DataSnapshot ds : dataSnapshot.getChildren()){
                    HashMap<String,String> hashMap =(HashMap<String, String>) ds.getValue();

                    System.out.println(distance(40.6943393,29.6242943, Double.parseDouble(hashMap.get("FirmaLat")), Double.parseDouble(hashMap.get("FirmaLog")), 'K') + " gerçek mesafe(q7)\n");
                    double mesafe=distance(latitude,longitude, Double.parseDouble(hashMap.get("FirmaLat")), Double.parseDouble(hashMap.get("FirmaLog")), 'K');
                    System.out.println(mesafe + " hesaplanan mesafe(q7)\n");
                    System.out.println(latitude+"   "+longitude+" hesaplanan(q7)");
                    System.out.println("40.6936682   29.6243801 gerçek(q7)");

                    if(hashMap.get("KampanyaIcerik").equalsIgnoreCase("kozmetik") && mesafe<=Integer.parseInt(textView.getText().toString())){
                        System.out.println("bjk:"+hashMap.get("FirmaID"));
                        System.out.println("bjk:"+hashMap.get("FirmaAdi"));
                        System.out.println("bjk:"+hashMap.get("FirmaLat"));
                        System.out.println("bjk:"+hashMap.get("FirmaLog"));
                        System.out.println("bjk:"+hashMap.get("KampanyaIcerik"));
                        System.out.println("bjk:"+hashMap.get("KampanyaSuresi"));
                        System.out.println("bjk_________________________________");
                        kampanyalar.add(hashMap.get("FirmaAdi")+"  "+hashMap.get("KampanyaIcerik")+"  "+hashMap.get("KampanyaSuresi"));
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    public void aramaButon(View view){
        textView=findViewById(R.id.editText2);
        kampanyalar.clear();
         final TextView textView1=findViewById(R.id.editText7);
         final String magaza= textView1.getText().toString();

        kampanyalar.clear();
        DatabaseReference newReference = database.getReference("Reklam");
        newReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for(DataSnapshot ds : dataSnapshot.getChildren()){
                    HashMap<String,String> hashMap =(HashMap<String, String>) ds.getValue();

                    System.out.println(distance(40.6943393,29.6242943, Double.parseDouble(hashMap.get("FirmaLat")), Double.parseDouble(hashMap.get("FirmaLog")), 'K') + " gerçek mesafe(q7)\n");
                    double mesafe=distance(latitude,longitude, Double.parseDouble(hashMap.get("FirmaLat")), Double.parseDouble(hashMap.get("FirmaLog")), 'K');
                    System.out.println(mesafe + " hesaplanan mesafe(q7)\n");
                    System.out.println(latitude+"   "+longitude+" hesaplanan(q7)");
                    System.out.println("40.6936682   29.6243801 gerçek(q7)");

                    if(hashMap.get("FirmaAdi").equalsIgnoreCase(magaza) && mesafe<=Integer.parseInt(textView.getText().toString())){
                        System.out.println("bjk:"+hashMap.get("FirmaID"));
                        System.out.println("bjk:"+hashMap.get("FirmaAdi"));
                        System.out.println("bjk:"+hashMap.get("FirmaLat"));
                        System.out.println("bjk:"+hashMap.get("FirmaLog"));
                        System.out.println("bjk:"+hashMap.get("KampanyaIcerik"));
                        System.out.println("bjk:"+hashMap.get("KampanyaSuresi"));
                        System.out.println("bjk_________________________________");
                        kampanyalar.add(hashMap.get("FirmaAdi")+"  "+hashMap.get("KampanyaIcerik")+"  "+hashMap.get("KampanyaSuresi"));
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }




    private double distance(double lat1, double lon1, double lat2, double lon2, char unit) {

        double theta = lon1 - lon2;
        double dist = Math.sin(deg2rad(lat1)) * Math.sin(deg2rad(lat2)) + Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2)) * Math.cos(deg2rad(theta));
        dist = Math.acos(dist);
        dist = rad2deg(dist);
        dist = dist * 60 * 1.1515;
        if (unit == 'K') {
            dist = dist * 1.609344 * 1000;
        }
        return (dist);
    }
    /*:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/
    /*::  This function converts decimal degrees to radians             :*/
    /*:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/
    private double deg2rad(double deg) {
        return (deg * Math.PI / 180.0);
    }
    /*:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/
    /*::  This function converts radians to decimal degrees             :*/
    /*:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/
    private double rad2deg(double rad) {
        return (rad * 180.0 / Math.PI);
    }


}
