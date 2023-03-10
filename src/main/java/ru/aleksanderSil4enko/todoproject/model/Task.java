package ru.aleksanderSil4enko.todoproject.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
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

    @Column(name = "date_in", columnDefinition = "DATE")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dateStart;
    @Column(name = "date_out", columnDefinition = "DATE")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dateFinish;
    @Column(name = "date_off", columnDefinition = "DATE")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dateDone;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private Status taskStatus;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name="users_task",
            joinColumns=  @JoinColumn(name="task_id", referencedColumnName="id_task"),
            inverseJoinColumns= @JoinColumn(name="user_id", referencedColumnName="id"))
    private List<User> employers;

    @OneToMany(mappedBy = "task")
    @JsonIgnore
    @ToString.Exclude
    private List<Report> reports;

}
