package ru.job4j.chat.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import ru.job4j.chat.model.Room;
import ru.job4j.chat.service.ServiceChat;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;

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
        return new ResponseEntity<>(
               service.createRoom(),
                HttpStatus.CREATED
        );
    }

    @GetMapping("/{id}")
    public Room findById(@PathVariable int id) {
        Optional<Room> room = service.findByIdRoom(id);
        return room.orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Room in not found!"
        ));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
       service.deleteRoom(id);
       return ResponseEntity.ok().build();
    }
}
