create table if not exists categories
(
    id   bigint generated by default as identity
        primary key,
    name varchar not null unique
        constraint catNameCheck check (char_length(name) >= 3 and char_length(name) <= 120)
);

create table if not exists users
(
    id    bigint generated by default as identity
        primary key,
    email varchar(256) not null,
    name  varchar (256) not null
);

create table if not exists locations
(
    id        bigint generated by default as identity
        primary key,
    event_id  bigint,
    latitude  double precision,
    longitude double precision
);

create table if not exists events
(
    id                 bigint generated by default as identity
    primary key,
    annotation         varchar not null
        constraint annCheck check (char_length(annotation) >= 20 and char_length(annotation) <= 2000),
    category           bigint not null references categories(id),
    confirmed_requests bigint,
    created_on         timestamp,
    description        varchar
        constraint descCheck check (char_length(description) >= 20 and char_length(description) <= 7000),
    event_date         timestamp not null ,
    initiator          bigint not null references users(id),
    location           bigint not null references locations(id),
    paid               boolean not null default false,
    participant_limit  integer default 0,
    published_on       timestamp,
    request_moderation boolean default true,
    state              varchar,
    title              varchar not null
        constraint titleCheck check (char_length(title) >= 3 and char_length(title) <= 120),
    views              bigint
);

alter table locations
add constraint eventsLocation foreign key(event_id) references events(id);

create table if not exists participation_requests
(
    id        bigint generated by default as identity
        primary key,
    created   timestamp,
    event     bigint references events(id),
    requester bigint references users(id),
    status    varchar
);

create table if not exists compilations
(
    id     bigint generated by default as identity
        primary key,
    pinned boolean default false,
    title  varchar
        constraint compTitleCheck check (char_length(title) >= 3 and char_length(title) <= 120)
);

create table if not exists comps_events
(

    event_id       bigint references events(id),
    compilation_id bigint references compilations(id),
    primary key (event_id, compilation_id)

);


