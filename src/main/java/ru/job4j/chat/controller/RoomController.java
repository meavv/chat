package ru.job4j.chat.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.job4j.chat.model.Room;
import ru.job4j.chat.repository.RoomRep;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/rooms")
public class RoomController {

    private final RoomRep rooms;

    public RoomController(RoomRep rooms) {
        this.rooms = rooms;
    }

    @GetMapping("/")
    public List<Room> findAll() {
        List<Room> list = new ArrayList<>();
        rooms.findAll().forEach(list::add);
        return list;
    }

    @PostMapping("/")
    public ResponseEntity<Room> create() {
        return new ResponseEntity<>(
                rooms.save(new Room()),
                HttpStatus.CREATED
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        Room room = new Room();
        room.setId(id);
        rooms.delete(room);
        return ResponseEntity.ok().build();
    }
}
