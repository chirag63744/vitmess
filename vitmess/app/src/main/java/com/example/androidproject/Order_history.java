package com.example.androidproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;


public class Order_history extends AppCompatActivity {
    RecyclerView recyclerView;

    adapter_rec2 Adapter;
    FirebaseDatabase root;
    String regn;
    String sum;
    TextView t;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_history2);
        Intent i =getIntent();

        regn=i.getStringExtra("Regno");  
        root=FirebaseDatabase.getInstance();
        recyclerView=findViewById(R.id.recycler1);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        FirebaseRecyclerOptions<user2> options=new FirebaseRecyclerOptions.Builder<user2>().setQuery(FirebaseDatabase.getInstance().getReference().child(regn),user2.class).build();
        Adapter=new adapter_rec2(options);
        recyclerView.setAdapter(Adapter);
        // t.setText(sum);




    }
    @Override protected void onStart() {

        super.onStart();
        Adapter.startListening();
    }
    @Override protected void onStop()
    {
        super.onStop();
        Adapter.stopListening();
    }
}