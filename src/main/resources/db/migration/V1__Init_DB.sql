create sequence hibernate_sequence start 1 increment 1;
create table usr
(
    user_id                    bigint  not null
        constraint usr_pkey
            primary key,
    email                      varchar(255),
    is_account_non_expired     boolean not null,
    is_account_non_locked      boolean not null,
    is_credentials_non_expired boolean not null,
    is_enabled                 boolean not null,
    name                       varchar(255),
    password                   varchar(255),
    patronymic                 varchar(255),
    surname                    varchar(255)
);

create table user_role
(
    user_id bigint not null
        constraint user_role_usr
            references usr,
    roles   varchar(255)
);
create table document_type
(
    document_type_id   bigint not null
        constraint document_type_pkey
            primary key,
    document_type_name varchar(255)
);
create table secrecy_level
(
    secrecy_id   bigint not null
        constraint secrecy_level_pkey
            primary key,
    secrecy_name varchar(255)
);
create table document
(
    document_id      bigint                not null
        constraint document_pkey
            primary key,
    creation_date    timestamp,
    deleted          boolean default false not null,
    document_name    varchar(255),
    password         varchar(255),
    secrecy_level_id bigint
        constraint document_secrecy_level
            references secrecy_level,
    document_type_id bigint
        constraint document_document_type
            references document_type
);

create table document_access
(
    document_access_id bigint not null
        constraint document_access_pkey
            primary key,
    document_id        bigint
        constraint document_access_document
            references document,
    user_id            bigint
        constraint document_access_usr
            references usr
);

create table access(
    document_access_id bigint not null
        constraint access_document_access
            references document_access,
    access_types       varchar(255)
);



create table document_version
(
    document_version_id bigint not null
        constraint document_version_pkey
            primary key,
    date                timestamp,
    url                 varchar(255),
    version_name        varchar(255),
    document_id         bigint
        constraint document_version_document
            references document
);


create table document_history
(
    document_history_id bigint not null
        constraint document_history_pkey
            primary key,
    action_date         timestamp,
    description         varchar(255),
    user_id             bigint
        constraint document_history_usr
            references usr,
    document_id         bigint
        constraint document_history_document
            references document,
    document_version_id bigint
        constraint document_history_document_version
            references document_version
);


create table action
(
    document_history_id bigint not null
        constraint action_document_history
            references document_history,
    actions             varchar(255)
);








create table position
(
    position_id   bigint not null
        constraint position_pkey
            primary key,
    position_name varchar(255)
);



create table user_position
(
    user_id     bigint not null
        constraint user_position_usr
            references usr,
    position_id bigint not null
        constraint user_position_position
            references position,
    constraint user_position_pkey
        primary key (position_id, user_id)
);


