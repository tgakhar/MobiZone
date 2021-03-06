package com.example.mobizone.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import com.example.mobizone.R;
import com.example.mobizone.adapter.OrderAdapter;
import com.example.mobizone.model.Order;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

/**
 * @author Patel Dhruv
 * @author Gakhar Tanvi
 * @author Sarbjit Kaur
 * @author Kamaljit Kaur
 * @author Akshay Varma
 * this java class is for Order History
 */
public class OrderhistoryActivity extends AppCompatActivity {
    /**
     * Object of FirebaseFirestore
     */
    FirebaseFirestore db;
    /**
     * Object of FirebaseAuth
     */
    FirebaseAuth auth;
    /**
     * Object of FirebaseUser
     */
    FirebaseUser user;
    /**
     * variable of toolbar
     */
    Toolbar toolbar;
    /**
     * variable of recyclerView
     */
    RecyclerView orderItemRecycler;
    /**
     * object of OrderAdapter
     */
    OrderAdapter orderAdapter;
    /**
     * Arraylist of Order type
     */
    final ArrayList<Order> productsList = new ArrayList<>();

    /**
     * onCreate
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orderhistory);
        toolbar=findViewById(R.id.toolbar_orderHistory);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        db=FirebaseFirestore.getInstance();
        auth=FirebaseAuth.getInstance();
        user=auth.getCurrentUser();
        getOrderHistory();
    }

    /**
     * For getting all old order details from Firestore
     */
    private void getOrderHistory() {
        db.collection("Users").document(user.getUid()).collection("Orders").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot document : task.getResult()) {

                        String name= (String) document.getData().get("ProductName");
                        String quantity= String.valueOf(document.getData().get("productQty"));
                        String apt= (String) document.getData().get("Apt");
                        String image= (String) document.getData().get("Image");
                        String address= (String) document.getData().get("Address");
                        String city= (String) document.getData().get("City");
                        String postal= (String) document.getData().get("Postal");
                        String price= String.valueOf(document.getData().get("price"));
                        String province=(String) document.getData().get("Province");

                        productsList.add(new Order(name, quantity, price,apt,postal,city,address,province,image));
                    }
                    setProdItemRecycler(productsList);
                }
            }
        });
    }

    /**
     *
     * @param productsList
     */
    private void setProdItemRecycler(ArrayList<Order> productsList) {
        orderItemRecycler = findViewById(R.id.recyclerview_orderHistory);
        RecyclerView.LayoutManager layoutManager= new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        orderItemRecycler.setLayoutManager(layoutManager);
        orderAdapter = new OrderAdapter(this, productsList);
        orderItemRecycler.setAdapter(orderAdapter);
    }
}