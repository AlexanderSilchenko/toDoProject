package ru.aleksanderSil4enko.todoproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.aleksanderSil4enko.todoproject.model.Task;
import ru.aleksanderSil4enko.todoproject.service.TaskService;

import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {
    private final TaskService taskService;

    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }
    //все задания
    @GetMapping
    @PreAuthorize("hasAuthority('tasks:read')")
    public List<Task> getAll() {
        return taskService.findAll();
    }
    //задание по id
    @GetMapping("/id")
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

    //частично обновить задание

}
