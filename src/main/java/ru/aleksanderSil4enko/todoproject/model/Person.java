package ru.aleksanderSil4enko.todoproject.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@Table(name = "person")
@AllArgsConstructor
@NoArgsConstructor
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;
    @Column(name = "title")
    private String title;

    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    private Role role;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "email")
    private String email;
    @Column(name = "password")
    private String password;

    @ManyToOne
    @JoinColumn(name = "department_id", referencedColumnName = "id")
    private Department department;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name="person_task",
            joinColumns=  @JoinColumn(name="person_id", referencedColumnName="id"),
            inverseJoinColumns= @JoinColumn(name="task_id", referencedColumnName="id_task") )
    private Set<Task> tasks = new HashSet<Task>();

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name="person_report",
            joinColumns=  @JoinColumn(name="person_id", referencedColumnName="id"),
            inverseJoinColumns= @JoinColumn(name="report_id", referencedColumnName="id") )
    private Set<Report> reports = new HashSet<Report>();
}
