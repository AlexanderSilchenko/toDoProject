package ru.aleksanderSil4enko.todoproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.aleksanderSil4enko.todoproject.model.Department;

public interface DepartmentRepository extends JpaRepository<Department,Long> {
}
