package com.example.projetofinalpw3.dto;

import com.example.projetofinalpw3.model.TipoUsuario;

public class UsuarioCadDTO {
    private String cpf;
    private String nome;
    private String email;
    private String senha;
    private TipoUsuario tipo;

    private String emailUsuarioVinculado;

    public UsuarioCadDTO(String nome, String email, String senha, TipoUsuario tipo, String emailUsuarioVinculado) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.tipo = tipo;
        this.emailUsuarioVinculado = emailUsuarioVinculado;
    }

    public UsuarioCadDTO() {
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
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

    public String getEmailUsuarioVinculado() {
        return emailUsuarioVinculado;
    }

    public void setEmailUsuarioVinculado(String emailUsuarioVinculado) {
        this.emailUsuarioVinculado = emailUsuarioVinculado;
    }

    @Override
    public String toString() {
        return "UsuarioCadDTO{" +
                ", nome='" + nome + '\'' +
                ", email='" + email + '\'' +
                ", senha='" + senha + '\'' +
                ", tipo=" + tipo +
                ", emailUsuarioVinculado='" + emailUsuarioVinculado + '\'' +
                '}';
    }
}
