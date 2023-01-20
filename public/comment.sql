create table comment
(
    comment_id serial
        primary key,
    task_id    integer      not null,
    person_id  integer      not null
        constraint comment_person_person_id_fk
            references person,
    timestamp  date         not null,
    text       varchar(500) not null
);

alter table comment
    owner to postgres;

