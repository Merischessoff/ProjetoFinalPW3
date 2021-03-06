package com.example.projetofinalpw3.model;

import java.io.Serializable;

public class AtividadeDiaria{
    private int id;
    private String nome;
    private String descricao;
    private HistoriaSocial historiaSocial;

    public AtividadeDiaria(){
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "AtividadeDiaria{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", descricao='" + descricao + '\'' +
                ", historiaSocial=" + historiaSocial +
                '}';
    }
}
