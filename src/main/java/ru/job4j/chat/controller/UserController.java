package ru.job4j.chat.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import ru.job4j.chat.model.Person;
import ru.job4j.chat.service.ServiceChat;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {

    private static final Logger LOGGER = LoggerFactory
            .getLogger(UserController.class.getSimpleName());

    private final ObjectMapper objectMapper;

    private ServiceChat services;
    private BCryptPasswordEncoder encoder;

    public UserController(ObjectMapper objectMapper, ServiceChat services,
                          BCryptPasswordEncoder encoder) {
        this.objectMapper = objectMapper;
        this.services = services;
        this.encoder = encoder;
    }

    @GetMapping("/")
    public List<Person> findAll() {
        return services.findAllPerson();
    }

    @GetMapping("/{id}")
    public Person findById(@PathVariable int id) {
        Optional<Person> person = services.findByIdPerson(id);
        return person.orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "User in not found!"
                ));
    }

    @PostMapping("/sign-up")
    public ResponseEntity<Person> signUp(@RequestBody Person person) {
        System.out.println(person);
        if (person.getRole() == null) {
            throw new NullPointerException("Role cannot be null");
        }
        if (person.getPassword().length() < 6) {
            throw new IllegalArgumentException("Invalid password. "
                    + "Password length must be more than 5 characters.");
        }
        person.setPassword(encoder.encode(person.getPassword()));
        return new ResponseEntity<>(
                services.createPerson(person),
                HttpStatus.CREATED
        );
    }

    @PutMapping("/")
    public ResponseEntity<Void> update(@RequestBody Person person) {
        services.updatePerson(person);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        services.deletePerson(id);
        return ResponseEntity.ok().build();
    }

    @ExceptionHandler(value = { IllegalArgumentException.class })
    public void exceptionHandler(Exception e, HttpServletRequest request,
                                 HttpServletResponse response) throws IOException {
        response.setStatus(HttpStatus.BAD_REQUEST.value());
        response.setContentType("application/json");
        response.getWriter().write(objectMapper.writeValueAsString(new HashMap<>() { {
            put("message", e.getMessage());
            put("type", e.getClass());
        }}));
        LOGGER.error(e.getLocalizedMessage());
    }

}