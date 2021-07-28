--liquibase formatted sql
--changeset chundrikov:create_consumer_table_test
create table consumers (
        id                serial not null primary key,
        name              varchar(200) not null,
        phone             varchar(36),
        email             varchar(100) not null,
        address           varchar(200)
)

--liquibase formatted sql
--changeset chundrikov:fill_consumer_table
insert into consumers(id, name, phone, email, address)
  values (1, 'Vasiya', '79865693214', 'example@yandex.ru', 'Test street');