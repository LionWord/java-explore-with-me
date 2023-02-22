create table if not exists endpoint_hit
(
    id        bigint generated by default as identity
        primary key,
    app       varchar(64),
    uri       varchar,
    ip        varchar(39),
    timestamp timestamp
);


