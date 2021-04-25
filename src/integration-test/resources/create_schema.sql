--create schema example;

create table MACHINE(
name varchar2(20) not null
);

insert into MACHINE values ('VW');

create table ANIMAL(
age int not null,
name varchar2(20) not null
);

insert into ANIMAL values (5,'JACK');

commit;