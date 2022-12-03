drop database if exists rsvp_db;

create database rsvp_db;

use rsvp_db;

create table rsvp(
    name varchar(64) not null,
    email varchar(64) not null,
    phone varchar(16) not null,
    confirmation_date date,
    comments text,

    primary key(email)
);