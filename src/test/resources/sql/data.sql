INSERT INTO chats (id,name)
VALUES (1,'memes'),
       (2,'politic'),
       (3,'it'),
       (4,'main');

SELECT setval('chats_id_seq',(select max(id) from chats));

INSERT INTO users (id,email, nickname, role, birth_date, chat_id)
VALUES (1,'Orau@gmail.com', 'Orau', 'ALESHA', '1995-02-10', (SELECT id FROM chats WHERE name = 'politic')),
       (2,'Hammer@gmail.com', 'Hammer', 'OWNER', '1995-03-10', (SELECT id FROM chats WHERE name = 'main')),
       (3,'Timmy@gmail.com', 'Timmy', 'MEMBER', '1995-04-10', (SELECT id FROM chats WHERE name = 'memes')),
       (4,'Pa2ix@gmail.com', 'Pa2ix', 'MODERATOR', '1995-05-10', (SELECT id FROM chats WHERE name = 'it')),
       (5,'Java@gmail.com', 'Java', 'MEMBER', '1995-06-10', (SELECT id FROM chats WHERE name = 'it'));
SELECT setval('users_id_seq',(select max(id) from users));


