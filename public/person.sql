create table person
(
    id            bigserial
        primary key,
    first_name    varchar(255),
    last_name     varchar(255),
    title         varchar(255),
    department_id bigint not null
        constraint fkqqg5cmjglivmepn16lqb1m8ux
            references department,
    user_id       bigint not null
        constraint uk_77e12pjkm9v423j9hd3u10bk1
            unique
        constraint fkemsnreyk6g37uoja1ngeog5sp
            references users
);

alter table person
    owner to postgres;

