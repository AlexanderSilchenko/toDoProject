package ru.aleksanderSil4enko.todoproject.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
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

    @Column(name = "date", columnDefinition = "DATE")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date date;
    @Column(name = "time_on")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private Date timeStart;
    @Column(name = "time_off")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private Date timeStop;

    @ManyToOne//(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", nullable = false, referencedColumnName = "id")
    private User employer;

    @ManyToOne//(cascade = CascadeType.ALL)
    @JoinColumn(name = "task_id", nullable = false, referencedColumnName = "id_task")
    private Task task;

    @OneToMany(mappedBy = "report")
    @JsonIgnore
    @ToString.Exclude
    private List<Comment> comments;
}
