package ru.aleksanderSil4enko.todoproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.aleksanderSil4enko.todoproject.model.Task;
import ru.aleksanderSil4enko.todoproject.model.User;
import ru.aleksanderSil4enko.todoproject.service.TaskService;
import ru.aleksanderSil4enko.todoproject.service.UserService;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/tasks")
public class TaskController {
    private final TaskService taskService;
    private final UserService userService;

    //обработка иск.ситуации (тип1)
    //с указанием в Header: своего сообщения-"No user present!")
    //с указанием в ответе: класса ошибки NoSuchElementException.class
    //чтоб статус ответа тоже остался ошибкой -"404 NOT FOUND"
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<String> handle(){
        return  new ResponseEntity<>("No such task!", HttpStatus.NOT_FOUND);
    }

    @Autowired
    public TaskController(TaskService taskService,
                          UserService userService) {
        this.taskService = taskService;
        this.userService = userService;
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
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('all:permissions')")
    public void delete(@PathVariable long id) {
        taskService.delete(id);
    }

    //обновить задание полностью
    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('tasks:write')")
    public Task update(@PathVariable long id,
                         @RequestBody Task task) {
        return taskService.update(id, task);
    }

    //частично обновить задание
    @PatchMapping("/{id}")
    @PreAuthorize("hasAuthority('tasks:write')")
    public Task partialUpdate(@PathVariable long id,
                                @RequestBody Task task) {
        return taskService.partialUpdate(id, task);
    }

    //добавить работника в задание
    @PostMapping("/add_employer/{id}")
    @PreAuthorize("hasAuthority('tasks:write')")
    public Task addTaskToUser(@PathVariable long id,
                              @RequestParam(name="user", required = true) User user) {
        return taskService.addUser(id, user);
    }

    //убрать работника из задания
    @PostMapping("/remove_employer/{id}")
    @PreAuthorize("hasAuthority('tasks:write')")
    public Task removeTaskFromUser(@PathVariable long id,
                                   @RequestParam(name="user", required = true) User user) {
        return taskService.removeUser(id, user);
    }
}
