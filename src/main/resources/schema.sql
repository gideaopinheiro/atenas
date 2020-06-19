drop table if exists client;
drop table if exists guy;

create table client (
    id int auto_increment primary key,
    name varchar(50),
    email varchar(50),
    contactNumber varchar(50),
    cpf varchar(11),
    bornDate date
);

create table guy(
    id int auto_increment primary key,
    login varchar(128),
    password varchar(256),
    admin bool
);