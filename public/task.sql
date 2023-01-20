create table task
(
    task_id     serial
        primary key
        unique,
    person_id   integer      not null
        constraint task_person_person_id_fk
            references person,
    title       varchar(100) not null,
    description varchar(256) not null,
    date_start  date         not null,
    date_done   date         not null,
    date_finish date,
    comment_id  integer
        constraint task_comment_comment_id_fk
            references comment,
    is_done     boolean      not null
);

alter table task
    owner to postgres;

