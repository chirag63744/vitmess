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


public class recycler extends AppCompatActivity {
    RecyclerView recyclerView;

    adapter_rec Adapter;
    FirebaseDatabase root;
    String regn;
    String sum;
    TextView t;
    Button b1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler);
        Intent i=getIntent();
        regn=i.getStringExtra("regno");
        sum=i.getStringExtra("sums");
        root=FirebaseDatabase.getInstance();
        recyclerView=findViewById(R.id.recycler1);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        FirebaseRecyclerOptions<user> options=new FirebaseRecyclerOptions.Builder<user>().setQuery(FirebaseDatabase.getInstance().getReference().child(regn),user.class).build();
        Adapter=new adapter_rec(options);
        recyclerView.setAdapter(Adapter);
        // t.setText(sum);
        b1=findViewById(R.id.button5);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i2=new Intent(recycler.this,payment.class);
                i2.putExtra("Sums",sum);

                startActivity(i2);
                Toast.makeText(recycler.this, ""+sum, Toast.LENGTH_SHORT).show();
            }
        });



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