package ru.aleksanderSil4enko.todoproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.aleksanderSil4enko.todoproject.model.Task;
import ru.aleksanderSil4enko.todoproject.service.PersonService;
import ru.aleksanderSil4enko.todoproject.service.TaskService;

import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {
    private final TaskService taskService;
    private final PersonService personService;

    @Autowired
    public TaskController(TaskService taskService,
                          PersonService personService) {
        this.taskService = taskService;
        this.personService = personService;
    }
    //все задания
    @GetMapping
    @PreAuthorize("hasAuthority('tasks:read')")
    public List<Task> getAll() {
        return taskService.findAll();
    }
    //задание по id
    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('tasks:read')")
    public Task taskInfo(@PathVariable long id) {
        return taskService.findById(id);
    }
    //добавить задание
    @PostMapping
    @PreAuthorize("hasAuthority('tasks:write')")
    public Task create(@RequestBody Task task) {
        return taskService.save(task);
    }
    //удалить задание по id
    @DeleteMapping("{id}")
    @PreAuthorize("hasAuthority('tasks:write')")
    public void delete(@PathVariable long id) {
        taskService.delete(id);
    }
    //обновить задание полностью
    @PutMapping("{id}")
    @PreAuthorize("hasAuthority('tasks:write')")
    public Task update(@PathVariable long id,
                         @RequestBody Task task) {
        return taskService.update(id, task);
    }
    //частично обновить задание
    @PatchMapping("{id}")
    @PreAuthorize("hasAuthority('tasks:write')")
    public Task partialUpdate(@PathVariable long id,
                                @RequestBody Task task) {
        return taskService.partialUpdate(id, task);
    }
    //добавить в задание работника
    @PatchMapping("{id}/add_employer/{personId}")
    @PreAuthorize("hasAuthority('tasks:write')")
    public Task addPersonToTask(@PathVariable long id,
                                @PathVariable long personId) {
        return taskService.addPerson(id, personService.findById(personId));
    }
    //убрать работника из задания
    @PatchMapping("{id}/remove_employer/{personId}")
    @PreAuthorize("hasAuthority('tasks:write')")
    public Task removePersonFromTask(@PathVariable long id,
                                @PathVariable long personId) {
        return taskService.removePerson(id, personService.findById(personId));
    }
}
