package com.rafael.servicios_web_firebase.views;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.rafael.servicios_web_firebase.R;

public class MainActivity_Regist extends AppCompatActivity {
    private EditText inputEmail, inputPassword;
    private Button buttonRegistrar, buttonIniciarSeccion;
    private ProgressBar progressBar;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main__regist);
        progressBar = findViewById(R.id.mainActivity_regist_progressBar);
        inputEmail = findViewById(R.id.mainActivity_editText_email);
        inputPassword = findViewById(R.id.mainActivity_editText_password);
        buttonRegistrar = findViewById(R.id.mainActivity_button_registrar);
        mAuth = FirebaseAuth.getInstance();

        buttonRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registrarUsuario(inputEmail.getText().toString().trim(),inputPassword.getText().toString().trim());
            }
        });


    }

    public void registrarUsuario(String email,String password){
        progressBar.setVisibility(View.VISIBLE);
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            progressBar.setVisibility(View.GONE);
                            Log.d("MY LOGEO FIRBASE:", "createUserWithEmail:success");
                            FirebaseAuth.getInstance().signOut();
                            startActivity(new Intent(MainActivity_Regist.this, MainActivity_Logeo.class));
                            finish();
                        } else {
                            // If sign in fails, display a message to the user.
                            progressBar.setVisibility(View.GONE);
                            Log.w("MY LOGEO FIRBASE:", "createUserWithEmail:failure", task.getException());
                            Toast.makeText(MainActivity_Regist.this, "Authentication failed.",Toast.LENGTH_SHORT).show();
                        }

                        // ...
                    }
                });
    }


}
