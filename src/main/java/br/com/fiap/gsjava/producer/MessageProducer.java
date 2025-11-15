package br.com.fiap.gsjava.producer;


import br.com.fiap.gsjava.DTO.MensagemRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class MessageProducer {

    private static final Logger log = LoggerFactory.getLogger(MessageProducer.class);

    private final RabbitTemplate rabbitTemplate;
    private final String exchangeName;
    private final String routingKey;

    public MessageProducer(
            RabbitTemplate rabbitTemplate,
            @Value("${app.rabbitmq.exchange}") String exchangeName,
            @Value("${app.rabbitmq.routingkey}") String routingKey) {

        this.rabbitTemplate = rabbitTemplate;
        this.exchangeName = exchangeName;
        this.routingKey = routingKey;
    }

    // 🔥 Agora envia apenas o MensagemRequest (sem usuário)
    public void sendMessage(MensagemRequest dto) {
        log.info("Enviando mensagem para RabbitMQ: {}", dto);

        rabbitTemplate.convertAndSend(exchangeName, routingKey, dto);

        log.info("Mensagem enviada com sucesso");
    }
}
