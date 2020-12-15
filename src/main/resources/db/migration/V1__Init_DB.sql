create sequence hibernate_sequence start 1 increment 1;

create table access (
    user_id int8 not null,
    access_types varchar(255),
    document_id int8 not null
);

create table action (
    document_history_id int8 not null,
    actions varchar(255)
);

create table document (
    document_id int8 not null,
    creation_date timestamp,
    deleted boolean default false not null,
    document_name varchar(255),
    password varchar(255),
    secrecy_level_id int8,
    document_type_id int8,
    primary key (document_id)
);

create table document_history (
    document_history_id int8 not null,
    action_date timestamp,
    description varchar(2048),
    user_id int8,
    document_id int8,
    document_version_id int8,
    primary key (document_history_id)
);

create table document_type (
   document_type_id int8 not null,
   document_type_name varchar(255),
   primary key (document_type_id)
);

create table document_version (
    document_version_id int8 not null,
    date timestamp,
    url varchar(2048),
    version_name varchar(255),
    document_id int8,
    primary key (document_version_id)
);

create table position (
    position_id int8 not null,
    position_name varchar(2048),
    primary key (position_id)
);

create table secrecy_level (
    secrecy_id int8 not null,
    secrecy_name varchar(255),
    primary key (secrecy_id)
);

create table user_position (
    user_id int8 not null,
    position_id int8 not null,
    primary key (position_id, user_id)
);

create table user_role (
    user_id int8 not null,
    roles varchar(255)
);

create table usr (
     user_id int8 not null,
     email varchar(255),
     name varchar(255),
     password varchar(255),
     patronymic varchar(255),
     surname varchar(255),
     primary key (user_id)
);

alter table if exists access
    add constraint access_user_fk
    foreign key (user_id) references usr;

alter table if exists access
    add constraint access_document_fk
    foreign key (document_id) references document;

alter table if exists action
    add constraint action_document_history_fk
    foreign key (document_history_id) references document_history;

alter table if exists document
    add constraint document_secrecy_level_fk
    foreign key (secrecy_level_id) references secrecy_level;

alter table if exists document
    add constraint document_document_type_fk
    foreign key (document_type_id) references document_type;

alter table if exists document_history
    add constraint document_history_user_fk
    foreign key (user_id) references usr;

alter table if exists document_history
    add constraint document_history_document_fk
    foreign key (document_id) references document;

alter table if exists document_history
    add constraint document_history_document_version_fk
    foreign key (document_version_id) references document_version;

alter table if exists document_version
    add constraint document_version_document_fk
    foreign key (document_id) references document;

alter table if exists user_position
    add constraint user_position_position_fk
    foreign key (position_id) references position;

alter table if exists user_position
    add constraint user_position_user_fk
    foreign key (user_id) references usr;

alter table if exists user_role
    add constraint user_role_user_fk
    foreign key (user_id) references usr;