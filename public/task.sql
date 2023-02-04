create table task
(
    id_task     bigserial
        primary key,
    date_off    timestamp,
    date_out    timestamp,
    date_in     timestamp,
    description varchar(255),
    status      varchar(255),
    title       varchar(255)
);

alter table task
    owner to postgres;

