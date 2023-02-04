package ru.aleksanderSil4enko.todoproject.controller;

import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.aleksanderSil4enko.todoproject.model.Department;
import ru.aleksanderSil4enko.todoproject.service.DepartmentService;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/department")
public class DepartmentController {
    private final DepartmentService departmentService;

    @GetMapping
    @PreAuthorize("hasAuthority('users:read')")
    public List<Department> getAll() {
        return departmentService.findAll();
    }

    @GetMapping("{id}")
    @PreAuthorize("hasAuthority('users:read')")
    public Department get(@PathVariable long id) {
        return departmentService.findById(id);
    }

    @PostMapping
    @PreAuthorize("hasAuthority('users:read')")
    public Department create(@RequestBody Department department) {
        return departmentService.save(department);
    }

    @DeleteMapping("{id}")
    @PreAuthorize("hasAuthority('users:read')")
    public void delete(@PathVariable long id) {
        departmentService.delete(id);
    }
}

