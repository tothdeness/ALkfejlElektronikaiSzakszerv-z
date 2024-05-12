package com.example.elektronikaiszakszerviz2;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.animation.LinearInterpolator;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    EditText emailET;
    EditText passwordET;

    TextView textView;
    FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        emailET = findViewById(R.id.editTextText);
        passwordET = findViewById(R.id.editTextTextPassword2);
        auth = FirebaseAuth.getInstance();
        textView = findViewById(R.id.textView4);
    }


    public void loginUser(View view) {
        String email = emailET.getText().toString();
        String password = passwordET.getText().toString();

        auth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if(task.isSuccessful()){
                    textView.setText(R.string.sucLogin);
                    textView.setTextColor(getResources().getColor(R.color.green));
                    animation();
                }else{
                    textView.setText(R.string.failLogin);
                    textView.setTextColor(getResources().getColor(R.color.red));
                    animation();
                }

            }


        });




    }

    public void back(View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }


public void animation(){

    textView.animate()
            .scaleX(1.2f)
            .scaleY(1.2f)
            .alpha(1f)
            .rotation(360f)
            .setDuration(500)
            .setInterpolator(new AccelerateDecelerateInterpolator())
            .withEndAction(() -> {
                textView.animate()
                        .scaleX(1f)
                        .scaleY(1f)
                        .setDuration(500)
                        .setInterpolator(new AccelerateDecelerateInterpolator())
                        .start();
            })
            .start();

}



}