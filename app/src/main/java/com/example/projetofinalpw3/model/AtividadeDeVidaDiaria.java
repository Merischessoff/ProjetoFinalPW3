package com.example.projetofinalpw3.model;

import java.util.List;


public class AtividadeDeVidaDiaria{
    private Long id;
    private String nome;
    private String descricao;
    private HistoriaSocial historiaSocial;

    public AtividadeDeVidaDiaria() {
    }

    public AtividadeDeVidaDiaria(Long id, String nome, String descricao, HistoriaSocial historiaSocial) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.historiaSocial = historiaSocial;
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

    public HistoriaSocial getHistoriaSocial() {
        return historiaSocial;
    }

    public void setHistoriaSocial(HistoriaSocial historiaSocial) {
        this.historiaSocial = historiaSocial;
    }

    @Override
    public String toString() {
        return "AtividadeDeVidaDiaria{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", descricao='" + descricao + '\'' +
                ", historiaSocial=" + historiaSocial +
                '}';
    }
}
