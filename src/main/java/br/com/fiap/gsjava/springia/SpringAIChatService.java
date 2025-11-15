package br.com.fiap.gsjava.springia;

import org.springframework.stereotype.Service;
import org.springframework.ai.chat.client.ChatClient;

@Service
public class SpringAIChatService {
    private final ChatClient.Builder chatClientBuilder;
    public SpringAIChatService(ChatClient.Builder chatClientBuilder){
        this.chatClientBuilder = chatClientBuilder;
    }

    public String run(String userPrompt){
        var chatClient = chatClientBuilder.build();
        return chatClient
                .prompt()
                .user(userPrompt)
                .call()
                .content()
                .replace("\n", "");
    }
}
