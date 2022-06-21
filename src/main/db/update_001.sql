create table role
(
    id       serial primary key not null,
    name    varchar(2000)
);

insert into role (name)
values ('ADMIN');

    insert into role (name)
values ('USER');