package br.com.fiap.gsjava.model;


import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "tb_gs_usuario")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String cpf;
    private String email;
    private String senha;
    private LocalizacaoTrabalho locTrabalho;
    private List<Mensagem> mensagens;

    public Usuario() {

    }
    public Usuario(Long id, String nome, String cpf, String email, String senha, LocalizacaoTrabalho locTrabalho, List<Mensagem> mensagens) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
        this.senha = senha;
        this.locTrabalho = locTrabalho;
        this.mensagens = mensagens;
    }

    public List<Mensagem> getMensagens() {
        return mensagens;
    }

    public void setMensagens(List<Mensagem> mensagens) {
        this.mensagens = mensagens;
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

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
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

    public LocalizacaoTrabalho getLocTrabalho() {
        return locTrabalho;
    }

    public void setLocTrabalho(LocalizacaoTrabalho locTrabalho) {
        this.locTrabalho = locTrabalho;
    }
}
