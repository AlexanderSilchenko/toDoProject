create table person
(
    person_id     serial
        constraint person_pk
            primary key
        unique,
    user_id       integer     not null
        unique
        constraint person_users_user_id_fk
            references users,
    title         varchar(50) not null,
    "order"       integer     not null,
    department_id integer     not null
        constraint person_department_department_id_fk
            references department,
    role          varchar     not null
);

alter table person
    owner to postgres;

