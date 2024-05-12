package com.example.elektronikaiszakszerviz2;

import android.content.res.TypedArray;
import android.os.Bundle;
import android.util.Log;
import android.widget.DatePicker;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;
import java.util.Date;

public class CartActivity extends AppCompatActivity {


    private FirebaseUser mAuth;
    private RecyclerView recyclerView;
    private ArrayList<Appointment> items;

    private CartAdapter adapter;

    private DatePicker datePicker;

    private FirebaseFirestore mFirestore;

    private CollectionReference mAppointments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_cart);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        mAuth = FirebaseAuth.getInstance().getCurrentUser();

        if (mAuth == null) {
            finish();
            return;
        }

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 1));
        items = new ArrayList<>();
        adapter = new CartAdapter(this, items);
        recyclerView.setAdapter(adapter);

        mFirestore = FirebaseFirestore.getInstance();
        mAppointments = mFirestore.collection("basket");

        initData();
    }


    public void initData(){

        items.clear();

        mAppointments.whereEqualTo("user", mAuth.getUid()).get().addOnSuccessListener(queryDocumentSnapshots -> {

            for(QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots){
                Appointment appointment = documentSnapshot.toObject(Appointment.class);
                Log.i("Appointment", appointment.getUser()+" "+appointment.getTitle());
                items.add(appointment);
            }
            adapter.notifyDataSetChanged();

        });

    }

}