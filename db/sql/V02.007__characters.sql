--drop table characters;

create table characters
(
    character_id serial  not null primary key,
    game_id      serial  not null,
    user_id      serial  not null,
    role_id      serial  not null,
    status       boolean not null,
    vote_id      int,

    constraint fk_characters2users foreign key (user_id) references users (user_id),
    constraint fk_characters2games foreign key (game_id) references games (game_id),
    constraint fk_character2role foreign key (role_id) references roles (role_id)
);




