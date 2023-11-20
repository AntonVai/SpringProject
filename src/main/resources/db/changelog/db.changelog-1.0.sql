--liquibase formatted sql

--changeset javaKava:1
create table if not exists users
(
    id bigserial primary key,
    email varchar not null unique,
    nickname varchar(64),
    role varchar(32),
    created_at  timestamp,
    modified_at timestamp,
    birth_date  date,
    created_by  varchar(32),
    modified_by varchar(32)
);
