package pl.sidor.kafkaproducer.api;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.sidor.kafkaproducer.service.MessageService;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "api/v1/message")
public class MessageController {

    private final MessageService messageService;

    @PostMapping
    public ResponseEntity<MessageResponse> sendMessage(@RequestBody String message) {
        MessageResponse response = messageService.execute(message);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
