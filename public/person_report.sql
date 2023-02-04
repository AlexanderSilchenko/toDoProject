create table person_report
(
    report_id bigint not null
        constraint fkpg0t6tl40st94vek90vdiuwdb
            references report,
    person_id bigint not null
        constraint fkt3t5d0c6v42s1cv1f8ums8pcm
            references person
);

alter table person_report
    owner to postgres;

