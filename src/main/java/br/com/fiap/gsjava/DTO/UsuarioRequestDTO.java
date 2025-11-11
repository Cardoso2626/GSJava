package br.com.fiap.gsjava.DTO;

import java.util.List;

public record UsuarioRequestDTO(String nome, String cpf, String email, String senha, Long idLocalizacao, List<Long> idMensagens) {
}
