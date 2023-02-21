package ru.aleksanderSil4enko.todoproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.aleksanderSil4enko.todoproject.model.*;
import ru.aleksanderSil4enko.todoproject.service.TaskService;
import ru.aleksanderSil4enko.todoproject.service.UserService;

import javax.persistence.EntityExistsException;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    //обработка иск.ситуации (тип1)
    //с указанием в Header: своего сообщения-"No user present!")
    //с указанием в ответе: класса ошибки NoSuchElementException.class
    //чтоб статус ответа тоже остался ошибкой -"404 NOT FOUND"
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<String> handle(){
        return  new ResponseEntity<>("No such user!", HttpStatus.NOT_FOUND);
    }

    //класс exception EntityExistsException.class
    @ExceptionHandler(EntityExistsException.class)
    public ResponseEntity<String> handleBadEmail(){
        return new ResponseEntity<>("Bad email!", HttpStatus.BAD_REQUEST);
    }

    //класс exception IllegalArgumentException.class
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleExistsEmail(){
        return  new ResponseEntity<>("Already exists!", HttpStatus.BAD_REQUEST);
    }

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    @PreAuthorize("hasAuthority('users:read')")
    public List<User> getAll(){
        return userService.findAll();
    }

    @GetMapping("/{userId}")
    @PreAuthorize("@userDetailsServiceImpl.hasUserId(authentication, #userId) or hasAuthority('users:read')")
    public User userInfo(@PathVariable long userId) {
        return userService.findById(userId);
    }

    @GetMapping("/tasks/{userId}")
    @PreAuthorize("@userDetailsServiceImpl.hasUserId(authentication, #userId) or hasAuthority('tasks:read')")
    public List<Task> userTasks(@PathVariable long userId) {
        return userService.findById(userId).getTasks();
    }

    @GetMapping("/reports/{userId}")
    @PreAuthorize("@userDetailsServiceImpl.hasUserId(authentication, #userId) or hasAuthority('reports:read')")
    public List<Report> userReport(@PathVariable long userId) {
        return userService.findById(userId).getReports();
    }

    @GetMapping("/comments/{userId}")
    @PreAuthorize("@userDetailsServiceImpl.hasUserId(authentication, #userId) or hasAuthority('reports:read')")
    public List<Comment> userComments(@PathVariable long userId) {
        return userService.findById(userId).getComments();
    }

    @PostMapping("/create_task/{userId}")
    @PreAuthorize("@userDetailsServiceImpl.hasUserId(authentication, #userId) or hasAuthority('tasks:write')")
    public User userAddTask(@PathVariable long userId,
                            @RequestBody Task task) {
        return userService.addTask(userId, task);
    }
    @PostMapping("/create_report/{userId}")
    @PreAuthorize("@userDetailsServiceImpl.hasUserId(authentication, #userId) or hasAuthority('tasks:write')")
    public User userAddReport(@PathVariable long userId,
                              @RequestParam(name="task", required = true) long taskId,
                              @RequestBody Report report) {
        return userService.addTasksReport(userId, taskId, report);
    }
    @PostMapping("/create_comment/{userId}")
    @PreAuthorize("@userDetailsServiceImpl.hasUserId(authentication, #userId) or hasAuthority('comments:write')")
    public User userAddComment(@PathVariable long userId,
                               @RequestParam(name="report", required = true) long reportId,
                               @RequestBody Comment comment) {
        return userService.addReportsComment(userId, reportId, comment);
    }
    //добавляем юзера
    @PostMapping("/create")
    public User create(@RequestBody User user) {
        return userService.create(user);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('users:write')")
    public void delete(@PathVariable long id) {
        userService.delete(id);
    }

    @PutMapping("/{userId}")
    @PreAuthorize("hasAuthority('users:write')")
    public User update(@PathVariable long userId,
                         @RequestBody User user) {
        return userService.update(userId, user);
    }

    @PatchMapping("/{userId}")
    @PreAuthorize("@userDetailsServiceImpl.hasUserId(authentication, #userId) or hasAuthority('users:write')")
    public User partialUpdate(@PathVariable long userId,
                                @RequestBody User user) {
        return userService.partialUpdate(userId, user);
    }

    @PostMapping("/role/{userId}")
    @PreAuthorize("hasAuthority('role:write')")
    public User setRoleUser(@PathVariable("userId") long id,
                            @RequestParam(name="role", required = true) Role role) {
        return userService.setRoleUser(id, role);
    }

}
