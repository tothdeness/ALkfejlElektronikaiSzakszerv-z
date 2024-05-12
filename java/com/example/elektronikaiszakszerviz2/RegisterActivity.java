package com.example.elektronikaiszakszerviz2;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivity extends AppCompatActivity {

    EditText emailET;
    EditText passwordET;
    TextView textView;

    private static final String TAG = "RegisterActivity";
    private FirebaseAuth fireBaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_register);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

         fireBaseAuth = FirebaseAuth.getInstance();
         emailET = findViewById(R.id.editTextText);
         passwordET = findViewById(R.id.editTextTextPassword2);
         textView = findViewById(R.id.textView4);
    }

    public void registerUser(View view){

       String email = emailET.getText().toString();
       String password = passwordET.getText().toString();

       Log.i("RegisterUser", "Email is " + email +  " Password is: " + password);

       fireBaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
           @Override
           public void onComplete(@NonNull Task<AuthResult> task) {
               if(task.isSuccessful()){
                   Log.d(TAG, "createUserWithEmail:success");
                   textView.setText(R.string.sucRegister);
                   textView.setTextColor(getResources().getColor(R.color.green));
                   animation();
               }else{
                   Log.w(TAG, "createUserWithEmail:failure", task.getException());
                   textView.setText(R.string.failRegister);
                   textView.setTextColor(getResources().getColor(R.color.red));
                   animation();
               }
               fireBaseAuth.signOut();
           }
       });


    }

    public void back(View view){
        finish();
    }


    public void animation(){
        textView.animate()
                .scaleX(1.2f)
                .scaleY(1.2f)
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