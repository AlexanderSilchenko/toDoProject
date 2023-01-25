package ru.aleksanderSil4enko.todoproject.controller;

import lombok.AllArgsConstructor;
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
    public List<Department> getAll() {
        return departmentService.findAll();
    }

    @GetMapping("{id}")
    public Department get(@PathVariable long id) {
        return departmentService.findById(id);
    }

    @PostMapping
    public Department save(@RequestBody Department department) {
        return departmentService.save(department);
    }
}

