create table person_task
(
    task_id   bigint not null
        constraint fk9ahn5lc58fp7a92ahv2vav3wx
            references task,
    person_id bigint not null
        constraint fkia3rjqn5lflsnr0coa0lmi0d4
            references person
);

alter table person_task
    owner to postgres;

