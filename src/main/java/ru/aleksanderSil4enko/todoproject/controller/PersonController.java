package ru.aleksanderSil4enko.todoproject.controller;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.aleksanderSil4enko.todoproject.model.Person;
import ru.aleksanderSil4enko.todoproject.model.Report;
import ru.aleksanderSil4enko.todoproject.model.Task;
import ru.aleksanderSil4enko.todoproject.model.User;
import ru.aleksanderSil4enko.todoproject.service.PersonService;
import ru.aleksanderSil4enko.todoproject.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/persons")
public class PersonController {
    private final PersonService personService;
    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }
    @GetMapping
    @PreAuthorize("hasAuthority('users:read')")
    public List<Person> getAll(){
        return personService.findAll();
    }

    @GetMapping("{id}")
    @PreAuthorize("hasAuthority('users:read')")
    public Person get(@PathVariable long id) {
        return personService.findById(id);
    }

    @PostMapping
    @PreAuthorize("hasAuthority('users:write')")
    public Person create(@RequestBody Person person) {
        return personService.save(person);
    }

    @DeleteMapping("{id}")
    @PreAuthorize("hasAuthority('users:write')")
    public void delete(@PathVariable long id) {
        personService.delete(id);
    }

    @PutMapping("{id}")
    @PreAuthorize("hasAuthority('users:write')")
    public Person update(@PathVariable long id,
                         @RequestBody Person person) {
        return personService.update(id, person);
    }

    @PatchMapping("{id}")
    @PreAuthorize("hasAuthority('users:write')")
    public Person partialUpdate(@PathVariable long id,
                                @RequestBody Person person) {
        return personService.partialUpdate(id, person);
    }
    //выдать пользователю все его задания
    @GetMapping("/{userId}/tasks")
    @PreAuthorize("@userDetailsServiceImpl.hasUserId(authentication, #userId) or hasAuthority('tasks:read')")
    public List<Task> userTasks(@PathVariable long userId) {
        return personService.findById(userId).getTasks();
    }
    //выдать пользователю все его отчёты
    @GetMapping("/{userId}/reports")
    @PreAuthorize("@userDetailsServiceImpl.hasUserId(authentication, #userId) or hasAuthority('reports:read')")
    public List<Report> userReports(@PathVariable long userId) {
        return personService.findById(userId).getReports();
    }
}
