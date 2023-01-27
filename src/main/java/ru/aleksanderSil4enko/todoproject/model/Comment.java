package ru.aleksanderSil4enko.todoproject.model;

import lombok.Data;

import javax.persistence.*;
@Entity
@Table(name = "comment")
@Data

public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;
    @ManyToOne
    @JoinColumn(name = "report_id", referencedColumnName = "id")
    private Report report;

    @Column(name = "text")
    private String text;
}
