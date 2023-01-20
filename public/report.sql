create table report
(
    report_id  serial
        primary key
        unique,
    person_id  integer not null
        constraint report_person_person_id_fk
            references person,
    task_id    integer not null
        constraint report_task_task_id_fk
            references task,
    date       date    not null,
    time_start date    not null,
    time_stop  date    not null,
    comment_id integer
        constraint report_comment_comment_id_fk
            references comment
);

alter table report
    owner to postgres;

