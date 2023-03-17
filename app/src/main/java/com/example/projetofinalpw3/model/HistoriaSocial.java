package com.example.projetofinalpw3.model;

import java.util.List;

public class HistoriaSocial {
    private Long id;
    private String titulo;
    private String texto;

    private List<HabilidadeSocial> habilidadesSociais;

    private List<AtividadeDeVidaDiaria> atividadeDeVidaDiarias;

    private List<Imagem> imagens;

    public HistoriaSocial() {
    }

    public HistoriaSocial(Long id, String titulo, String texto, List<HabilidadeSocial> habilidadesSociais, List<AtividadeDeVidaDiaria> atividadeDeVidaDiarias, List<Imagem> imagens) {
        this.id = id;
        this.titulo = titulo;
        this.texto = texto;
        this.habilidadesSociais = habilidadesSociais;
        this.atividadeDeVidaDiarias = atividadeDeVidaDiarias;
        this.imagens = imagens;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public List<HabilidadeSocial> getHabilidadesSociais() {
        return habilidadesSociais;
    }

    public void setHabilidadesSociais(List<HabilidadeSocial> habilidadesSociais) {
        this.habilidadesSociais = habilidadesSociais;
    }

    public List<AtividadeDeVidaDiaria> getAtividadeDeVidaDiarias() {
        return atividadeDeVidaDiarias;
    }

    public void setAtividadeDeVidaDiarias(List<AtividadeDeVidaDiaria> atividadeDeVidaDiarias) {
        this.atividadeDeVidaDiarias = atividadeDeVidaDiarias;
    }

    public List<Imagem> getImagens() {
        return imagens;
    }

    public void setImagens(List<Imagem> imagens) {
        this.imagens = imagens;
    }

    @Override
    public String toString() {
        return "HistoriaSocial{" +
                "id=" + id +
                ", titulo='" + titulo + '\'' +
                ", texto='" + texto + '\'' +
                ", habilidadesSociais=" + habilidadesSociais +
                ", atividadeDeVidaDiarias=" + atividadeDeVidaDiarias +
                ", imagens=" + imagens +
                '}';
    }
}
