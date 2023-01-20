package ru.aleksanderSil4enko.todoproject.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "report")
@Data
public class Report {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "report_id")
    private long id;

    @OneToOne
    @JoinColumn(name = "person_id", referencedColumnName = "person_id")
    private Person person;

    @OneToMany
    @JoinColumn(name = "task_id", referencedColumnName = "task_id")
    private List<Task> tasks;

    @Column(name = "date")
    private Date date;
    @Column(name = "time_start")
    private Date timeStart;
    @Column(name = "time_stop")
    private Date timeStop;

    @OneToOne
    @JoinColumn(name = "person_id", referencedColumnName = "person_id")
    private Comment comment;
}
