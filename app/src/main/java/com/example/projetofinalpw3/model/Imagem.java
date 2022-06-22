package com.example.projetofinalpw3.model;

public class Imagem {
    String texto;
    String url;

    public Imagem(){ }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    @Override
    public String toString() {
        return "Imagem{" +
                "texto='" + texto + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
