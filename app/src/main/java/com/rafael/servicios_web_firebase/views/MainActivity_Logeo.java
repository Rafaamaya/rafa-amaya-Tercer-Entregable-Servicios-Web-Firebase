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

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;
import com.rafael.servicios_web_firebase.model.POJO.Artista;
import com.rafael.servicios_web_firebase.R;

public class MainActivity_Logeo extends AppCompatActivity {
    private static final String CONTACTOS ="artists" ;
    private EditText inputEmail, inputPassword;
    private Button logeobutton, regitrobutton;
    private ProgressBar progressBar;
    private LoginButton loginButtonFacebook;
    private CallbackManager callbackManager;
    private FirebaseAuth mAuth;
    private FirebaseDatabase database;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_logeo);
        inputEmail = findViewById(R.id.MainActivity_Logeo_editText_email);
        inputPassword = findViewById(R.id.MainActivity_Logeo_editText_password);
        progressBar = findViewById(R.id.MainActivity_Logeo_progressBar);
        logeobutton = findViewById(R.id.MainActivity_Logeo_Iniciar);
        regitrobutton=findViewById(R.id.MainActivity_Logeo_registrar);



        mAuth = FirebaseAuth.getInstance();

        // Si la instancia es distinta de null
        if (mAuth.getCurrentUser() != null) {
            startActivity(new Intent(MainActivity_Logeo.this, MainActivity_Inicio.class));
            finish();
        }


        logeobutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!inputEmail.getText().toString().trim().isEmpty() && !inputPassword.getText().toString().trim().isEmpty()) {
                    logearUsuario(inputEmail.getText().toString().trim(), inputPassword.getText().toString().trim());
                }else {
                    Toast.makeText(MainActivity_Logeo.this, "VERIFIQUE LOS DATOS INGRESADOS", Toast.LENGTH_SHORT).show();
                }
            }

        });

        regitrobutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity_Logeo.this,MainActivity_Regist.class);
                startActivity(intent);
                finish();
            }
        });


        callbackManager = CallbackManager.Factory.create();
        loginButtonFacebook = findViewById(R.id.MainActivity_Logeo_loginbutton_facebook);
        loginButtonFacebook.setReadPermissions("email", "public_profile");
        loginButtonFacebook.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {

            @Override
            public void onSuccess(LoginResult loginResult) {
                handleFacebookAccessToken(loginResult.getAccessToken());
                Log.i("SESION CON FACEBOOK","SUCCESS");
            }

            @Override
            public void onCancel() {
                Log.i("SESION CON FACEBOOK","CANCEL");
                Toast.makeText(MainActivity_Logeo.this, "INICIO DE SESION CANCELADO", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(FacebookException exception) {
                Toast.makeText(MainActivity_Logeo.this, "ERROR AL INTENTAR INICIAR SESION", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void handleFacebookAccessToken(AccessToken accessToken) {
        AuthCredential credential = FacebookAuthProvider.getCredential(accessToken.getToken());
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            FirebaseUser user = mAuth.getCurrentUser();
                            Intent intent = new Intent(MainActivity_Logeo.this,MainActivity_Inicio.class);
                            intent.addFlags(intent.FLAG_ACTIVITY_CLEAR_TOP|intent.FLAG_ACTIVITY_CLEAR_TASK|intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(intent);
                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(MainActivity_Logeo.this, "Autentificacion fallo.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
    }


    //LOGEO USUARIO EN FIREBASE
    public void logearUsuario(String email,String password){
        progressBar.setVisibility(View.VISIBLE);
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            progressBar.setVisibility(View.GONE);
                            Log.d("MY LOGEO FIRBASE:", "createUserWithEmail:success");
                            startActivity(new Intent(MainActivity_Logeo.this, MainActivity_Inicio.class));
                            finish();
                        } else {
                            // If sign in fails, display a message to the user.
                            progressBar.setVisibility(View.GONE);
                            Log.w("MY LOGEO FIRBASE:", "createUserWithEmail:failure", task.getException());
                            Toast.makeText(MainActivity_Logeo.this, "Authentication failed.",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

}
