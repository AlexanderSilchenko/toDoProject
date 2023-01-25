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
    @Column(name = "id")
    private long id;

    @ManyToOne
    @JoinColumn(name = "task_id", referencedColumnName = "id")
    private Task task;
    @ManyToOne
    @JoinColumn(name = "report_id", referencedColumnName = "report_id")
    private Report report;

    @ManyToOne
    @JoinColumn(name = "person_id", referencedColumnName = "id")
    private Person autor;

    @Column(name = "timestamp")
    private Date timestamp;
    @Column(name = "text")
    private String text;
}
