package br.com.fiap.gsjava.service;


import br.com.fiap.gsjava.DTO.LoginRequestDTO;
import br.com.fiap.gsjava.DTO.LoginResponse;

import br.com.fiap.gsjava.model.Login;
import br.com.fiap.gsjava.model.Usuario;
import br.com.fiap.gsjava.repository.LoginRepository;
import br.com.fiap.gsjava.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

@Service
public class LoginService {
    public final LoginRepository loginRepository;
    public final UsuarioRepository userRepository;

    public LoginService(LoginRepository loginRepository, UsuarioRepository userRepository) {
        this.loginRepository = loginRepository;
        this.userRepository = userRepository;

    }

    public LoginResponse fazerLogin(LoginRequestDTO loginRequest) {
        Usuario usuario = userRepository.findByEmailAndSenha(loginRequest.email(), loginRequest.senha());
        if (usuario == null){
            throw new RuntimeException("Email ou senhas inválidos!");
        }
        Login login = new Login();
        login.setEmail(loginRequest.email());
        login.setSenha(loginRequest.senha());
        login = loginRepository.save(login);
        return new LoginResponse(
                login.getId(),
                login.getEmail(),
                login.getSenha()
        );
    }
}
