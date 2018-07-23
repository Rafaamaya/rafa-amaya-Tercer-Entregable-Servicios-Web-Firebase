package com.rafael.servicios_web_firebase.views;


import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.firebase.ui.storage.images.FirebaseImageLoader;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.rafael.servicios_web_firebase.model.POJO.Pintura;
import com.rafael.servicios_web_firebase.R;
import com.rafael.servicios_web_firebase.ResultadoListener;

import java.util.List;

public class Adapter_RecyclerView_Pinturas extends RecyclerView.Adapter {
    private List<Pintura> listPintura;
    private ResultadoListener <Pintura> resultadoListenerPinturaClikeada;


    public Adapter_RecyclerView_Pinturas(List<Pintura> listPintura,ResultadoListener <Pintura> resultadoListenerPinturaClikeada) {
        this.listPintura = listPintura;
        this.resultadoListenerPinturaClikeada = resultadoListenerPinturaClikeada;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.recyclerview_celda,parent,false);
        ViewHolderPintura viewHolderPintura = new ViewHolderPintura(view);
        return viewHolderPintura;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Pintura pintura = listPintura.get(position);
        ViewHolderPintura viewHolderPintura = (ViewHolderPintura) holder;
        viewHolderPintura.CargarDatosEnCelda(pintura);
    }

    @Override
    public int getItemCount() {
        if(listPintura != null){
            return listPintura.size();
        }else{
            return 0;
        }
    }

    public class ViewHolderPintura extends RecyclerView.ViewHolder {
        private ImageView imageViewPintura;
        private TextView textViewtitulo;
        private RelativeLayout relativeLayout;



        public ViewHolderPintura(View itemView) {
            super(itemView);
            imageViewPintura = itemView.findViewById(R.id.recyclerview_celda_imagen);
            textViewtitulo = itemView.findViewById(R.id.recyclerview_celda_nombre);
            relativeLayout = itemView.findViewById(R.id.recyclerview_celda_relativelayout);


            relativeLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int posClikeada = getAdapterPosition();
                    Pintura pinturaClikeada = listPintura.get(posClikeada);
                    //INTERFAZ QUE COMUNICA ADAPTER CON LA ACTIVITY INICIO. LA ACTIVITY INICIO ES QUIEN IMPLEMENTA ESTA INTERFAZ
                    resultadoListenerPinturaClikeada.resultado(pinturaClikeada);
                }
            });

        }

        private void CargarDatosEnCelda (Pintura unapintura){
          //  imageViewPintura.setImageResource(unapintura.getImagen());
            textViewtitulo.setText(unapintura.getName());

            Glide.with(itemView.getContext())
                    .using(new FirebaseImageLoader())
                    .load(unapintura.cargarPintura())
                    .placeholder(R.drawable.placeholder)
                    .error(R.drawable.image_error)
                    .into(imageViewPintura);


        }
    }
}
