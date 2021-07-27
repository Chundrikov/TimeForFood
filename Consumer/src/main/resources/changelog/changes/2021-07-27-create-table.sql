--liquibase formatted sql
--changeset chundrikov:create_consumer_table
create table consumers (
        id                serial not null primary key,
        name              varchar(200) not null,
        phone             varchar(36),
        email             varchar(100) not null,
        address           varchar(200)
)