package com.rafael.servicios_web_firebase.model.DAO;

import android.content.Context;
import android.content.res.AssetManager;

import com.google.gson.Gson;
import com.rafael.servicios_web_firebase.model.POJO.ConteinerPintura;
import com.rafael.servicios_web_firebase.model.POJO.Pintura;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class DAOPintura {

    public List <Pintura> getListaDePintura (Context context){
        List <Pintura> listapintura = new ArrayList<>();
        //Creamos un stream para leer el archivo Json
        AssetManager assetManager = context.getAssets();
        try {
            InputStream archivoPinturaJson = assetManager.open("Pinturas.json");
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(archivoPinturaJson));

            Gson gson = new Gson();
            ConteinerPintura conteinerPintura = gson.fromJson(bufferedReader,ConteinerPintura.class);
            listapintura = conteinerPintura.getListPintura();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return listapintura;
    }
}
