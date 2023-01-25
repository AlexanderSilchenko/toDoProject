package ru.aleksanderSil4enko.todoproject.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
@Entity
@Table(name = "task")
@Data
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "title")
    private String title;
    @Column(name = "description")
    private String description;
    @Column(name = "date_start")
    private Date dateStart;
    @Column(name = "date_finish")
    private Date dateFinish;
    @Column(name = "date_done")
    private Date dateDone;
    @Column(name = "is_done")
    private boolean isDone;

    @ManyToOne
    @JoinColumn(name = "person_id", referencedColumnName = "id")
    private Person employer;

    @OneToMany(mappedBy = "task")
    private List<Report> reports;
}
