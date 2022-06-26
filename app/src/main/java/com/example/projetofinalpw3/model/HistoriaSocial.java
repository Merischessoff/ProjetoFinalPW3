package com.example.projetofinalpw3.model;

import java.util.ArrayList;
import java.util.List;

public class HistoriaSocial {
    private String id;
    private String seq;
    private String titulo;
    private String texto;
    private String url;
    //private List<String> imagem = new ArrayList<String>();


    public HistoriaSocial() {
    }

    public HistoriaSocial(String seq, String titulo, String texto, String url) {
        this.seq = seq;
        this.titulo = titulo;
        this.texto = texto;
        this.url = url;
    }

    public String getSeq() {
        return seq;
    }

    public void setSeq(String seq) {
        this.seq = seq;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setId(String id) { this.id = id; }

    public String getId(){ return this.id; }

    @Override
    public String toString() {
        return "HistoriaSocial{" +
                "seq='" + seq + '\'' +
                ", titulo='" + titulo + '\'' +
                ", texto='" + texto + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
