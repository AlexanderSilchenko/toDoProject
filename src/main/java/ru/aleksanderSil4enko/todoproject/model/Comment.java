package ru.aleksanderSil4enko.todoproject.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;
@Entity
@Table(name = "comment")
@Data
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private int id;

    @Column(name = "task_id")
    private int taskId;

    @Column(name = "user_id")
    private int userId;

    @Column(name = "timestamp")
    private Date timestamp;

    @Column(name = "text")
    private String text;
}
