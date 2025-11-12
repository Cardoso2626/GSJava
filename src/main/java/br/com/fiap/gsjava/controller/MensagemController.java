package br.com.fiap.gsjava.controller;
import br.com.fiap.gsjava.DTO.MensagemRequest;
import br.com.fiap.gsjava.DTO.MensagemRequestDTO;
import br.com.fiap.gsjava.DTO.MensagemResponse;
import br.com.fiap.gsjava.service.MensagemService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/mensagem")
public class MensagemController {
    public final MensagemService mensagemService;
    public MensagemController(MensagemService mensagemService) {
        this.mensagemService = mensagemService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<MensagemResponse> pegarPorId(@PathVariable Long id) {
        MensagemResponse mensagem = mensagemService.pegarPorId(id);
        return ResponseEntity.ok(mensagem);
    }

    @DeleteMapping("/{id}")
    public void delete (@PathVariable Long id) {
        mensagemService.excluirMensagem(id);
    }

    @PostMapping
    public ResponseEntity<MensagemResponse> inserir(@RequestBody @Valid MensagemRequest mensagemRequest) {
        MensagemResponse mensagem = mensagemService.criarMensagem(mensagemRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(mensagem);
    }
    @PutMapping("/{id}")
    public ResponseEntity<MensagemResponse> atualizar(@PathVariable Long id, @RequestBody @Valid MensagemRequestDTO mensagemRequest) {
        MensagemResponse mensagem = mensagemService.autalizarMensagem(id, mensagemRequest);
        return ResponseEntity.ok(mensagem);
    }
}
