package br.com.fiap.gsjava.repository;

import br.com.fiap.gsjava.model.Login;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoginRepository extends JpaRepository<Login, Long> {
}
