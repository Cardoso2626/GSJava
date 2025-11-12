package br.com.fiap.gsjava.controller;

import br.com.fiap.gsjava.DTO.LoginRequestDTO;
import br.com.fiap.gsjava.DTO.LoginResponse;
import br.com.fiap.gsjava.service.LoginService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class LoginController {
    public final LoginService loginService;
    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @PostMapping
    public ResponseEntity<LoginResponse> login(@RequestBody @Valid LoginRequestDTO loginRequestDTO) {
        LoginResponse login = loginService.fazerLogin(loginRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(login);
    }
}
