create table comment
(
    id        bigserial
        primary key,
    text      varchar(255),
    timestamp timestamp,
    report_id bigint
        constraint fkn2m8whrfw1drq2c67d247br7c
            references report
);

alter table comment
    owner to postgres;

