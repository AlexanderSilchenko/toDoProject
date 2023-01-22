package ru.aleksanderSil4enko.todoproject.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.aleksanderSil4enko.todoproject.model.Person;
import ru.aleksanderSil4enko.todoproject.repository.PersonRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class PersonService {
    private final PersonRepository personRepository;

    public List<Person> findAll() {
        return personRepository.findAll();
    }

    public Person findById(int id) {
        return personRepository.findById(id).orElseThrow();
    }

    //регистрация пользователя
    public Person create(Person person) {
        return personRepository.save(person);
    }
}
