package ru.aleksanderSil4enko.todoproject.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.aleksanderSil4enko.todoproject.model.Department;
import ru.aleksanderSil4enko.todoproject.model.Person;
import ru.aleksanderSil4enko.todoproject.repository.DepartmentRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class DepartmentService {
    private final DepartmentRepository departmentRepository;

    public List<Department> findAll() {
        return departmentRepository.findAll();
    }

    public Department findById(long id) {
        return departmentRepository.findById(id).orElseThrow();
    }

    //регистрация пользователя
    public Department save(Department department) {
        return departmentRepository.save(department);
    }
}
