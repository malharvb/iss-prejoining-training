create database server_auth_db;

use server_auth_db;

create table user_info (
	acc_email VARCHAR(255) PRIMARY KEY,
    acc_password VARCHAR(255)
);

select * from user_info;