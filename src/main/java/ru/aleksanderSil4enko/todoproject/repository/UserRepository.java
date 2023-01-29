package ru.aleksanderSil4enko.todoproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.aleksanderSil4enko.todoproject.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
}
