package pl.sidor.kafkaproducer.api;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MessageResponse {

    private String message;
    private String topicName;
    private LocalDateTime actualDateTime;

    public static MessageResponse of(String message, String topicName) {
        MessageResponse messageResponse = new MessageResponse();
        messageResponse.setActualDateTime(LocalDateTime.now());
        messageResponse.setMessage(message);
        messageResponse.setTopicName(topicName);

        return messageResponse;
    }
}
