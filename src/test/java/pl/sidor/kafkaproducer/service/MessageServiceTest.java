package pl.sidor.kafkaproducer.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import pl.sidor.kafkaproducer.api.MessageResponse;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;


@RunWith(SpringRunner.class)
@SpringBootTest
@DirtiesContext
@EmbeddedKafka(partitions = 1, brokerProperties = { "listeners=PLAINTEXT://localhost:9092", "port=9092" })
public class MessageServiceTest {

    @Autowired
    private MessageService messageService;

    @Value(value = "${message.topic.name}")
    private String topicName;

    @Test
    public void executeTest() {
        // given:
        String message ="Hello Kafka";

        // when:
        MessageResponse response = messageService.execute(message);

        // then:
        assertNotNull(response);
        assertEquals(response.getMessage(), message);
        assertEquals(response.getTopicName(), topicName);
    }
}