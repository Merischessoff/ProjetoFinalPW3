package com.example.projetofinalpw3.model;

import java.util.List;


public class AtividadeDeVidaDiaria{
    private Long id;
    private String nome;
    private String descricao;
    private List<HistoriaSocial> historiasSociais;

    public AtividadeDeVidaDiaria() {
    }

    public AtividadeDeVidaDiaria(Long id, String nome, String descricao, List<HistoriaSocial> historiasSociais) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.historiasSociais = historiasSociais;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public List<HistoriaSocial> getHistoriasSociais() {
        return historiasSociais;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setHistoriasSociais(List<HistoriaSocial> historiasSociais) {
        this.historiasSociais = historiasSociais;
    }

    @Override
    public String toString() {
        return "AtividadeDeVidaDiaria{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", descricao='" + descricao + '\'' +
                ", historiasSociais=" + historiasSociais +
                '}';
    }
}
