package ru.job4j.chat.service;


import org.springframework.http.ResponseEntity;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
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
import java.util.Optional;

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

    public Message createMessage(Message message) {
        return messages.save(message);
    }

    public List<Person> findAllPerson() {
        List<Person> list = new ArrayList<>();
        persons.findAll().forEach(list::add);
        return list;
    }

    public Optional<Person> findByIdPerson(int id) {
        return persons.findById(id);
    }

    public Person createPerson(Person person) {
       return persons.save(person);
    }

    public Person updatePerson(Person person) {
        return persons.save(person);
    }


    public void deletePerson(int id) {
        findByIdPerson(id).ifPresent(persons::delete);
    }

    public List<Role> findAllRole() {
        List<Role> list = new ArrayList<>();
        roles.findAll().forEach(list::add);
        return list;
    }

    public Optional<Role> findByIdRole(int id) {
        return roles.findById(id);
    }

    public List<Room> findAllRoom() {
        List<Room> list = new ArrayList<>();
        rooms.findAll().forEach(list::add);
        return list;
    }

    public Room createRoom() {
        return rooms.save(new Room());
    }

    public void deleteRoom(int id) {
        Room room = new Room();
        room.setId(id);
        rooms.delete(room);
    }
}
