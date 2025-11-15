package br.com.fiap.gsjava.controller;

import br.com.fiap.gsjava.DTO.MensagemRequest;
import br.com.fiap.gsjava.model.Usuario;
import br.com.fiap.gsjava.producer.MessageProducer;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rabbit")
public class MessageController {

    private final MessageProducer producer;

    public MessageController(MessageProducer producer) {
        this.producer = producer;
    }

    @PostMapping("/mensagemRbbt")
    public ResponseEntity<?> enviar(@Valid @RequestBody MensagemRequest request) {
        producer.sendMessage(request);
        return ResponseEntity.ok("Mensagem enviada para a fila");
    }
}
