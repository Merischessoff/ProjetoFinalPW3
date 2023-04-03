package com.example.projetofinalpw3.model;

import java.util.ArrayList;
import java.util.List;

public class Imagem {

    private Long id;
    private int seq;
    private String url;

    private String texto;

    private List<HistoriaSocial> historias = new ArrayList<HistoriaSocial>();

    public Imagem(Long id, int seq, String url, String texto) {
        this.id = id;
        this.seq = seq;
        this.url = url;
        this.texto = texto;
    }

    public Imagem(Long id, int seq, String url, String texto, List<HistoriaSocial> historias) {
        this.id = id;
        this.seq = seq;
        this.url = url;
        this.texto = texto;
        this.historias = historias;
    }

    public Imagem() {
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

    public List<HistoriaSocial> getHistorias() {
        return historias;
    }

    public void setHistorias(List<HistoriaSocial> historias) {
        this.historias = historias;
    }

    @Override
    public String toString() {
        return "Imagem{" +
                "id=" + id +
                ", seq=" + seq +
                ", url='" + url + '\'' +
                ", texto='" + texto + '\'' +
                ", historias=" + historias +
                '}';
    }
}
