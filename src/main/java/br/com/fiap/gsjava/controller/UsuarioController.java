package br.com.fiap.gsjava.controller;

import br.com.fiap.gsjava.DTO.UsuarioRequest;
import br.com.fiap.gsjava.DTO.UsuarioRequestDTO;
import br.com.fiap.gsjava.DTO.UsuarioResponse;
import br.com.fiap.gsjava.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {
    public final UsuarioService usuarioService;
    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
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

    @PostMapping
    public ResponseEntity<UsuarioResponse> inserir(@RequestBody @Valid UsuarioRequest usuarioRequest) {
        UsuarioResponse usuario = usuarioService.criarUsuario(usuarioRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(usuario);
    }
    @PutMapping("/{id}")
    public ResponseEntity<UsuarioResponse> atualizar(@PathVariable Long id, @RequestBody @Valid UsuarioRequestDTO usuarioRequest) {
        UsuarioResponse usuario = usuarioService.atualizarUsuario(id, usuarioRequest);
        return ResponseEntity.ok(usuario);
    }
}
