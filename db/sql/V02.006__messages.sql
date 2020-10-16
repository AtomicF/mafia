-- drop table messages;

create table messages
(
    message_id serial not null primary key,
    game_id    serial not null,
    user_id    serial not null,
    text       text   not null,
    addressee  serial not null,

    constraint fk_game foreign key (game_id) references games(game_id),
    constraint fk_user foreign key (user_id) references users(user_id)
);


