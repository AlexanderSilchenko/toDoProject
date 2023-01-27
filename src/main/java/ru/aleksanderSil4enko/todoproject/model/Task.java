package ru.aleksanderSil4enko.todoproject.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "task")
@Data
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_task")
    private long id;

    @Column(name = "title")
    private String title;
    @Column(name = "description")
    private String description;
    @Column(name = "date_in")
    private Date dateStart;
    @Column(name = "date_out")
    private Date dateFinish;
    @Column(name = "date_off")
    private Date dateDone;
    @Column(name = "is_done")
    private boolean isDone;

    @ManyToMany(mappedBy = "tasks")
    private List<Person> employers;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name="report_task",
            joinColumns=  @JoinColumn(name="task_id", referencedColumnName="id_task"),
            inverseJoinColumns= @JoinColumn(name="report_id", referencedColumnName="id") )
    private Set<Report> reports = new HashSet<Report>();

}
