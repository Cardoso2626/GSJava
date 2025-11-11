package br.com.fiap.gsjava.model;

import jakarta.persistence.*;

@Entity
@Table(name = "tb_gs_mensagem")
public class Mensagem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String mensagem;
    private int nivelEstresse;

    public Mensagem(){

    }
    public Mensagem(Long id, String mensagem, int nivelEstresse) {
        this.id = id;
        this.mensagem = mensagem;
        this.nivelEstresse = nivelEstresse;
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public int getNivelEstresse() {
        return nivelEstresse;
    }

    public void setNivelEstresse(int nivelEstresse) {
        this.nivelEstresse = nivelEstresse;
    }
}
