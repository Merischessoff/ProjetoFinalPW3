package com.example.projetofinalpw3.model;

public class Imagem {

    private Long id;
    private String seq;
    private String url;

    HistoriaSocial historiaSocial;

    public Imagem(Long id, String seq, String url, HistoriaSocial historiaSocial) {
        this.id = id;
        this.seq = seq;
        this.url = url;
        this.historiaSocial = historiaSocial;
    }

    public Imagem() {
    }

    public Long getId() {
        return id;
    }

    public String getSeq() {
        return seq;
    }

    public String getUrl() {
        return url;
    }

    public HistoriaSocial getHistoriaSocial() {
        return historiaSocial;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setSeq(String seq) {
        this.seq = seq;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setHistoriaSocial(HistoriaSocial historiaSocial) {
        this.historiaSocial = historiaSocial;
    }

    @Override
    public String toString() {
        return "Imagem{" +
                "id=" + id +
                ", seq='" + seq + '\'' +
                ", url='" + url + '\'' +
                ", historiaSocial=" + historiaSocial +
                '}';
    }
}
