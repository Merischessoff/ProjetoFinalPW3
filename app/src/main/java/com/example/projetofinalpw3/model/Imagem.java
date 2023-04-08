package com.example.projetofinalpw3.model;

import java.util.ArrayList;
import java.util.List;

public class Imagem {

    private Long id;
    private int seq;
    private String url;

    private String texto;

    private HistoriaSocial historiaSocial;

    public Imagem(Long id, int seq, String url, String texto, HistoriaSocial historiaSocial) {
        this.id = id;
        this.seq = seq;
        this.url = url;
        this.texto = texto;
        this.historiaSocial = historiaSocial;
    }

    public Imagem(){

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getSeq() {
        return seq;
    }

    public void setSeq(int seq) {
        this.seq = seq;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public HistoriaSocial getHistoriaSocial() {
        return historiaSocial;
    }

    public void setHistoriaSocial(HistoriaSocial historiaSocial) {
        this.historiaSocial = historiaSocial;
    }

    @Override
    public String toString() {
        return "Imagem{" +
                "id=" + id +
                ", seq=" + seq +
                ", url='" + url + '\'' +
                ", texto='" + texto + '\'' +
                ", historiaSocial=" + historiaSocial +
                '}';
    }
}
