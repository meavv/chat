package ru.job4j.chat.controller;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import ru.job4j.chat.model.Person;
import ru.job4j.chat.service.ServiceChat;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private ServiceChat services;
    private BCryptPasswordEncoder encoder;

    public UserController(ServiceChat services,
                          BCryptPasswordEncoder encoder) {
        this.services = services;
        this.encoder = encoder;
    }

    @PostMapping("/sign-up")
    public void signUp(@RequestBody Person person) {
        person.setPassword(encoder.encode(person.getPassword()));
        services.createPerson(person);
        System.out.println(person);
    }

    @GetMapping("/all")
    public List<Person> findAll() {
        return services.findAllPerson();
    }
}