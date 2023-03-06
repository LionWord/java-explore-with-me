create table if not exists public.categories
(
    id   bigint generated by default as identity
        primary key,
    name varchar not null
        unique
        constraint catnamecheck
            check ((char_length((name)::text) >= 3) AND (char_length((name)::text) <= 120))
);

create table if not exists public.users
(
    id    bigint generated by default as identity
        primary key,
    email varchar(256) not null,
    name  varchar(256) not null
);

create table if not exists public.locations
(
    id        bigint generated by default as identity
        primary key,
    event_id  bigint,
    latitude  double precision,
    longitude double precision
);

create table if not exists public.events
(
    id                 bigint generated by default as identity
        primary key,
    annotation         varchar               not null
        constraint anncheck
            check ((char_length((annotation)::text) >= 20) AND (char_length((annotation)::text) <= 2000)),
    category           bigint                not null
        references public.categories,
    confirmed_requests bigint,
    created_on         timestamp,
    description        varchar
        constraint desccheck
            check ((char_length((description)::text) >= 20) AND (char_length((description)::text) <= 7000)),
    event_date         timestamp             not null,
    initiator          bigint                not null
        references public.users,
    location           bigint                not null
        references public.locations,
    paid               boolean default false not null,
    participant_limit  integer default 0,
    published_on       timestamp,
    request_moderation boolean default true,
    state              varchar,
    title              varchar               not null
        constraint titlecheck
            check ((char_length((title)::text) >= 3) AND (char_length((title)::text) <= 120)),
    views              bigint
);

create table if not exists public.participation_requests
(
    id        bigint generated by default as identity
        primary key,
    created   timestamp,
    event     bigint
        references public.events,
    requester bigint
        references public.users,
    status    varchar
);

create table if not exists public.compilations
(
    id     bigint generated by default as identity
        primary key,
    pinned boolean default false,
    title  varchar
        constraint comptitlecheck
            check ((char_length((title)::text) >= 3) AND (char_length((title)::text) <= 120))
);

create table if not exists public.comps_events
(
    event_id       bigint not null
        references public.events,
    compilation_id bigint not null
        references public.compilations,
    primary key (event_id, compilation_id)
);
