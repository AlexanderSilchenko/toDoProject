package ru.aleksanderSil4enko.todoproject.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.aleksanderSil4enko.todoproject.model.Person;
import ru.aleksanderSil4enko.todoproject.service.PersonService;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/person")
public class PersonController {
    private final PersonService personService;

    @GetMapping
    public List<Person> getAll(){
        return personService.findAll();
    }

    @GetMapping("{id}")
    public Person get(@PathVariable long id) {
        return personService.findById(id);
    }

    @PostMapping
    public Person create(@RequestBody Person person) {
        return personService.save(person);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable long id) {
        personService.delete(id);
    }

    @PutMapping("{id}")
    public Person update(@PathVariable long id,
                         @RequestBody Person person) {
        return personService.update(id, person);
    }

    @PatchMapping("{id}")
    public Person partialUpdate(@PathVariable long id,
                                @RequestBody Person person) {
        return personService.partialUpdate(id, person);
    }
}
