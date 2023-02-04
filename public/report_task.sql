create table report_task
(
    task_id   bigint not null
        constraint fk5pl6wtkq4hoat0m9phm7fa70r
            references task,
    report_id bigint not null
        constraint fkmbim7n2u2px4lkqqg5whcbcjh
            references report,
    primary key (task_id, report_id)
);

alter table report_task
    owner to postgres;

