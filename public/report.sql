create table report
(
    id       bigserial
        primary key,
    time_on  timestamp,
    time_off timestamp
);

alter table report
    owner to postgres;

