package com.rafael.servicios_web_firebase.views;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.facebook.login.LoginManager;
import com.google.firebase.auth.FirebaseAuth;
import com.rafael.servicios_web_firebase.model.POJO.Pintura;
import com.rafael.servicios_web_firebase.R;
import com.rafael.servicios_web_firebase.ResultadoListener;
import com.rafael.servicios_web_firebase.controller.ControllerPintura;

import java.util.List;

public class MainActivity_Inicio extends AppCompatActivity implements ResultadoListener<Pintura> {

    private RecyclerView recyclerView;
    private List <Pintura> listpintura;
    private Adapter_RecyclerView_Pinturas adapterPinturas;
    private Button logoutButton ;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main__inicio);
        recyclerView = findViewById(R.id.activity_main_recyclerView);
        logoutButton = findViewById(R.id.salir);
        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                LoginManager.getInstance().logOut();
                startActivity(new Intent(MainActivity_Inicio.this, MainActivity_Logeo.class));
                finish();
            }
        });


        armarListadoPintura(this);

    }

    public void armarListadoPintura (final Context context){
        ControllerPintura controllerPintura = new ControllerPintura();
        controllerPintura.obtenerListaDePintura(new ResultadoListener<List<Pintura>>() {
            @Override
            public void resultado(List<Pintura> res) {
                listpintura = res;
                //Toast.makeText(MainActivity_Inicio.this, listpintura.toString(), Toast.LENGTH_SHORT).show();
                adapterPinturas = new Adapter_RecyclerView_Pinturas(listpintura,MainActivity_Inicio.this);
                recyclerView.setLayoutManager(new GridLayoutManager(context, 2,1,false));
                recyclerView.setAdapter(adapterPinturas);
            }
        });
    }

    //RESIBO LA PINTURA CLIKEADA
    @Override
    public void resultado(Pintura res) {
        Pintura unpintura =  res;
        //Toast.makeText(MainActivity_Inicio.this,unpintura.getName(),Toast.LENGTH_LONG).show();
        Intent intent = new Intent(this,FirebaseDB.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable(FirebaseDB.KEY_PINTURA, unpintura);
        intent.putExtras(bundle);
        startActivity(intent);
    }
}
