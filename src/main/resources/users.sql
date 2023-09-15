-- create table  if not exists USERS (id identity primary, name varchar (255) not null);
CREATE TABLE IF NOT EXISTS USERS (
                                     id INT PRIMARY KEY AUTO_INCREMENT,
                                     name VARCHAR(255) NOT NULL
    );
truncate table USERS;
insert  INTO USERS (name) values ('Macro');
insert  INTO USERS (name) values ('Lisa');
