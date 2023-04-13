package com.example.projetofinalpw3.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

public class Imagem implements Parcelable {

    private Long id;
    private int seq;
    private String url;

    private String texto;


    public Imagem(){

    }

    public Imagem(Long id, int seq, String url, String texto) {
        this.id = id;
        this.seq = seq;
        this.url = url;
        this.texto = texto;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getSeq() {
        return seq;
    }

    public void setSeq(int seq) {
        this.seq = seq;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    @Override
    public String toString() {
        return "Imagem{" +
                "id=" + id +
                ", seq=" + seq +
                ", url='" + url + '\'' +
                ", texto='" + texto + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeLong(this.id);
        dest.writeInt(this.seq);
        dest.writeString(this.url);
        dest.writeString(this.texto);
    }

    protected Imagem(Parcel in) {
        this.id = in.readLong();
        this.seq = in.readInt();
        this.url = in.readString();
        this.texto = in.readString();
    }

    public static final Parcelable.Creator<Imagem> CREATOR = new Parcelable.Creator<Imagem>() {
        @Override
        public Imagem createFromParcel(Parcel source) {
            return new Imagem(source);
        }

        @Override
        public Imagem[] newArray(int size) {
            return new Imagem[size];
        }
    };
}
