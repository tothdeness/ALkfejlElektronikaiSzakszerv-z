package com.example.elektronikaiszakszerviz2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    FirebaseAuth auth;

    TextView welcome;
    Button login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        login = findViewById(R.id.buttonLogin);
        welcome = findViewById(R.id.textView);

        auth = FirebaseAuth.getInstance();

        if(auth.getCurrentUser() != null){
            welcome.setText("Üdvözlet, " + auth.getCurrentUser().getEmail() + "!");
            login.setText(R.string.logout);
        };

    }


    public void register(View view){
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }


    public void login(View view) {

        if(auth.getCurrentUser() == null) {
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
            finish();

        }else{
            auth.signOut();
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
        }


    }

    public void viewAppointments(View view) {

        if (auth.getCurrentUser() == null){
            login(view);
            return;
        }

        Intent intent = new Intent(this, AppointmentActivity.class);
        startActivity(intent);
    }

    public void viewCart(View view) {

        if (auth.getCurrentUser() == null){
            login(view);
            return;
        }


        Intent intent = new Intent(this, CartActivity.class);
        startActivity(intent);

    }



}