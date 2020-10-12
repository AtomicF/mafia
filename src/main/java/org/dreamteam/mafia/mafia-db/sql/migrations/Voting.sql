create table "Table"."Voting"
(
    vote_id      serial  not null
        constraint voiting_pk
            primary key,
    game_id      serial  not null,
    character_id serial  not null,
    day_number   integer not null,
    votes_amount integer not null
);

alter table "Table"."Voiing"
    owner to postgres;

create unique index voiting_vote_id_uindex
    on "Table"."Voiting" (vote_id);

