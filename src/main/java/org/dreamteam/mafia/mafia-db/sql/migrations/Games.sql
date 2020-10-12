create table "Table"."Games"
(
    game_id    serial  not null,
    phase      serial  not null,
    status     boolean not null,
    day_number integer not null
);

alter table "Table"."Games"
    owner to postgres;

create unique index games_day_number_uindex
    on "Table"."Games" (day_number);

create unique index games_game_id_uindex
    on "Table"."Games" (game_id);

