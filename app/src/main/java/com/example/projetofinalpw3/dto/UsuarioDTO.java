package com.example.projetofinalpw3.dto;

import com.example.projetofinalpw3.model.TipoUsuario;

public class UsuarioDTO {
    private String nome;
    private String email;
    private String cpf;
    private String senha;

    private TipoUsuario tipo;

    public UsuarioDTO() {
    }

    public UsuarioDTO(String nome, String email, String cpf, String senha, TipoUsuario tipo) {
        this.nome = nome;
        this.email = email;
        this.cpf = cpf;
        this.senha = senha;
        this.tipo = tipo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public TipoUsuario getTipo() {
        return tipo;
    }

    public void setTipo(TipoUsuario tipo) {
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        return "UsuarioDTO{" +
                "nome='" + nome + '\'' +
                ", email='" + email + '\'' +
                ", cpf='" + cpf + '\'' +
                ", senha='" + senha + '\'' +
                ", tipo=" + tipo +
                '}';
    }
}
