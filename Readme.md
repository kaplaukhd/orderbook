# **Spring security application.**

 _The code for filling the database is below._

`CREATE table user.users
(
id       bigint auto_increment,
username varchar(30) unique,
password varchar(80) not null,
name     varchar(50) not null,
lastname varchar(50) not null,
email    varchar(50) not null unique,
age      int         not null,
primary key (id)
);`

`create table user.roles
(
id   bigint auto_increment ,
name varchar(50) not null,
primary key (id)
);`

`create table user.user_roles
(
user_id bigint not null,
role_id bigint not null,
primary key (user_id, role_id),
foreign key (user_id) references users (id),
foreign key (role_id) references roles (id)
);`

`insert into roles (name)
values ('ROLE_USER'),
('ROLE_ADMIN');`

`insert into users (username, password, name, lastname, email, age)
values ('admin', '$2a$12$uzAOTiS3at4Bbs7BXTevD.ImIEuARVzb5gkbGGhmg0w45zmharDPm', 'YourName', 'YourSurname',
'youremail@gmail.com', 24);`

`insert into users (username, password, name, lastname, email, age)
values ('user', '$2a$12$uzAOTiS3at4Bbs7BXTevD.ImIEuARVzb5gkbGGhmg0w45zmharDPm', 'YourName', 'YourSurname',
'youremail@gmail.com', 24);`

`insert into user_roles (user_id, role_id)
values (1, 1);`

`insert into user_roles (user_id, role_id)
values (1, 2);`

`insert into user_roles (user_id, role_id)
values (2, 1);`