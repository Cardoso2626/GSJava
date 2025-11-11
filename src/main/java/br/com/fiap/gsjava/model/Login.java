package br.com.fiap.gsjava.model;


import jakarta.persistence.*;

@Entity
@Table(name = "tb_gs_login")
public class Login {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;
    private String senha;


    public Login(){}
    public Login(Long id, String email, String senha) {
        this.id = id;
        this.email = email;
        this.senha = senha;

    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
}
