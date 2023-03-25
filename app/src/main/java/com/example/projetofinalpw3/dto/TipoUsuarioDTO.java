package com.example.projetofinalpw3.dto;

public class TipoUsuarioDTO {
    private String tipo;

    public TipoUsuarioDTO(String tipo) {
        this.tipo = tipo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        return "TipoUsuarioDTO{" +
                "tipo='" + tipo + '\'' +
                '}';
    }
}
