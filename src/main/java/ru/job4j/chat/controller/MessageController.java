package ru.job4j.chat.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.job4j.chat.model.Message;
import ru.job4j.chat.repository.MessageRep;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/messages")
public class MessageController {

    private final MessageRep messages;

    public MessageController(MessageRep messages) {
        this.messages = messages;
    }

    @GetMapping("/")
    public List<Message> findAll() {
        List<Message> list = new ArrayList<>();
        messages.findAll().forEach(list::add);
        return list;
    }

    @PostMapping("/")
    public ResponseEntity<Message> create(@RequestBody Message message) {
        return new ResponseEntity<>(
                messages.save(message),
                HttpStatus.CREATED
        );
    }

}
