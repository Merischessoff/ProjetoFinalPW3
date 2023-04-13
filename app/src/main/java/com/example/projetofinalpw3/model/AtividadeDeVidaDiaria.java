package com.example.projetofinalpw3.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;


public class AtividadeDeVidaDiaria implements Parcelable {
    private Long id;
    private String nome;
    private String descricao;

    private List<HistoriaSocial> historiasSociais = new ArrayList<HistoriaSocial>();
    public AtividadeDeVidaDiaria() {
    }
    public AtividadeDeVidaDiaria(Long id, String nome, String descricao, List<HistoriaSocial> lista) {
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
        return "AtividadeDeVidaDiaria{" +
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
        dest.writeLong(this.id);
        dest.writeString(this.nome);
        dest.writeString(this.descricao);
        dest.writeList(this.historiasSociais);
    }

    public static final Parcelable.Creator<AtividadeDeVidaDiaria> CREATOR = new Parcelable.Creator<AtividadeDeVidaDiaria>() {
        @Override
        public AtividadeDeVidaDiaria createFromParcel(Parcel source) {
            Long id = source.readLong();
            String nome = source.readString();
            String descricao = source.readString();
            List<HistoriaSocial> historiasSociais = new ArrayList<>();
            source.readList(historiasSociais, getClass().getClassLoader());
            return new AtividadeDeVidaDiaria(id, nome, descricao, historiasSociais);
        }

        @Override
        public AtividadeDeVidaDiaria[] newArray(int size) {
            return new AtividadeDeVidaDiaria[size];
        }
    };
}
