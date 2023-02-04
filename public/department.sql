create table department
(
    id   bigserial
        primary key,
    name varchar(255) not null
        unique
);

alter table department
    owner to postgres;

