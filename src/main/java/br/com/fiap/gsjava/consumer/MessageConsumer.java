package br.com.fiap.gsjava.consumer;

import br.com.fiap.gsjava.DTO.MensagemRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class MessageConsumer {

    private static final Logger log = LoggerFactory.getLogger(MessageConsumer.class);

    @Value("${app.rabbitmq.queue}")
    private String queueName;

    @RabbitListener(queues = "${app.rabbitmq.queue}")
    public void receiveMessage(MensagemRequest dto) {
        System.out.println("Mensagem recebida: " + dto.getMensagem());
        System.out.println("Nível de estresse: " + dto.getNivelEstresse());
    }
}
