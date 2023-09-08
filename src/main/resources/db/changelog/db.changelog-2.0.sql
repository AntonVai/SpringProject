--liquibase formatted sql

--changeset javaKava:1
create table if not exists chats
(
    id bigserial primary key,
    name varchar(64) not null unique
);

