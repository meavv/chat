package ru.job4j.chat.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.job4j.chat.model.Message;
import ru.job4j.chat.service.ServiceChat;
import java.util.List;

@RestController
@RequestMapping("/messages")
public class MessageController {

    private final ServiceChat service;

    public MessageController(ServiceChat service) {
        this.service = service;
    }

    @GetMapping("/")
    public List<Message> findAll() {
        return service.findAllMessage();
    }

    @PostMapping("/")
    public ResponseEntity<Message> create(@RequestBody Message message) {
        return new ResponseEntity<>(
                service.createMessage(message),
                HttpStatus.CREATED
        );
    }

}
