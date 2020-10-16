--drop table votings;

create table votings
(
    vote_id      serial  not null primary key,
    game_id      serial  not null,
    character_id serial  not null,
    day_number   serial  not null,
    votes_amount integer not null,

    constraint fk_voting2character foreign key (character_id) references characters (character_id),
    constraint fk_voting2games foreign key (game_id) references games (game_id)
);


