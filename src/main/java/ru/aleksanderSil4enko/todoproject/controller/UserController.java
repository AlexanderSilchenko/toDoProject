package ru.aleksanderSil4enko.todoproject.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.aleksanderSil4enko.todoproject.model.User;
import ru.aleksanderSil4enko.todoproject.service.UserService;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    @GetMapping
    public List<User> getAll(){
        return userService.findAll();
    }

    @GetMapping("{id}")
    public User get(@PathVariable long id) {
        return userService.findById(id);
    }

    @PostMapping
    public User create(@RequestBody User user) {
        return userService.save(user);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable long id) {
        userService.delete(id);
    }

    @PutMapping("{id}")
    public User update(@PathVariable long id,
                         @RequestBody User user) {
        return userService.update(id, user);
    }

    @PatchMapping("{id}")
    public User partialUpdate(@PathVariable long id,
                                @RequestBody User user) {
        return userService.partialUpdate(id, user);
    }
}
