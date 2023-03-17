package com.example.projetofinalpw3.model;


public class Usuario {

    private Long id;
    private String login;
    private String senha;
    private TipoUsuario tipo;

    public Usuario() {
    }

    public Usuario(Long id, String login, String senha, TipoUsuario tipo) {
        this.id = id;
        this.login = login;
        this.senha = senha;
        this.tipo = tipo;
    }

    public Usuario(String login, String senha) {
        this.login = login;
        this.senha = senha;
    }

    public Long getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public String getSenha() {
        return senha;
    }

    public TipoUsuario getTipo() {
        return tipo;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public void setTipo(TipoUsuario tipo) {
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", senha='" + senha + '\'' +
                ", tipo=" + tipo +
                '}';
    }
}
