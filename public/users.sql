create table users
(
    id       bigserial
        primary key,
    email    varchar(255)
        constraint uk_6dotkott2kjsp8vw4d0m25fb7
            unique,
    password varchar(255),
    role     varchar(255)
);

alter table users
    owner to postgres;

