package com.rafael.servicios_web_firebase.model.POJO;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ConteinerPintura {
    @SerializedName("paints")
    private List<Pintura> listPintura;


    public List<Pintura> getListPintura() {
        return listPintura;
    }

}
