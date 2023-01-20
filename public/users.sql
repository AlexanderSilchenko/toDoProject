create table users
(
    user_id    serial
        primary key
        unique,
    first_name varchar(50)  not null,
    last_name  varchar(50)  not null,
    email      varchar(100) not null
        unique,
    password   varchar(256) not null
);

alter table users
    owner to postgres;

