package ru.job4j.chat.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import ru.job4j.chat.model.Message;
import ru.job4j.chat.model.Person;
import ru.job4j.chat.service.ServiceChat;
import java.util.List;
import java.util.Optional;

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
        if (message.getPerson() == null || message.getRoom() == null
                                                || message.getBody() == null) {
            throw new NullPointerException("cannot be null");
        }
        return new ResponseEntity<>(
                service.createMessage(message),
                HttpStatus.CREATED
        );
    }

    @GetMapping("/{id}")
    public Message findById(@PathVariable int id) {
        Optional<Message> message = service.findByIdMessage(id);
        return message.orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Message in not found!"
        ));
    }

}
