package br.com.fiap.gsjava.DTO;


import br.com.fiap.gsjava.enums.TipoTrabalho;
import br.com.fiap.gsjava.model.Usuario;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;

import java.util.List;

public class LocalizacaoTrabalhoRequest {

    @NotBlank(message = "O tipo é obrigatório, deve ser 'HIBRIDO', 'REMOTO' ou 'PRESENCIAL'")
    private TipoTrabalho tipo;

    @NotBlank(message = "Os graus celcius são obrigatórios")
    @PositiveOrZero(message = "O nivel de umidade não pode ser = 0")
    private int grausCelcius;

    @NotBlank(message = "O tipo é obrigatório, deve ser 'HIBRIDO', 'REMOTO' ou 'PRESENCIAL'")
    @DecimalMin(value = "0.0", message = "O nível de umidade deve ser maior ou igual a 0")
    @DecimalMax(value = "100.0", message = "O nível de umidade não pode ultrapassar 100")
    private float nivelUmidade;


    public TipoTrabalho getTipo() {
        return tipo;
    }

    public void setTipo(TipoTrabalho tipo) {
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
