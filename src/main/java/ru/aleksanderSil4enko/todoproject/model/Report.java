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
    @Column(name = "id")
    private long id;

    @Column(name = "date")
    private Date date;
    @Column(name = "time_on")
    private Date timeStart;
    @Column(name = "time_off")
    private Date timeStop;

    @ManyToMany(mappedBy = "reports")
    private List<Person> employers;

    @ManyToMany(mappedBy = "reports")
    private List<Task> tasks;

    @OneToMany(mappedBy = "report")
    private List<Comment> comments;
}
