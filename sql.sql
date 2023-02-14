 -- create database GraduationProjectManagement;
 use GraduationProjectManagement;

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
ALTER TABLE SchoolYears ADD UNIQUE(name);

UPDATE SchoolYears SET name='2020 - 2021' WHERE id='3'

/*
SELECT * FROM SchoolYears
*/

create table Majors(
	id int IDENTITY(1,1) PRIMARY KEY,
	majorsId VARCHAR(20) NOT NULL ,
	name NVARCHAR(200) NOT NULL,
	description NVARCHAR(200),
)
ALTER TABLE Majors ADD UNIQUE(majorsId)



/*
SELECT * FROM Majors
Delete from Majors
*/

create table Courses(
	id int IDENTITY(1,1) PRIMARY KEY,
	name NVARCHAR(200) NOT NULL,
	description NVARCHAR(200),
	studyTime VARCHAR(20) NOT NULL,
	majors int FOREIGN KEY REFERENCES Majors(id)
)
ALTER TABLE Courses ADD UNIQUE(name)
/*
SELECT * FROM Courses
Delete from Majors
*/
SELECT Courses.id, Courses.name, Courses.description, Majors.name as majors FROM Courses, Majors WHERE Courses.majors = Majors.id

create table Classes(
	id int IDENTITY(1,1) PRIMARY KEY,
	name NVARCHAR(200) NOT NULL,
	description NVARCHAR(200),
	course int FOREIGN KEY REFERENCES Courses(id)
)

ALTER TABLE Classes ADD UNIQUE(name)



SELECT * FROM Classes
SELECT Classes.id, Classes.name, Courses.name as course, Majors.name as majors, Courses.studyTime, Classes.description FROM Classes, Courses, Majors WHERE Classes.course = Courses.id and Courses.majors = Majors.id ORDER BY Courses.studyTime, name

create table Teachers(
	id int IDENTITY(1,1) PRIMARY KEY,
	name NVARCHAR(200) NOT NULL,
	academicRank NVARCHAR(100) NOT NULL,
	majors int FOREIGN KEY REFERENCES Majors(id),
	phonenumber VARCHAR(20) NOT NULL,
	email VARCHAR(200) NOT NULL,
)

ALTER TABLE Teachers ADD UNIQUE(phonenumber, email)
SELECT * FROM Teachers

SELECT Teachers.id, Teachers.name, Teachers.academicRank, Majors.name as majors, Teachers.phonenumber, Teachers.email FROM Teachers, Majors WHERE Teachers.majors = Majors.id ORDER BY Teachers.name

SELECT DISTINCT Majors.name FROM Classes, Courses, Majors WHERE Courses.id = Classes.course and Majors.id = Courses.majors and Classes.name = 'CT3A'
SELECT DISTINCT Majors.name FROM Classes, Courses, Majors WHERE Courses.id = Classes.course and Majors.id = Courses.majors and Courses.name = 'CT3'
SELECT DISTINCT Courses.name FROM Classes, Courses, Majors WHERE Courses.id = Classes.course and Majors.id = Courses.majors and Classes.name = 'CT3A'
SELECT DISTINCT Classes.name FROM Classes, Courses, Majors WHERE Courses.id = Classes.course and Majors.id = Courses.majors and Majors.id = '1'
SELECT DISTINCT Majors.name FROM  Courses, Majors WHERE Majors.id = Courses.majors and Courses.name = 'CT2'

create table Students(
	id VARCHAR(12) PRIMARY KEY,
	name NVARCHAR(100) NOT NULL,
	gender NVARCHAR (10) NOT NULL,
	birthday VARCHAR(100) NOT NULL,
	class int FOREIGN KEY REFERENCES Classes(id),
	phonenumber VARCHAR(20) NOT NULL UNIQUE,
	email VARCHAR(100) NOT NULL UNIQUE,
)

SELECT * FROM Students
SELECT Students.id, Students.name, Students.gender, Students.birthday, Classes.name as class, Students.phonenumber, Students.email FROM Students, Classes WHERE Students.class = Classes.id 
GROUP BY Classes.name, Students.id, Students.name, Students.gender, Students.birthday,Students.phonenumber, Students.email
ORDER BY  Classes.name, Students.id
INSERT INTO Students(id,name,gender,birthday,class,phonenumber,email)VALUES('CT030401',N'Bùi Thị B',N'Nữ','29-02-2000','10','0128679646','bbt@gmail.com')

SELECT DISTINCT Majors.name FROM Majors 
                ORDER BY Majors.name
/**/