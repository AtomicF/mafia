--drop table games;

create table games
(
    game_id    serial  not null primary key,
    phase      serial  not null,
    status     boolean not null,
    day_number integer not null
);

