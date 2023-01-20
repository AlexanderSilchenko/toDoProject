create table department
(
    department_id serial
        primary key
        unique,
    name          varchar(50) not null
        unique,
    "order"       integer     not null
);

alter table department
    owner to postgres;

