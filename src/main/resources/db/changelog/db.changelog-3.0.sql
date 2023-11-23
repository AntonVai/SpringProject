--liquibase formatted sql

--changeset javaKava:1
alter table users
    add column password varchar(128) default '{noop}12345';
