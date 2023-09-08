--liquibase formatted sql

--changeset javaKava:1
alter table users
    add column chat_id int references chats(id)
