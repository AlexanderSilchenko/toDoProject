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

    @Column(name = "report_id")
    private long reportId;
    @Column(name = "date")
    private Date date;
    @Column(name = "time_start")
    private Date timeStart;
    @Column(name = "time_stop")
    private Date timeStop;

    @ManyToOne
    @JoinColumn(name = "person_id", referencedColumnName = "id")
    private Person employer;

    @ManyToOne
    @JoinColumn(name = "task_id", referencedColumnName = "id")
    private Task task;

    @OneToMany(mappedBy = "report")
    private List<Comment> comments;
}
