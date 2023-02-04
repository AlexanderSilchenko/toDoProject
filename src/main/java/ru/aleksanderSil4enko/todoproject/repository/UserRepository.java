package ru.aleksanderSil4enko.todoproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.aleksanderSil4enko.todoproject.model.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
}
