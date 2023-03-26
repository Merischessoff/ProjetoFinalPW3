package com.example.projetofinalpw3.model;


public class Usuario {
    private Long id;
    private String cpf;
    private String nome;
    private String email;
    private String senha;
    private TipoUsuario tipo;

    private String emailUsuarioVinculado;

    public Usuario(Long id, String cpf, String nome, String email, String senha, TipoUsuario tipo, String emailUsuarioVinculado) {
        this.id = id;
        this.cpf = cpf;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.tipo = tipo;
        this.emailUsuarioVinculado = emailUsuarioVinculado;
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
        return "Usuario{" +
                "id=" + id +
                ", cpf='" + cpf + '\'' +
                ", nome='" + nome + '\'' +
                ", email='" + email + '\'' +
                ", senha='" + senha + '\'' +
                ", tipo=" + tipo +
                ", emailUsuarioVinculado='" + emailUsuarioVinculado + '\'' +
                '}';
    }
}
