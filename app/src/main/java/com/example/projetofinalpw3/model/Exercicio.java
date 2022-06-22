package com.example.projetofinalpw3.model;

public class Exercicio {
    private int id;
    private HabilidadeSocial hs;
    private AtividadeDiaria avd;
    private String descricao;
    private HistoriaSocial historiaSocial;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public HabilidadeSocial getHs() {
        return hs;
    }

    public void setHs(HabilidadeSocial hs) {
        this.hs = hs;
    }

    public AtividadeDiaria getAvd() {
        return avd;
    }

    public void setAvd(AtividadeDiaria avd) {
        this.avd = avd;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public HistoriaSocial getHistoriaSocial() {
        return historiaSocial;
    }

    public void setHistoriaSocial(HistoriaSocial historiaSocial) {
        this.historiaSocial = historiaSocial;
    }

    @Override
    public String toString() {
        return "Exercicio{" +
                "id=" + id +
                ", hs=" + hs +
                ", avd=" + avd +
                ", descricao='" + descricao + '\'' +
                ", historiaSocial=" + historiaSocial +
                '}';
    }
}
