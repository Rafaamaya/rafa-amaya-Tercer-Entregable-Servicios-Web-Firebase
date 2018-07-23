package com.rafael.servicios_web_firebase.model.POJO;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ServicePintura {


    @GET("x858r")
    Call<ConteinerPintura> getProductos();
}
