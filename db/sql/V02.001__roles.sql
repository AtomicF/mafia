--drop table roles;

create table roles
(
    role_id serial      not null primary key,
    role    varchar(20) not null
);


insert into roles (role)
values ('don'),
       ('mafia'),
       ('mafia'),
       ('sheriff'),
       ('citizen'),
       ('citizen'),
       ('citizen'),
       ('citizen'),
       ('citizen'),
       ('citizen');