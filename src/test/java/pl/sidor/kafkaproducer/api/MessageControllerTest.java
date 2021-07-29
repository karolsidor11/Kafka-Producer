package pl.sidor.kafkaproducer.api;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@DirtiesContext
@EmbeddedKafka(partitions = 1, brokerProperties = {"listeners=PLAINTEXT://localhost:9092", "port=9092"})
public class MessageControllerTest {

    @Autowired
    private MessageController messageController;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Value(value = "${message.topic.name}")
    private String topicName;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
    }

    @Test
    public void sendMessage() {
        // given:
        String message = "Hello Kafka";

        // when:
        ResponseEntity<MessageResponse> response = messageController.sendMessage(message);

        // then:
        assertNotNull(response);
        assertEquals(response.getBody().getMessage(), message);
        assertEquals(response.getBody().getTopicName(), topicName);
        assertEquals(response.getStatusCode(), HttpStatus.CREATED);
        assertEquals(response.getStatusCodeValue(), 201);
    }

    @Test
    public void shouldSendMessage() throws Exception {
        // given:
        String message = "Hello Kafka";

        // then:
        mockMvc.perform(post("/api/v1/message").content(message))
                .andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value(message))
                .andExpect(MockMvcResultMatchers.jsonPath("$.topicName").value(topicName));
    }
}