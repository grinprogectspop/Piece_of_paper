create table catalog
(
    catalog_id   int8 not null,
    catalog_name varchar(255),
    description  varchar(2048),
    primary key (catalog_id)
);

create table document_catalog
(
    document_id int8 not null,
    catalog_id  int8 not null,
    primary key (catalog_id, document_id)
);

create table template_document
(
    template_document_id int8 not null,
    description          varchar(2048),
    template_name        varchar(255),
    template_url         varchar(255),
    primary key (template_document_id)
);

alter table if exists document_catalog
    add constraint document_catalog
    foreign key (catalog_id)
    references catalog;


alter table if exists document_catalog
    add constraint catalog_document
    foreign key (document_id)
    references document;
