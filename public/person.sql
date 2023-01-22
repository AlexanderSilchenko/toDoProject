create table person
(
    person_id     serial
        constraint person_pk
            primary key
        unique,
    title         varchar(50)  not null,
    "order"       integer      not null,
    department_id integer      not null
        constraint person_department_department_id_fk
            references department,
    role          varchar      not null,
    first_name    varchar(50)  not null,
    last_name     varchar(50)  not null,
    email         varchar(255) not null,
    password      varchar(255) not null,
    task_id       integer      not null,
    report_id     integer      not null
);

alter table person
    owner to postgres;

