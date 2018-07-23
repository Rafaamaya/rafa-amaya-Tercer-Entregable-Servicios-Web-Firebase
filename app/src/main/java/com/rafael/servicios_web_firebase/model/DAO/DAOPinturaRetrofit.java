package com.rafael.servicios_web_firebase.model.DAO;

import com.rafael.servicios_web_firebase.model.POJO.ConteinerPintura;
import com.rafael.servicios_web_firebase.model.POJO.Pintura;
import com.rafael.servicios_web_firebase.model.POJO.ServicePintura;
import com.rafael.servicios_web_firebase.ResultadoListener;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DAOPinturaRetrofit {
    private ServicePintura servicePintura;

    public DAOPinturaRetrofit(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.myjson.com/bins/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        servicePintura = retrofit.create(ServicePintura.class);
    }


    public void obtenerPinturas(final ResultadoListener<List<Pintura>> resultadoListenerController) {
        Call <ConteinerPintura> call = servicePintura.getProductos();
        call.enqueue(new Callback<ConteinerPintura>() {
            @Override
            public void onResponse(Call<ConteinerPintura> call, Response<ConteinerPintura> response) {
                resultadoListenerController.resultado(response.body().getListPintura());
            }

            @Override
            public void onFailure(Call<ConteinerPintura> call, Throwable t) {
                resultadoListenerController.resultado(new ArrayList<Pintura>());

            }
        });
    }
}
