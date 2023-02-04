package ru.aleksanderSil4enko.todoproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.aleksanderSil4enko.todoproject.model.Report;
import ru.aleksanderSil4enko.todoproject.model.Task;
import ru.aleksanderSil4enko.todoproject.model.User;
import ru.aleksanderSil4enko.todoproject.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{userId}")
    @PreAuthorize("@userDetailsServiceImpl.hasUserId(authentication, #userId) or hasAuthority('users:read')")
    public User userInfo(@PathVariable long userId) {
        return userService.findById(userId);
    }

    @GetMapping
    @PreAuthorize("hasAuthority('users:read')")
    public List<User> getAll(){
        return userService.findAll();
    }

    @PostMapping
    @PreAuthorize("hasAuthority('users:write')")
    public User create(@RequestBody User user) {
        return userService.save(user);
    }

    @DeleteMapping("{id}")
    @PreAuthorize("hasAuthority('users:write')")
    public void delete(@PathVariable long id) {
        userService.delete(id);
    }

    @PutMapping("{id}")
    @PreAuthorize("hasAuthority('users:write')")
    public User update(@PathVariable long id,
                         @RequestBody User user) {
        return userService.update(id, user);
    }

    @PatchMapping("{id}")
    @PreAuthorize("hasAuthority('users:write')")
    public User partialUpdate(@PathVariable long id,
                                @RequestBody User user) {
        return userService.partialUpdate(id, user);
    }
}
