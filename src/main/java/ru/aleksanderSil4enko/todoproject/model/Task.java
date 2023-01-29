package ru.aleksanderSil4enko.todoproject.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "task")
@Data
@AllArgsConstructor
@NoArgsConstructor
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
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dateStart;
    @Column(name = "date_out")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dateFinish;
    @Column(name = "date_off")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dateDone;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private Status taskStatus;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name="person_task",
            joinColumns=  @JoinColumn(name="task_id", referencedColumnName="id_task"),
            inverseJoinColumns= @JoinColumn(name="person_id", referencedColumnName="id") )
    private List<Person> employers;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name="report_task",
            joinColumns=  @JoinColumn(name="task_id", referencedColumnName="id_task"),
            inverseJoinColumns= @JoinColumn(name="report_id", referencedColumnName="id") )
    private Set<Report> reports = new HashSet<Report>();

}
