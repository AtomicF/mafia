create table users2rooms
(
    user_id       int not null primary key,
    room_id       int not null,

    constraint fk_users2rooms_users foreign key (user_id) references users (user_id),
    constraint fk_users2rooms_rooms foreign key (room_id) references rooms (room_id)
);
