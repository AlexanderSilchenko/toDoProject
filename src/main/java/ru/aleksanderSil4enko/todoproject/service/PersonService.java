package ru.aleksanderSil4enko.todoproject.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.aleksanderSil4enko.todoproject.model.Person;
import ru.aleksanderSil4enko.todoproject.repository.PersonRepository;

import javax.transaction.Transactional;
import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class PersonService {
    private final PersonRepository personRepository;

    public List<Person> findAll() {
        return personRepository.findAll();
    }

    public Person findById(long id) {
        return personRepository.findById(id).orElseThrow();
    }

    //регистрация пользователя
    public Person save(Person person) {
        return personRepository.save(person);
    }

    public void delete(long id) {
        personRepository.deleteById(id);
    }
    @Transactional
    public Person update(long id, Person person) {
        return personRepository.findById(id)
                .map(x -> {
                    x.setTitle(person.getTitle());
                    x.setDepartment(person.getDepartment());
                    x.setRole(person.getRole());
                    x.setFirstName(person.getFirstName());
                    x.setLastName(person.getLastName());
                    x.setEmail(person.getEmail());
                    x.setPassword(person.getPassword());
                    return x;
                }).orElseThrow();
    }
    @Transactional
    public Person partialUpdate(long id, Person person) {
        return personRepository.findById(id)
                .map(x -> {
                    log.info(x.toString());
                    if(person.getTitle() != null && !person.getTitle().equals(""))
                        x.setTitle(person.getTitle());
                    if(person.getDepartment() != null)
                        x.setDepartment(person.getDepartment());
                    if(person.getRole() != null)
                        x.setRole(person.getRole());
                    if(person.getFirstName() != null && !person.getFirstName().equals(""))
                        x.setFirstName(person.getFirstName());
                    if(person.getLastName() != null && !person.getLastName().equals(""))
                        x.setLastName(person.getLastName());
                    if(person.getEmail() != null && !person.getEmail().equals(""))
                        x.setEmail(person.getEmail());
                    if(person.getPassword() != null && !person.getPassword().equals(""))
                        x.setPassword(person.getPassword());
                    return x;
                }).orElseThrow();
    }
}
