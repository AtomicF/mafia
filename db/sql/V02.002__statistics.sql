--drop table statistics;

create table statistics
(
    statistics_id          serial not null primary key,
    games_total_as_mafia   bigint,
    games_total_as_sheriff bigint,
    games_total_as_citizen bigint,
    games_won_as_mafia     bigint,
    games_won_as_sheriff   bigint,
    games_won_as_citizen   bigint
);