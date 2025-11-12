package br.com.fiap.gsjava.controller;


import br.com.fiap.gsjava.DTO.LocalizacaoTrabalhoRequest;
import br.com.fiap.gsjava.DTO.LocalizacaoTrabalhoRequestDTO;
import br.com.fiap.gsjava.DTO.LocalizacaoTrabalhoResponse;
import br.com.fiap.gsjava.service.LocalizacaoTrabalhoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/localizacao")
public class LocalizacaoTrabalhoController {
    public final LocalizacaoTrabalhoService localizacaoTrabalhoService;
    public LocalizacaoTrabalhoController(LocalizacaoTrabalhoService localizacaoTrabalhoService) {
        this.localizacaoTrabalhoService = localizacaoTrabalhoService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<LocalizacaoTrabalhoResponse> pegarPorId(@PathVariable Long id) {
        LocalizacaoTrabalhoResponse loc = localizacaoTrabalhoService.pegarPorId(id);
        return ResponseEntity.ok(loc);
    }

    @DeleteMapping("/{id}")
    public void delete (@PathVariable Long id) {
        localizacaoTrabalhoService.deletarLocalizacao(id);
    }

    @PostMapping
    public ResponseEntity<LocalizacaoTrabalhoResponse> inserir(@RequestBody @Valid LocalizacaoTrabalhoRequest locRequest) {
        LocalizacaoTrabalhoResponse loc = localizacaoTrabalhoService.criarLocalizacao(locRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(loc);
    }
    @PutMapping("/{id}")
    public ResponseEntity<LocalizacaoTrabalhoResponse> atualizar(@PathVariable Long id, @RequestBody @Valid LocalizacaoTrabalhoRequestDTO locRequest) {
        LocalizacaoTrabalhoResponse loc = localizacaoTrabalhoService.atualizarLocalizacao(id, locRequest);
        return ResponseEntity.ok(loc);
    }

}
