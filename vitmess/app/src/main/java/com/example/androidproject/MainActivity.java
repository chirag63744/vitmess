package com.example.androidproject;

import android.content.Intent;


import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {
   LinearLayout l1,l3,l4;
    TextView t1;
    String regno;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //show rating dialog

        l1=(LinearLayout) findViewById(R.id.first);
        l3=(LinearLayout) findViewById(R.id.third);
        l4=(LinearLayout) findViewById(R.id.fourth);
        t1=(TextView) findViewById(R.id.Reg);
        Intent i=getIntent();
        regno=i.getStringExtra("Regnos");
        t1.setText(regno);

//        if(getSupportActionBar()==null){
//            getSupportActionBar().show();
//        }

        l1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(MainActivity.this,order_food.class);
                i.putExtra("Regno",regno);
                startActivity(i);
            }
        });


        l3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(MainActivity.this,Order_history.class);
                i.putExtra("Regno",regno);
                startActivity(i);

            }
        });

        l4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent i=new Intent(MainActivity.this,Feedback.class);
//                i.putExtra("Regno",regno);
//                startActivity(i);

                Feedback feedback = new Feedback(MainActivity.this);
                feedback.getWindow().setBackgroundDrawable(new ColorDrawable(getResources().getColor(android.R.color.transparent)));
                feedback.setCancelable(false);
                feedback.show();

            }
        });




    }
}