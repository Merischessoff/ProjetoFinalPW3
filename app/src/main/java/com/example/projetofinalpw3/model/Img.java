package com.example.projetofinalpw3.model;

import android.net.Uri;


public class Img {
    private Uri uri;
    private Integer idTexto;

    private Integer idImagem;

    public Img() {
    }

    public Uri getUri() {
        return uri;
    }

    public void setUri(Uri uri) {
        this.uri = uri;
    }

    public Integer getIdTexto() {
        return idTexto;
    }

    public void setIdTexto(Integer idTexto) {
        this.idTexto = idTexto;
    }

    public Integer getIdImagem() {
        return idImagem;
    }

    public void setIdImagem(Integer idImagem) {
        this.idImagem = idImagem;
    }

    @Override
    public String toString() {
        return "Img{" +
                "uri=" + uri +
                ", idTexto=" + idTexto +
                ", idImagem=" + idImagem +
                '}';
    }
}
