package br.com.fiap.gsjava.model;

import jakarta.persistence.*;

@Entity
@Table(name = "tb_gs_localizacao")
public class LocalizacaoTrabalho {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String tipo;
    private int grausCelcius;
    private float nivelUmidade;

    public LocalizacaoTrabalho() {

    }

    public LocalizacaoTrabalho(Long id, String tipo, int grausCelcius, float nivelUmidade) {
        this.id = id;
        this.tipo = tipo;
        this.grausCelcius = grausCelcius;
        this.nivelUmidade = nivelUmidade;
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
