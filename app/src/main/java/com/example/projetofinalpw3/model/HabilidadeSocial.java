package com.example.projetofinalpw3.model;


import java.util.ArrayList;
import java.util.List;

public class HabilidadeSocial {
    private Long id;
    private String nome;
    private String descricao;

    private List<HistoriaSocial> historiasSociais = new ArrayList<HistoriaSocial>();

    public HabilidadeSocial() {
    }

    public HabilidadeSocial(Long id, String nome, String descricao, List<HistoriaSocial> lista) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.historiasSociais = lista;
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

    public List<HistoriaSocial> getLista() {
        return historiasSociais;
    }

    public void setLista(List<HistoriaSocial> lista) {
        this.historiasSociais = lista;
    }

    @Override
    public String toString() {
        return "HabilidadeSocial{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", descricao='" + descricao + '\'' +
                ", historiasSociais=" + historiasSociais +
                '}';
    }
}
