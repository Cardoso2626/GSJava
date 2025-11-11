package br.com.fiap.gsjava.DTO;

import br.com.fiap.gsjava.enums.TipoTrabalho;

import java.util.List;

public record LocalizacaoTrabalhoResponse(Long id, TipoTrabalho tipo, int grausCelcius, float nivelUmidade ,List<Long> idUsuarios) {
}
