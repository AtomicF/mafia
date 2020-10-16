--drop table users cascade;

create table users
(
    user_id       serial      not null primary key,
    password_hash text        not null,
    login         text unique not null,
    statistics_id int,

    constraint fk_user2statistics foreign key (statistics_id) references
        statistics (statistics_id)
);
