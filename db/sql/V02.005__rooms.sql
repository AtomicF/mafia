--drop table rooms;

create table rooms
(
    room_id       serial      not null primary key,
    name          varchar(50) not null,
    password_hash bigint      not null,
    game_id       serial,
    users_amount  integer     not null,

    constraint fk_room2game foreign key (game_id) references games (game_id)
);


