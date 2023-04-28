package com.example.projetofinalpw3.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

public class BancoDeHistoriaSocial implements Parcelable {

    private Long id;
    private String titulo;
    private String texto;
    private List<HabilidadeSocial> habilidadesSociais;

    private List<AtividadeDeVidaDiaria> atividadesDeVidaDiarias;

    private List<Imagem> imagens;

    public BancoDeHistoriaSocial(Long id, String titulo, String texto, List<HabilidadeSocial> habilidadesSociais, List<AtividadeDeVidaDiaria> atividadesDeVidaDiarias, List<Imagem> imagens) {
        this.id = id;
        this.titulo = titulo;
        this.texto = texto;
        this.habilidadesSociais = habilidadesSociais;
        this.atividadesDeVidaDiarias = atividadesDeVidaDiarias;
        this.imagens = imagens;
    }

    public BancoDeHistoriaSocial() {
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

    @Override
    public String toString() {
        return "BancoDeHistoriaSocial{" +
                "id=" + id +
                ", titulo='" + titulo + '\'' +
                ", texto='" + texto + '\'' +
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
        dest.writeTypedList(this.habilidadesSociais);
        dest.writeTypedList(this.atividadesDeVidaDiarias);
        dest.writeTypedList(this.imagens);
    }

    public static final Creator<BancoDeHistoriaSocial> CREATOR = new Creator<BancoDeHistoriaSocial>() {
        @Override
        public BancoDeHistoriaSocial createFromParcel(Parcel source) {
            BancoDeHistoriaSocial bancoDeHistoriaSocial = new BancoDeHistoriaSocial();
            bancoDeHistoriaSocial.id = source.readLong();
            bancoDeHistoriaSocial.titulo = source.readString();
            bancoDeHistoriaSocial.texto = source.readString();
            bancoDeHistoriaSocial.habilidadesSociais = new ArrayList<>();
            source.readTypedList(bancoDeHistoriaSocial.habilidadesSociais, HabilidadeSocial.CREATOR);
            bancoDeHistoriaSocial.atividadesDeVidaDiarias = new ArrayList<>();
            source.readTypedList(bancoDeHistoriaSocial.atividadesDeVidaDiarias, AtividadeDeVidaDiaria.CREATOR);
            bancoDeHistoriaSocial.imagens = new ArrayList<>();
            source.readTypedList(bancoDeHistoriaSocial.imagens, Imagem.CREATOR);
            return bancoDeHistoriaSocial;
        }

        @Override
        public BancoDeHistoriaSocial[] newArray(int size) {
            return new BancoDeHistoriaSocial[size];
        }
    };
}