package br.com.fiap.gsjava.repository;

import br.com.fiap.gsjava.model.Mensagem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MensagemRepository extends JpaRepository<Mensagem, Long> {
}
