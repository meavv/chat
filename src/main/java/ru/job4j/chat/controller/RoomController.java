package ru.job4j.chat.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.job4j.chat.model.Room;
import ru.job4j.chat.service.ServiceChat;
import java.util.List;

@RestController
@RequestMapping("/rooms")
public class RoomController {

    private final ServiceChat service;

    public RoomController(ServiceChat service) {
        this.service = service;
    }

    @GetMapping("/")
    public List<Room> findAll() {
       return service.findAllRoom();
    }

    @PostMapping("/")
    public ResponseEntity<Room> create() {
        return service.createRoom();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
       return service.deleteRoom(id);
    }
}
