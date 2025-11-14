package br.com.fiap.gsjava.controller;

import br.com.fiap.gsjava.DTO.*;
import br.com.fiap.gsjava.model.Usuario;
import br.com.fiap.gsjava.repository.UsuarioRepository;
import br.com.fiap.gsjava.security.TokenService;
import br.com.fiap.gsjava.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {
    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UsuarioRepository repository;
    @Autowired
    private TokenService tokenService;

    @PostMapping("/auth/login")
    public ResponseEntity login (@RequestBody @Valid LoginRequestDTO data) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.email(), data.senha());
        var auth = this.authenticationManager.authenticate(usernamePassword);

        var token = tokenService.generateToken((Usuario) auth.getPrincipal());
        return ResponseEntity.ok(new LoginResponseDTO(token));
    }

    @PostMapping("/auth/register")
    public ResponseEntity register (@RequestBody @Valid UsuarioRequest data) {
        if(this.repository.findByEmail(data.getEmail()) != null) return ResponseEntity.badRequest().build();
        String encryptedPassword = new BCryptPasswordEncoder().encode(data.getSenha());
        Usuario user = new Usuario(data.getNome(),data.getCpf(),data.getEmail(), encryptedPassword, data.getRole());

        this.repository.save(user);

        return ResponseEntity.ok().build();
    }
    @GetMapping("/{id}")
    public ResponseEntity<UsuarioResponse> pegarPorId(@PathVariable Long id) {
        UsuarioResponse usuario = usuarioService.pegarPorId(id);
        return ResponseEntity.ok(usuario);
    }

    @DeleteMapping("/{id}")
    public void delete (@PathVariable Long id) {
        usuarioService.deletarUsuario(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsuarioResponse> atualizar(@PathVariable Long id, @RequestBody @Valid UsuarioRequestDTO usuarioRequest) {
        UsuarioResponse usuario = usuarioService.atualizarUsuario(id, usuarioRequest);
        return ResponseEntity.ok(usuario);
    }
}
