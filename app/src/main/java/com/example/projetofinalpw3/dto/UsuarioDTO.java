package com.example.projetofinalpw3.dto;


import com.example.projetofinalpw3.model.TipoUsuario;

public class UsuarioDTO {
    private Long id;
    private String cpf;
    private String nome;
    private String email;
    private String senha;

    public UsuarioDTO(Long id, String cpf, String nome, String email, String senha) {
        this.id = id;
        this.cpf = cpf;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    @Override
    public String toString() {
        return "UsuarioDTO{" +
                "id=" + id +
                ", cpf='" + cpf + '\'' +
                ", nome='" + nome + '\'' +
                ", email='" + email + '\'' +
                ", senha='" + senha + '\'' +
                '}';
    }
}
