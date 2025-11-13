package br.com.fiap.gsjava.DTO;

import br.com.fiap.gsjava.model.UsuarioRole;

import java.util.List;

public record UsuarioResponse(Long id, String nome, String cpf, String email, String senha, Long idLocalizacao, List<Long> idMensagens, UsuarioRole role) {
}
