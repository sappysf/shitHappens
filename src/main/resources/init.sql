create table users(
    id bigserial primary key ,
    surname varchar(128),
    password varchar(128),
    birth_date DATE,
    role varchar(128)
);
drop table users;