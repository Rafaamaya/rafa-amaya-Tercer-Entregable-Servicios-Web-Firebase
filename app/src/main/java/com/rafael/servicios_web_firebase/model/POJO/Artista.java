package com.rafael.servicios_web_firebase.model.POJO;



public class Artista {
    private String Influenced_by;
    private String artistId;
    private String name;
    private String nationality;

    public Artista() {

    }
    public Artista(String influenced_by, String artistId, String name, String nationality) {
        this.Influenced_by = influenced_by;
        this.artistId = artistId;
        this.name = name;
        this.nationality = nationality;
    }

    public String getInfluenced_by() {
        return Influenced_by;
    }

    public void setInfluenced_by(String influenced_by) {
        Influenced_by = influenced_by;
    }

    public String getArtistId() {
        return artistId;
    }

    public void setArtistId(String artistId) {
        this.artistId = artistId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    @Override
    public String toString() {
        return "Artista{" +
                "Influenced_by='" + Influenced_by + '\'' +
                ", artistId=" + artistId +
                ", name='" + name + '\'' +
                ", nationality='" + nationality + '\'' +
                '}';
    }
}
