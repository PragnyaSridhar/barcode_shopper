package com.example.p7;

import android.nfc.Tag;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

// import com.google.firebase.database.DataSnapshot;
// import com.google.firebase.database.DatabaseError;
// import com.google.firebase.database.DatabaseReference;
// import com.google.firebase.database.FirebaseDatabase;
// import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class bill extends AppCompatActivity {

    //DatabaseReference myref;
    RecyclerView recyclerView;
    productAdapter adapter;


    List<product> productList;
    //List<String> barcs;
    ArrayList<String> barcs;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bill);


        Bundle bundle = getIntent().getExtras();
        barcs = (ArrayList<String>) bundle.getStringArrayList("barc");


        //myref=FirebaseDatabase.getInstance().getReference("barcode-19a3f");
        productList= new ArrayList<>();

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter=new productAdapter(this,productList);
        recyclerView.setAdapter(adapter);

        // myref.addValueEventListener(new ValueEventListener() {
        //     @Override
        //     public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
        //         productList.clear();
        //         if(dataSnapshot.exists()){
        //             for(DataSnapshot snapshot : dataSnapshot.getChildren()){
        //                 product p=snapshot.getValue(product.class);
        //                 productList.add(p);
        //             }
        //         }
        //     }

        //     @Override
        //     public void onCancelled(@NonNull DatabaseError databaseError) {

        //     }
        // });

    }

}
