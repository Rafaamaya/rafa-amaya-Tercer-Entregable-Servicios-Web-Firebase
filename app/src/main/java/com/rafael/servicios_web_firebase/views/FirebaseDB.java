package com.rafael.servicios_web_firebase.views;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.firebase.ui.storage.images.FirebaseImageLoader;
import com.rafael.servicios_web_firebase.model.POJO.Artista;
import com.rafael.servicios_web_firebase.model.POJO.Pintura;
import com.rafael.servicios_web_firebase.R;
import com.rafael.servicios_web_firebase.ResultadoListener;
import com.rafael.servicios_web_firebase.controller.ControllerPintura;

public class FirebaseDB extends AppCompatActivity {
    public static final String KEY_PINTURA ="pintura" ;
    private Pintura unaPintura;
    private ImageView pinturaimagen;
    private TextView textViewTiTuloPintura,textViewNombreArtista,textViewNacionalidadArtista,textViewInfluenciaArtista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firebase_db);
        pinturaimagen = findViewById(R.id.firebaseDB_pinturaimagen);
        textViewTiTuloPintura = findViewById(R.id.firebaseDB_pintura_nombre);
        textViewNombreArtista = findViewById(R.id.firebaseDB_textnombreartista);
        textViewNacionalidadArtista = findViewById(R.id.firebaseDB_textnacionalidad);
        textViewInfluenciaArtista =findViewById(R.id.firebaseDB_textinfluencia);


        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        unaPintura = (Pintura) bundle.getSerializable(KEY_PINTURA);

        textViewTiTuloPintura.setText(unaPintura.getName().toUpperCase());
        Glide.with(this)
                .using(new FirebaseImageLoader())
                .load(unaPintura.cargarPintura())
                .placeholder(R.drawable.placeholder)
                .error(R.drawable.image_error)
                .into(pinturaimagen);


        obtenerArtista(unaPintura.getArtistId());



    }
    public void obtenerArtista (String idPintura){
        ControllerPintura controllerPintura = new ControllerPintura();
        controllerPintura.obtenerUnArtista (idPintura,new ResultadoListener<Artista>() {
            @Override
            public void resultado(Artista res) {
                textViewNombreArtista.setText(res.getName());
                textViewNacionalidadArtista.setText(res.getNationality());
                textViewInfluenciaArtista.setText(res.getInfluenced_by());
            }
        });


    }
}
