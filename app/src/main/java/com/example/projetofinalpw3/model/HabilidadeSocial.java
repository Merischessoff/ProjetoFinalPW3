package com.example.projetofinalpw3.model;


import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

public class HabilidadeSocial implements Parcelable {
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeLong(id != null ? id : -1);
        dest.writeString(nome);
        dest.writeString(descricao);
        dest.writeList(historiasSociais);
    }

    public static final Creator<HabilidadeSocial> CREATOR = new Creator<HabilidadeSocial>() {
        @Override
        public HabilidadeSocial createFromParcel(Parcel in) {
            Long id = in.readLong();
            String nome = in.readString();
            String descricao = in.readString();
            List<HistoriaSocial> historiasSociais = new ArrayList<>();
            in.readList(historiasSociais, HistoriaSocial.class.getClassLoader());
            return new HabilidadeSocial(id != -1 ? id : null, nome, descricao, historiasSociais);
        }

        @Override
        public HabilidadeSocial[] newArray(int size) {
            return new HabilidadeSocial[size];
        }
    };

}
