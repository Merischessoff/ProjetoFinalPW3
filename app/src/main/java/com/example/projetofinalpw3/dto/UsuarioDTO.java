package com.example.projetofinalpw3.dto;

public class UsuarioDTO {
    String login;
    String senha;

    public UsuarioDTO(String login, String senha) {
        this.login = login;
        this.senha = senha;
    }

    public String getLogin() {
        return login;
    }

    public String getSenha() {
        return senha;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    @Override
    public String toString() {
        return "UsuarioDTO{" +
                "login='" + login + '\'' +
                ", senha='" + senha + '\'' +
                '}';
    }
}
