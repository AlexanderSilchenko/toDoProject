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
    private long id;

    @ManyToOne
    @JoinColumn(name = "task_id", referencedColumnName = "task_id")
    private Task task;

    @OneToOne
    @JoinColumn(name = "person_id", referencedColumnName = "person_id")
    private Person person;

    @Column(name = "timestamp")
    private Date timestamp;

    @Column(name = "text")
    private String text;
}
