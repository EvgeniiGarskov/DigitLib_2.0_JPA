create table Person(
id serial primary key,
fullName varchar(40) not null unique,
yearOfBirth int not null check (yearOfBirth > 1900)
)

select * from person;

insert into person (fullname, yearOfBirth) values ('Иванов Иван Иванович', 1970);
insert into person (fullname, yearOfBirth) values ('Петров Петр Петрович', 1960);
insert into person (fullname, yearOfBirth) values ('Алексеев Алексей Алексеевич', 1989);
insert into person (fullname, yearOfBirth) values ('Познер Владимир Владимирович', 1944);
insert into person (fullname, yearOfBirth) values ('Федоров Мирон Янович', 1985);

create table Book(
id serial,
person_id int references person("id") on delete set null,
title varchar(60) not null,
author varchar(60) not null,
year int not null check (year > 1900),
created_at timestamp
)

select * from book;

insert into book (person_id, title, author, "year") values (1, 'Над пропастью во ржи', 'Джером Сэлинджер', 1951);
insert into book (title, author, "year") values ('День опричника', 'Владимир Сорокин', 2006);
insert into book (person_id, title, author, "year") values (3, 'Тайные виды на гору Фудзи', 'Владимир Пелевин', 2018);
insert into book (title, author, "year") values ('Философия Java', 'Брюс Эккель', 2018);
insert into book (title, author, "year") values ('Психопатология обыденной жизни', 'Фрейд Зигмунд', 1904);
insert into book (title, author, "year") values ('Игра в бисер', 'Герман Гессе', 1943);
insert into book (person_id, title, author, "year") values (2, 'Бытие и время', 'Мартин  Хайдеггер', 1927);