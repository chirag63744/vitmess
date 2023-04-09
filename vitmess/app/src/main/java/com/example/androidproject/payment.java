package com.example.androidproject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.*;

import androidx.appcompat.app.AppCompatActivity;

import com.razorpay.Checkout;
import com.razorpay.PaymentResultListener;

import org.json.JSONObject;


public class payment extends AppCompatActivity implements PaymentResultListener {

    Button b1;
    String sum1;
    int sum2,sum3;
   TextView t7;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.payment);
      Intent i=getIntent();
       sum1=i.getStringExtra("Sums");
       t7=(TextView)findViewById(R.id.textView10);
       t7.setText(sum1);


        b1=findViewById(R.id.button8);

        Checkout.preload(getApplicationContext());
        sum2=Integer.parseInt(sum1);
        sum3=sum2*100;

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startpayment();

            }
        });



    }
    private void startpayment()
    {

        /**
         * Instantiate Checkout
         */
        Checkout checkout = new Checkout();
        checkout.setKeyID("rzp_test_vxnqjGcBxoC8GH");

        /**
         * Set your logo here
         */
        checkout.setImage(R.drawable.ic_baseline_payment_24);

        /**
         * Reference to current activity
         */
        final Activity activity = this;

        /**
         * Pass your payment options to the Razorpay Checkout as a JSONObject
         */
        try {
            JSONObject options = new JSONObject();

            options.put("name", "vitMess");
            options.put("description", "Reference No. #123456");
            options.put("image", "https://s3.amazonaws.com/rzp-mobile/images/rzp.jpg");
           // options.put("order_id", "order_DBJOWzybf0sJbb");//from response of step 3.
            options.put("theme.color", "#3399cc");
            options.put("currency", "INR");
            options.put("amount", sum3);//pass amount in currency subunits
            options.put("prefill.email", "chirag.garg63744@gmail.com");
            options.put("prefill.contact","6374486837");
            JSONObject retryObj = new JSONObject();
            retryObj.put("enabled", true);
            retryObj.put("max_count", 4);
            options.put("retry", retryObj);

            checkout.open(activity, options);

        } catch(Exception e) {
            Log.e("TAG", "Error in starting Razorpay Checkout", e);
        }
    }


    @Override
    public void onPaymentSuccess(String s) {
        Toast.makeText(this, "Successful", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onPaymentError(int i, String s) {
        Toast.makeText(this, "An Error Occurred", Toast.LENGTH_SHORT).show();

    }
}

