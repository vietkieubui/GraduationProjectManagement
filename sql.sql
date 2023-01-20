 -- create database GraduationProjectManagement;
 use GraduationProjectManagement

 create table Users(
	id int IDENTITY(1,1) PRIMARY KEY,
	name NVARCHAR(255) NOT NULL,
	username VARCHAR(255) NOT NULL,
	phonenumber VARCHAR(20) NOT NULL,
	password VARCHAR(255) NOT NULL,
 );

/*
SELECT * FROM Users;
Delete  from Users
SELECT * FROM Users
*/
