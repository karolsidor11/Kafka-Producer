package pl.sidor.kafkaproducer.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import pl.sidor.kafkaproducer.api.MessageResponse;

@Service
@RequiredArgsConstructor
public class MessageService {

    private final KafkaTemplate<String, String> kafkaTemplate;

    @Value(value = "${message.topic.name}")
    private String topicName;

    public MessageResponse execute(String message) {
        kafkaTemplate.send(topicName, message);
        return MessageResponse.of(message, topicName);
    }
}
