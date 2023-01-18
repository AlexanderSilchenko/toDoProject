package ru.aleksanderSil4enko.todoproject.model;

import java.util.Date;
import java.util.List;

public class Task {
    private int id;
    private int personId;
    private String title;
    private String description;
    private Date dateStart;
    private Date dateDone;
    private Date dateFinish;
    private int commentId;
    private boolean isDone;
}
