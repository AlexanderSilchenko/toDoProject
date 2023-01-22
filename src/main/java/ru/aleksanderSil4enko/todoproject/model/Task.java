package ru.aleksanderSil4enko.todoproject.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
@Entity
@Table(name = "task")
@Data
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "task_id")
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

    @OneToOne
    @JoinColumn(name = "person_id", referencedColumnName = "person_id")
    private Person autor;

    @OneToMany(mappedBy = "tasks")
    @JsonIgnore
    @ToString.Exclude
    private List<Person> employers;



}
