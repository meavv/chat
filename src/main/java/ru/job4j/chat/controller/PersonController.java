package ru.job4j.chat.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.job4j.chat.model.Person;
import ru.job4j.chat.service.ServiceChat;
import java.util.List;

@RestController
@RequestMapping("/persons")
public class PersonController {

    private final ServiceChat service;

    public PersonController(final ServiceChat service) {
        this.service = service;
    }

    @GetMapping("/")
    public List<Person> findAll() {
       return service.findAllPerson();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Person> findById(@PathVariable int id) {
      return service.findByIdPerson(id);
    }

    @PostMapping("/")
    public ResponseEntity<Person> create(@RequestBody Person person) {
        return service.createPerson(person);
    }

    @PutMapping("/")
    public ResponseEntity<Void> update(@RequestBody Person person) {
        return service.updatePerson(person);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
       return service.deletePerson(id);
    }
}
