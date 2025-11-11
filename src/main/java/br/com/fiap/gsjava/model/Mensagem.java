package br.com.fiap.gsjava.model;

import jakarta.persistence.*;

@Entity
@Table(name = "tb_gs_mensagem")
public class Mensagem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "mensagem")
    private String mensagem;

    @Column(name = "nivel_estresse")
    private int nivelEstresse;

    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;

    public Mensagem(){

    }
    public Mensagem(Long id, String mensagem, int nivelEstresse, Usuario usuario) {
        this.id = id;
        this.mensagem = mensagem;
        this.nivelEstresse = nivelEstresse;
        this.usuario = usuario;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
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
