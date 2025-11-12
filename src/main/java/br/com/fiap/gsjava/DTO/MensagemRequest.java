package br.com.fiap.gsjava.DTO;

import jakarta.validation.constraints.*;

public class MensagemRequest {
    @NotBlank(message = "A mensagem é obrigatória")
    @Size(min = 2, max = 100, message = "O nome deve ter entre 2 e 100 caracteres")
    private String mensagem;
    @NotNull(message = "O nivel de estresse é obrigatório")
    @Min(value = 0, message = "O nivel de estresse mínimo permitida é 0")
    @Max(value = 10, message = "O nivel de estresse máximo permitida é 10")
    private int nivelEstresse;

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
