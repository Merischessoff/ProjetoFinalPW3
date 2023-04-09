package com.example.projetofinalpw3.model;

import java.util.List;

public class HistoriaSocial {
    private Long id;
    private String titulo;
    private String texto;

    private String emailUsuarioResponsavel;

    private Usuario usuarioLeitor;

    private HabilidadeSocial habilidadeSocial;

    private AtividadeDeVidaDiaria atividadeDeVidaDiaria;

    private List<Imagem> imagem;

    public HistoriaSocial(String titulo, String texto, String emailUsuarioResponsavel, HabilidadeSocial habilidadeSocial, AtividadeDeVidaDiaria atividadeDeVidaDiaria, List<Imagem> imagem) {
        this.titulo = titulo;
        this.texto = texto;
        this.habilidadeSocial = habilidadeSocial;
        this.atividadeDeVidaDiaria = atividadeDeVidaDiaria;
        this.imagem = imagem;
        this.emailUsuarioResponsavel = emailUsuarioResponsavel;
    }

    public HistoriaSocial(){}

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

    public HabilidadeSocial getHabilidadeSocial() {
        return habilidadeSocial;
    }

    public void setHabilidadeSocial(HabilidadeSocial habilidadeSocial) {
        this.habilidadeSocial = habilidadeSocial;
    }

    public AtividadeDeVidaDiaria getAtividadeDeVidaDiaria() {
        return atividadeDeVidaDiaria;
    }

    public void setAtividadeDeVidaDiaria(AtividadeDeVidaDiaria atividadeDeVidaDiaria) {
        this.atividadeDeVidaDiaria = atividadeDeVidaDiaria;
    }

    public List<Imagem> getImagem() {
        return imagem;
    }

    public void setImagem(List<Imagem> imagem) {
        this.imagem = imagem;
    }

    public String getEmailUsuarioResponsavel() {
        return emailUsuarioResponsavel;
    }

    public void setEmailUsuarioResponsavel(String emailUsuarioResponsavel) {
        this.emailUsuarioResponsavel = emailUsuarioResponsavel;
    }

    public Usuario getUsuarioLeitor() {
        return usuarioLeitor;
    }

    public void setUsuarioLeitor(Usuario usuarioLeitor) {
        this.usuarioLeitor = usuarioLeitor;
    }

    public HistoriaSocial withId(Long id) {
        this.id = id;
        return this;
    }

    public HistoriaSocial withEmail(String email) {
        this.emailUsuarioResponsavel = email;
        return this;
    }

    public HistoriaSocial withTitulo(String titulo) {
        this.titulo = titulo;
        return this;
    }

    public HistoriaSocial withTexto(String texto) {
        this.texto = texto;
        return this;
    }

    public HistoriaSocial withHabilidadeSocial(HabilidadeSocial habilidadeSocial) {
        this.habilidadeSocial = habilidadeSocial;
        return this;
    }

    public HistoriaSocial withAtividadeDeVidaDiaria(AtividadeDeVidaDiaria atividadeDeVidaDiaria) {
        this.atividadeDeVidaDiaria = atividadeDeVidaDiaria;
        return this;
    }

    public HistoriaSocial withImagem(List<Imagem> imagem) {
        this.imagem = imagem;
        return this;
    }

    public HistoriaSocial build() {
        return new HistoriaSocial(titulo, texto, emailUsuarioResponsavel, habilidadeSocial, atividadeDeVidaDiaria, imagem);
    }

    @Override
    public String toString() {
        return "HistoriaSocial{" +
                "id=" + id +
                ", titulo='" + titulo + '\'' +
                ", texto='" + texto + '\'' +
                ", emailUsuarioResponsavel='" + emailUsuarioResponsavel + '\'' +
                ", usuarioLeitor=" + usuarioLeitor +
                ", habilidadeSocial=" + habilidadeSocial +
                ", atividadeDeVidaDiaria=" + atividadeDeVidaDiaria +
                ", imagem=" + imagem +
                '}';
    }
}
