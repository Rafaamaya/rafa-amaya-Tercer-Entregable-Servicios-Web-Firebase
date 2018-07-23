package com.rafael.servicios_web_firebase.model.POJO;

import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.Serializable;

public class  Pintura implements Serializable {

        private String image;
        private String name;
        private String artistId;

    public Pintura(String image, String name, String artistId) {
        this.image = image;
        this.name = name;
        this.artistId = artistId;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getArtistId() {
        return artistId;
    }

    public void setArtistId(String artistId) {
        this.artistId = artistId;
    }

    public StorageReference cargarPintura (){
        FirebaseStorage storage = FirebaseStorage.getInstance();
        StorageReference imageReference = storage.getReference();
        imageReference = imageReference.child(getImage());
        return imageReference;
    }

    @Override
    public String toString() {
        return "Producto{" +
                "image='" + image + '\'' +
                ", name='" + name + '\'' +
                ", artistId=" + artistId +
                '}';
    }
}
