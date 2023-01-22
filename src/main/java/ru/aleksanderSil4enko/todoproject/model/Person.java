package ru.aleksanderSil4enko.todoproject.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
@Data
@Entity
@Table(name = "person")
@NoArgsConstructor
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "person_id")
    private long id;

    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "email")
    private String email;
    @Column(name = "password")
    private String password;
    @Column(name = "title")
    private String title;
    @Column(name = "order")
    private int order;
    @ManyToOne
    @JoinColumn(name = "department_id", referencedColumnName = "department_id")
    private Department department;

    @ManyToOne
    @JoinColumn(name = "task_id", referencedColumnName = "task_id")
    private Task tasks;

    @ManyToOne
    @JoinColumn(name = "report_id", referencedColumnName = "report_id")
    private Report reports;

    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    private Role role;

}
