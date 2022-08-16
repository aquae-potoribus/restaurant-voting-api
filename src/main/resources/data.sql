INSERT INTO USERS (name, email, password)
VALUES ('User', 'user@yandex.ru', '{noop}password'),
       ('Admin', 'admin@gmail.com', '{noop}admin');

INSERT INTO USER_ROLES (role, user_id)
VALUES ('USER', 1),
       ('ADMIN', 2),
       ('USER', 2);

INSERT INTO MENUS (owner)
VALUES (1),
       (2),
       (2);

INSERT INTO RESTAURANT (ID, OWNER)
VALUES (1, 2),
(2, 2),
    (3, 2);