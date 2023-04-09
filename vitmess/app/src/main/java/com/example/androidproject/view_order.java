package com.example.androidproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.DataSetObserver;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;



public class view_order extends AppCompatActivity {

    FirebaseDatabase firebaseDatabase;
    DatabaseReference itemRef,QtyRef,priceRef;
    private TextView item,qty,price;
    String regn,path;
    int j=1;
    ListView listView;
  // adp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_order);

        Intent i=getIntent();
        regn=i.getStringExtra("regno");


//        ArrayList<String>items=(ArrayList<String>) getIntent().getSerializableExtra("itemss");
//       // t1=(TextView)findViewById(R.id.textView9);
        DatabaseReference ref=FirebaseDatabase.getInstance().getReference().child("Regno");
        firebaseDatabase = FirebaseDatabase.getInstance();
        item = findViewById(R.id.itemName);
        qty = findViewById(R.id.Quantity);
        price = findViewById(R.id.Price);
        path="Regno/"+regn;
        itemRef = firebaseDatabase.getReference(path+"/item"+1+"/item");
        QtyRef = firebaseDatabase.getReference(path+"/item"+1+"/quantity");
        priceRef = firebaseDatabase.getReference(path+"/item"+1+"/price");
        getData1();
        getData2();
    }

    private void getData2() {
        QtyRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
              int val=snapshot.getValue(int.class);

                qty.setText(""+val);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        priceRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                int val=snapshot.getValue(int.class);

                price.setText(""+val);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    private void getData1() {
        // calling add value event listener method
        // for getting the values from database.
            itemRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    // this method is call to get the realtime
                    // updates in the data.
                    // this method is called when the data is
                    // changed in our Firebase console.
                    // below line is for getting the data from
                    // snapshot of our database.
                    String value = snapshot.getValue(String.class);

                    // after getting the value we are setting
                    // our value to our text view in below line.
                    item.setText(value);

                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    // calling on cancelled method when we receive
                    // any error or we are not able to get the data.
                    Toast.makeText(view_order.this, "Fail to get data.", Toast.LENGTH_SHORT).show();

                }
            });



        }
}