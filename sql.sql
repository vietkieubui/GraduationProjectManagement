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
SELECT * FROM Users WHERE username = 'admin' AND password = 'c4ca4238a0b923820dcc509a6f75849b'
SELECT * FROM Users
*/


create table SchoolYears(
	id int IDENTITY(1,1) PRIMARY KEY,
	name VARCHAR(20) NOT NULL,
)

/*
SELECT * FROM SchoolYears
*/

create table Majors(
	id int IDENTITY(1,1) PRIMARY KEY,
	majorsId VARCHAR(20) NOT NULL,
	name NVARCHAR(200) NOT NULL,
	description NVARCHAR(200),
)

/*
SELECT * FROM Majors
Delete from Majors
*/

create table Courses(
	id int IDENTITY(1,1) PRIMARY KEY,
	name NVARCHAR(200) NOT NULL,
	description NVARCHAR(200),
	majors int FOREIGN KEY REFERENCES Majors(id)
)
/*
SELECT * FROM Courses, 
Delete from Majors
*/
SELECT Courses.id, Courses.name, Courses.description, Majors.name as majors FROM Courses, Majors WHERE Courses.majors = Majors.id
