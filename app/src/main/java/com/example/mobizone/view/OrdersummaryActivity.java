package com.example.mobizone.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.mobizone.R;

/**
 * @author Patel Dhruv
 * @author Gakhar Tanvi
 * @author Kaur Sarbjit
 * @author Kaur Kamaljit
 * @author Varma Akshay
 * Class for Order Summary
 */

public class OrdersummaryActivity extends AppCompatActivity {

    /**
     * Variable of TextView
     */
    TextView stotal,Tp;

    /**
     * Variable of checkout button
     */
    Button btnCheckout;

    /**
     * Variable of toolbar
     */
    Toolbar toolbar;

    /**
     * onCreate method
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ordersummary);
        stotal=findViewById(R.id.sTotal);
        Tp=findViewById(R.id.Tp);
        toolbar=findViewById(R.id.toolbar_orderSummary);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        btnCheckout=findViewById(R.id.btn_checkOut);
        final Bundle b = getIntent().getExtras();
        final double price = b.getDouble("total");
        stotal.setText("$ "+price);
        double total = price;
        total =  total+((price*15)/100)+4;

        Tp.setText("$ "+total);

        btnCheckout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),AddressActivity.class);
                i.putExtras(b);
                startActivity(i);
            }
        });
    }
}