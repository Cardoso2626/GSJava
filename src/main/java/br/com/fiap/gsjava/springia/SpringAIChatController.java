package br.com.fiap.gsjava.springia;

import br.com.fiap.gsjava.DTO.PromptDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/springia")
public class SpringAIChatController {
    private final SpringAIChatService chatService;

    public SpringAIChatController(SpringAIChatService chatService){
        this.chatService = chatService;
    }

    @PostMapping("/generate")
    public ResponseEntity<Map> generate(@RequestBody PromptDTO promptDTO){
        return new ResponseEntity<>(Map.of("ollama", chatService.run(promptDTO.userPrompt())), HttpStatus.OK);
    }
}
