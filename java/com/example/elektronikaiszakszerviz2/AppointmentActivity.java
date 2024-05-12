package com.example.elektronikaiszakszerviz2;

import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.util.Log;
import android.widget.DatePicker;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.Date;

public class AppointmentActivity extends AppCompatActivity  {

    private FirebaseUser mAuth;
    private RecyclerView recyclerView;
    private ArrayList<Appointment> items;

    private AppointmentAdapter adapter;

    private DatePicker datePicker;

    private FirebaseFirestore  mFirestore;

    private CollectionReference mAppointments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_appointment);
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
        adapter = new AppointmentAdapter(this, items);
        recyclerView.setAdapter(adapter);

        initData();

    }



    public void initData(){

        String[] itemsList = getResources().getStringArray(R.array.shopping_item_names);
        TypedArray itemsImageResources = getResources().obtainTypedArray(R.array.shopping_item_images);

        items.clear();

        for(int i= 0; i < itemsList.length; i++){
            items.add(new Appointment(null,itemsList[i],mAuth.getUid(),"0",itemsList[i],itemsImageResources.getResourceId(i,0)));

        }

        itemsImageResources.recycle();

        adapter.notifyDataSetChanged();
    }


    public void pressButton(String alert, Date date) {

        Appointment ans = new Appointment(date,alert,mAuth.getUid(),"0",alert,0);

        addToCart(ans);

        Log.i("AppointmentActivity", "MEGNYOMTAK A GOMBOT " + ans.getTitle() + " " + ans.getDate());

    }



    public void addToCart(Appointment appointment){

        mFirestore = FirebaseFirestore.getInstance();

        mAppointments = mFirestore.collection("basket");

        mAppointments.add(appointment);

    }


}