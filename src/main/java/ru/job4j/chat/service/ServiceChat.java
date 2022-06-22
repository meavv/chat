package ru.job4j.chat.service;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import ru.job4j.chat.model.Message;
import ru.job4j.chat.model.Person;
import ru.job4j.chat.model.Role;
import ru.job4j.chat.model.Room;
import ru.job4j.chat.repository.MessageRep;
import ru.job4j.chat.repository.PersonRep;
import ru.job4j.chat.repository.RoleRep;
import ru.job4j.chat.repository.RoomRep;

import java.util.ArrayList;
import java.util.List;

@Service
public class ServiceChat {

    private final MessageRep messages;
    private final RoleRep roles;
    private final PersonRep persons;
    private final RoomRep rooms;

    public ServiceChat(MessageRep messages, RoleRep roles, PersonRep persons, RoomRep rooms) {
        this.messages = messages;
        this.roles = roles;
        this.persons = persons;
        this.rooms = rooms;
    }

    public List<Message> findAllMessage() {
        List<Message> list = new ArrayList<>();
        messages.findAll().forEach(list::add);
        return list;
    }

    public ResponseEntity<Message> createMessage(Message message) {
        return new ResponseEntity<>(
                messages.save(message),
                HttpStatus.CREATED
        );
    }

    public List<Person> findAllPerson() {
        List<Person> list = new ArrayList<>();
        persons.findAll().forEach(list::add);
        return list;
    }

    public ResponseEntity<Person> findByIdPerson(int id) {
        var person = persons.findById(id);
        return new ResponseEntity<>(
                person.orElse(new Person()),
                person.isPresent() ? HttpStatus.OK : HttpStatus.NOT_FOUND
        );
    }

    public ResponseEntity<Person> createPerson(Person person) {
        return new ResponseEntity<>(
                persons.save(person),
                HttpStatus.CREATED
        );
    }

    public ResponseEntity<Void> updatePerson(Person person) {
        persons.save(person);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePerson(int id) {
        Person person = findByIdPerson(id).getBody();
        if (person != null) {
            persons.delete(person);
        }
        return ResponseEntity.ok().build();
    }

    public List<Role> findAllRole() {
        List<Role> list = new ArrayList<>();
        roles.findAll().forEach(list::add);
        return list;
    }

    public ResponseEntity<Role> findByIdRole(int id) {
        var role = roles.findById(id);
        return new ResponseEntity<>(
                role.orElse(new Role()),
                role.isPresent() ? HttpStatus.OK : HttpStatus.NOT_FOUND
        );
    }

    public List<Room> findAllRoom() {
        List<Room> list = new ArrayList<>();
        rooms.findAll().forEach(list::add);
        return list;
    }

    public ResponseEntity<Room> createRoom() {
        return new ResponseEntity<>(
                rooms.save(new Room()),
                HttpStatus.CREATED
        );
    }

    public ResponseEntity<Void> deleteRoom(int id) {
        Room room = new Room();
        room.setId(id);
        rooms.delete(room);
        return ResponseEntity.ok().build();
    }
}
