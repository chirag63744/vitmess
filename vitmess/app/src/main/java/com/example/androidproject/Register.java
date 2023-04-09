package com.example.androidproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Register extends AppCompatActivity {

    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("users");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        final EditText fullname = findViewById(R.id.fullname);
        final EditText email = findViewById(R.id.email);
        final EditText regno = findViewById(R.id.regno);
        final EditText password = findViewById(R.id.password);
        final EditText conPassword = findViewById(R.id.conPassword);

        final Button registerBtn = findViewById(R.id.registerBtn);
        final TextView loginNowBtn = findViewById(R.id.loginNow);

        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String fullnameTxt = fullname.getText().toString();
                final String emailTxt = email.getText().toString();
                final String regTxt = regno.getText().toString();
                final String passwordTxt = password.getText().toString();
                final String conPasswordTxt = conPassword.getText().toString();


                if (fullnameTxt.isEmpty() || emailTxt.isEmpty() || regTxt.isEmpty() || passwordTxt.isEmpty()){
                    Toast.makeText(Register.this, "Please Fill all the Fields", Toast.LENGTH_SHORT).show();
                }

                else if(validate(emailTxt)==false)
                {
                    Toast.makeText(Register.this, "Invalid Email id", Toast.LENGTH_SHORT).show();
                }
                else if(!passwordTxt.equals(conPasswordTxt)){
                    Toast.makeText(Register.this,"Passwords are not matching" ,Toast.LENGTH_SHORT).show();
                }
                else{
                    databaseReference.child("users").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if(snapshot.hasChild(regTxt)){
                                Toast.makeText(Register.this, "User is already registered", Toast.LENGTH_SHORT).show();
                            }
                            else{
                                databaseReference.child("users").child(regTxt).child("fullname").setValue(fullnameTxt);
                                databaseReference.child("users").child(regTxt).child("email").setValue(emailTxt);
                                databaseReference.child("users").child(regTxt).child("password").setValue(passwordTxt);

                                Toast.makeText(Register.this, "User registered successfully", Toast.LENGTH_SHORT).show();
                                finish();
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });

                }
            }
        });
        loginNowBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(Register.this,Login.class);
                startActivity(i);


            }
        });
    }

    private boolean validate(String emailTxt) {
        if(!emailTxt.isEmpty()&& Patterns.EMAIL_ADDRESS.matcher(emailTxt).matches())
        {
            Toast.makeText(this, "Correct emailid", Toast.LENGTH_SHORT).show();
            return true;
        }
        else
        {

            return false;
  }

}
}