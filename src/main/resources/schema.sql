drop table if exists client;
create table client (
    id int auto_increment primary key,
    name varchar(50),
    email varchar(50),
    contactNumber varchar(50),
    cpf varchar(11),
    bornDate date
);