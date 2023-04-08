package com.example.projetofinalpw3.model;


import java.util.List;

public class HabilidadeSocial {
    private Long id;
    private String nome;
    private String descricao;

    private List<HistoriaSocial> historiaSocial;

    public HabilidadeSocial(Long id, String nome, String descricao, List<HistoriaSocial> historiaSocial) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.historiaSocial = historiaSocial;
    }

    public HabilidadeSocial() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public List<HistoriaSocial> getHistoriaSocial() {
        return historiaSocial;
    }

    public void setHistoriaSocial(List<HistoriaSocial> historiaSocial) {
        this.historiaSocial = historiaSocial;
    }

    @Override
    public String toString() {
        return "HabilidadeSocial{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", descricao='" + descricao + '\'' +
                ", historiaSocial=" + historiaSocial +
                '}';
    }
}
