package ru.job4j.chat.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import ru.job4j.chat.model.Role;
import ru.job4j.chat.service.ServiceChat;

import java.util.ArrayList;
import java.util.Optional;

@RestController
@RequestMapping("/roles")
public class RoleController {

    private final ServiceChat service;

    public RoleController(final ServiceChat service) {
        this.service = service;
    }

    @GetMapping("/")
    public ResponseEntity<ArrayList<Role>> findAll() {
        var list = service.findAllRole();
        return ResponseEntity.of(Optional.of(
                new ArrayList<>(list)
        ));
    }

    @GetMapping("/{id}")
    public Role findById(@PathVariable int id) {
        Optional<Role> role = service.findByIdRole(id);
        return role.orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Role is not found"
        ));
    }
}
