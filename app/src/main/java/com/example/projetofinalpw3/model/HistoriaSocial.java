package com.example.projetofinalpw3.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

public class HistoriaSocial implements Parcelable {
    private Long id;
    private String titulo;
    private String texto;

    private String emailUsuarioResponsavel;

    private List<Usuario> usuariosLeitores = new ArrayList<Usuario>();

    private List<HabilidadeSocial> habilidadesSociais = new ArrayList<HabilidadeSocial>();

    private List<AtividadeDeVidaDiaria> atividadesDeVidaDiarias = new ArrayList<AtividadeDeVidaDiaria>();

    private List<Imagem> imagens = new ArrayList<Imagem>();

    public HistoriaSocial(String titulo, String texto, String emailUsuarioResponsavel, List<HabilidadeSocial> habilidadesSociais, List<AtividadeDeVidaDiaria> atividadeDeVidaDiarias, List<Imagem> imagens) {
        this.titulo = titulo;
        this.texto = texto;
        this.habilidadesSociais = habilidadesSociais;
        this.atividadesDeVidaDiarias = atividadeDeVidaDiarias;
        this.imagens = imagens;
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

    public List<HabilidadeSocial> getHabilidadesSociais() {
        return habilidadesSociais;
    }

    public void setHabilidadesSociais(List<HabilidadeSocial> habilidadesSociais) {
        this.habilidadesSociais = habilidadesSociais;
    }

    public List<AtividadeDeVidaDiaria> getAtividadesDeVidaDiarias() {
        return atividadesDeVidaDiarias;
    }

    public void setAtividadesDeVidaDiarias(List<AtividadeDeVidaDiaria> atividadesDeVidaDiarias) {
        this.atividadesDeVidaDiarias = atividadesDeVidaDiarias;
    }

    public List<Imagem> getImagens() {
        return imagens;
    }

    public void setImagens(List<Imagem> imagens) {
        this.imagens = imagens;
    }

    public String getEmailUsuarioResponsavel() {
        return emailUsuarioResponsavel;
    }

    public void setEmailUsuarioResponsavel(String emailUsuarioResponsavel) {
        this.emailUsuarioResponsavel = emailUsuarioResponsavel;
    }

    public List<Usuario> getUsuariosLeitores() {
        return usuariosLeitores;
    }

    public void setUsuariosLeitores(List<Usuario> usuariosLeitores) {
        this.usuariosLeitores = usuariosLeitores;
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
        this.habilidadesSociais.add(habilidadeSocial);
        return this;
    }
    public HistoriaSocial withHabilidadesSociais(List<HabilidadeSocial> habilidadesSociais) {
        this.habilidadesSociais = habilidadesSociais;
        return this;
    }

    public HistoriaSocial withAtividadeDeVidaDiaria(AtividadeDeVidaDiaria atividadeDeVidaDiaria) {
        this.atividadesDeVidaDiarias.add(atividadeDeVidaDiaria);
        return this;
    }

    public HistoriaSocial withAtividadeDeVidaDiarias(List<AtividadeDeVidaDiaria> atividadesDeVidaDiarias) {
        this.atividadesDeVidaDiarias = atividadesDeVidaDiarias;
        return this;
    }

    public HistoriaSocial withImagens(List<Imagem> imagens) {
        this.imagens = imagens;
        return this;
    }

    public HistoriaSocial build() {
        return new HistoriaSocial(titulo, texto, emailUsuarioResponsavel, habilidadesSociais, atividadesDeVidaDiarias, imagens);
    }

    @Override
    public String toString() {
        return "HistoriaSocial{" +
                "id=" + id +
                ", titulo='" + titulo + '\'' +
                ", texto='" + texto + '\'' +
                ", emailUsuarioResponsavel='" + emailUsuarioResponsavel + '\'' +
                ", usuariosLeitores=" + usuariosLeitores +
                ", habilidadesSociais=" + habilidadesSociais +
                ", atividadesDeVidaDiarias=" + atividadesDeVidaDiarias +
                ", imagens=" + imagens +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeLong(this.id);
        dest.writeString(this.titulo);
        dest.writeString(this.texto);
        dest.writeString(this.emailUsuarioResponsavel);
        dest.writeTypedList(this.usuariosLeitores);
        dest.writeTypedList(this.habilidadesSociais);
        dest.writeTypedList(this.atividadesDeVidaDiarias);
        dest.writeTypedList(this.imagens);
    }

    public static final Creator<HistoriaSocial> CREATOR = new Creator<HistoriaSocial>() {
        @Override
        public HistoriaSocial createFromParcel(Parcel source) {
            HistoriaSocial historiaSocial = new HistoriaSocial();
            historiaSocial.id = source.readLong();
            historiaSocial.titulo = source.readString();
            historiaSocial.texto = source.readString();
            historiaSocial.emailUsuarioResponsavel = source.readString();
            //historiaSocial.usuariosLeitores = new ArrayList<>();
            //source.readTypedList(historiaSocial.usuariosLeitores, Usuario.CREATOR);
            historiaSocial.habilidadesSociais = new ArrayList<>();
            source.readTypedList(historiaSocial.habilidadesSociais, HabilidadeSocial.CREATOR);
            historiaSocial.atividadesDeVidaDiarias = new ArrayList<>();
            source.readTypedList(historiaSocial.atividadesDeVidaDiarias, AtividadeDeVidaDiaria.CREATOR);
            historiaSocial.imagens = new ArrayList<>();
            source.readTypedList(historiaSocial.imagens, Imagem.CREATOR);
            return historiaSocial;
        }

        @Override
        public HistoriaSocial[] newArray(int size) {
            return new HistoriaSocial[size];
        }
    };
}
