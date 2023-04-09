package com.example.androidproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;

import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class order_food extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    Button b,b1,b2,b4;
    String data,temp,regn;
    ArrayList<String>items=new ArrayList<>();
    int i=1;
    TextView e;
    int price,count=0,sum=0;
    TextView t;
    FirebaseDatabase root;
    DatabaseReference ref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_food);
        Spinner spinner = findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.Food, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);
        Intent j=getIntent();
        regn=j.getStringExtra("Regno");


        b = findViewById(R.id.button);
        b1=findViewById(R.id.button2);
        b2=findViewById(R.id.button3);
        b4=findViewById(R.id.button4);
        e=(TextView) findViewById(R.id.editTextTextPersonName);
        e.setText(regn);

        t=(TextView)findViewById(R.id.textView7);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count=count+1;
                t.setText(""+count);

            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(count>0)
                {
                    count=count-1;
                }
                else
                    count=0;

                t.setText(""+count);
            }
        });


        b.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {
                if (TextUtils.isEmpty(e.getText().toString()) || t.getText().toString().equals("0")) {
                    Toast.makeText(order_food.this, "Details not Entered", Toast.LENGTH_SHORT).show();
                } else {
                    root = FirebaseDatabase.getInstance();
                    ref = root.getReferenceFromUrl("https://fir-connection-abaa7-default-rtdb.firebaseio.com/");

                    userhelper help = new userhelper(data, count, "" + price + "*" + count + ":" + price * count);
                    ref.child(e.getText().toString()).child("item" + i).setValue(help);
                    sum = sum + price * count;
                    i = i + 1;

                    t.setText("" + 0);
                    Toast.makeText(order_food.this, data + " added ", Toast.LENGTH_SHORT).show();


                }
            }
        });
        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(order_food.this,recycler.class);
                intent.putExtra("regno",e.getText().toString());
                temp=Integer.toString(sum);
                intent.putExtra("sums",temp);
                startActivity(intent);




            }
        });




    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        data = parent.getItemAtPosition(position).toString();
        items.add(data);
        int[]rate={10,20,35,55,120,140,40,30,40,25,200,160,90,94,69,45};
        String[] Order = getResources().getStringArray(R.array.Food);
        for (int i = 0; i < 16; i++) {
            if (data.equals(Order[i])) {
                price = rate[i];
            }
            else
            {
                //do nothing
            }

        }
        count=0;
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}





















