package com.rafael.servicios_web_firebase.controller;

import android.content.Context;

import com.rafael.servicios_web_firebase.model.DAO.DAOArtista;
import com.rafael.servicios_web_firebase.model.DAO.DAOPintura;
import com.rafael.servicios_web_firebase.model.DAO.DAOPinturaRetrofit;
import com.rafael.servicios_web_firebase.model.POJO.Artista;
import com.rafael.servicios_web_firebase.model.POJO.Pintura;
import com.rafael.servicios_web_firebase.ResultadoListener;

import java.util.List;

public class ControllerPintura {

    public void obtenerListaDePintura(final ResultadoListener<List<Pintura>> resultadoListener){
        DAOPinturaRetrofit daoPinturaRetrofit = new DAOPinturaRetrofit();
        daoPinturaRetrofit.obtenerPinturas(new ResultadoListener<List<Pintura>>() {
            @Override
            public void resultado(List<Pintura> res) {
                resultadoListener.resultado(res);
            }
        });



    }

    public void obtenerUnArtista(String id,final ResultadoListener<Artista> resultadoListener) {
        DAOArtista daoArtista = new DAOArtista();
        daoArtista.obtenerArtista(id,new ResultadoListener<Artista>() {
            @Override
            public void resultado(Artista res) {
                resultadoListener.resultado(res);
            }
        });
    }


    public List<Pintura> obtenerListaDePin (Context context){
        List<Pintura> listPintura;
        DAOPintura daoPintura = new DAOPintura();
        listPintura = daoPintura.getListaDePintura(context);
        return listPintura;
    }


}
