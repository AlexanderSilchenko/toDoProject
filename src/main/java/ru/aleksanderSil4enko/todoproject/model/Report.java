package ru.aleksanderSil4enko.todoproject.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "report")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Report {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "time_on")
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private Date timeStart;
    @Column(name = "time_off")
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private Date timeStop;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name="person_report",
            joinColumns=  @JoinColumn(name="report_id", referencedColumnName="id"),
            inverseJoinColumns= @JoinColumn(name="person_id", referencedColumnName="id"))
    private List<Person> employers;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name="report_task",
            joinColumns=  @JoinColumn(name="report_id", referencedColumnName="id"),
            inverseJoinColumns= @JoinColumn(name="task_id", referencedColumnName="id_task")
    )
    private List<Task> tasks;

    @OneToMany(mappedBy = "report")
    private List<Comment> comments;
}
