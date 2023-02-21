package ru.aleksanderSil4enko.todoproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.aleksanderSil4enko.todoproject.model.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}