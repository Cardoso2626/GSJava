package br.com.fiap.gsjava.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "tb_gs_localizacao")
public class LocalizacaoTrabalho {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "tipo")
    private String tipo;
    @Column(name = "graus_celcius")
    private int grausCelcius;
    @Column(name = "nivel_umidade")
    private float nivelUmidade;
    @OneToMany(mappedBy = "locTrabalho")
    private List<Usuario> usuarios;

    public LocalizacaoTrabalho() {

    }

    public LocalizacaoTrabalho(Long id, String tipo, int grausCelcius, float nivelUmidade, List<Usuario> usuarios) {
        this.id = id;
        this.tipo = tipo;
        this.grausCelcius = grausCelcius;
        this.nivelUmidade = nivelUmidade;
        this.usuarios = usuarios;
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getGrausCelcius() {
        return grausCelcius;
    }

    public void setGrausCelcius(int grausCelcius) {
        this.grausCelcius = grausCelcius;
    }

    public float getNivelUmidade() {
        return nivelUmidade;
    }

    public void setNivelUmidade(float nivelUmidade) {
        this.nivelUmidade = nivelUmidade;
    }
}
