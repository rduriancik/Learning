CREATE TABLE Course (
courseId char(5),
subjectId char(4) not null,
courseNumber integer,
title varchar(50) not null,
numOfCredits integer,
primary key (courseId)
);

CREATE TABLE Student (
ssn char(9),
firstName varchar(25),
mi char(1),
lastName varchar(25),
birthDate date,
street varchar(25),
phone char(11),
zipCode char(5),
deptId char(4),
primary key (ssn)
);

CREATE TABLE Enrollment (
ssn char(9),
courseId char(5),
dateRegistered date,
grade char(1),
primary key (ssn, courseId),
foreign key (ssn) references
Student(ssn),
foreign key (courseId) references
Course(courseId)
);

INSERT INTO Student VALUES (1, 'John', 'M', 'Doe', '1996-08-12', 'Oxford Street', '02342312342', '06585', 'BIO'),
(2, 'John', 'M', 'Doe', '1996-08-12', 'Oxford Street', '02342312342', '06585', 'BIO'),
(3, 'John', 'M', 'Doe', '1996-08-12', 'Oxford Street', '02342312342', '06585', 'BIO'),
(4, 'John', 'M', 'Doe', '1996-08-12', 'Oxford Street', '02342312342', '06585', 'BIO'),
(5, 'John', 'M', 'Doe', '1996-08-12', 'Oxford Street', '02342312342', '06585', 'BIO'),
(6, 'John', 'M', 'Doe', '1996-08-12', 'Oxford Street', '02342312342', '06585', 'BIO'),
(7, 'John', 'M', 'Doe', '1996-08-12', 'Oxford Street', '02342312342', '06585', 'MATH'),
(8, 'John', 'M', 'Doe', '1996-08-12', 'Oxford Street', '02342312342', '06585', 'MATH'),
(9, 'John', 'M', 'Doe', '1996-08-12', 'Oxford Street', '02342312342', '06585', 'MATH'),
(10, 'John', 'M', 'Doe', '1996-08-12', 'Oxford Street', '02342312342', '06585', 'MATH'),
(11, 'John', 'M', 'Doe', '1996-08-12', 'Oxford Street', '02342312342', '06585', 'CHEM'),
(12, 'John', 'M', 'Doe', '1996-08-12', 'Oxford Street', '02342312342', '06585', 'CHEM'),
(13, 'John', 'M', 'Doe', '1996-08-12', 'Oxford Street', '02342312342', '06585', 'CHEM'),
(14, 'John', 'M', 'Doe', '1996-08-12', 'Oxford Street', '02342312342', '06585', 'CHEM'),
(15, 'John', 'M', 'Doe', '1996-08-12', 'Oxford Street', '02342312342', '06585', 'CHEM'),
(16, 'John', 'M', 'Doe', '1996-08-12', 'Oxford Street', '02342312342', '06585', 'CHEM'),
(17, 'John', 'M', 'Doe', '1996-08-12', 'Oxford Street', '02342312342', '06585', 'CHEM'),
(18, 'John', 'M', 'Doe', '1996-08-12', 'Oxford Street', '02342312342', '06585', 'CHEM'),
(19, 'John', 'M', 'Doe', '1996-08-12', 'Oxford Street', '02342312342', '06585', 'CHEM'),
(20, 'John', 'M', 'Doe', '1996-08-12', 'Oxford Street', '02342312342', '06585', 'CHEM'),
(21, 'John', 'M', 'Doe', '1996-08-12', 'Oxford Street', '02342312342', '06585', 'CHEM'),
(22, 'John', 'M', 'Doe', '1996-08-12', 'Oxford Street', '02342312342', '06585', 'CHEM'),
(23, 'John', 'M', 'Doe', '1996-08-12', 'Oxford Street', '02342312342', '06585', 'CS'),
(24, 'John', 'M', 'Doe', '1996-08-12', 'Oxford Street', '02342312342', '06585', 'CS'),
(25, 'John', 'M', 'Doe', '1996-08-12', 'Oxford Street', '02342312342', '06585', 'CS'),
(26, 'John', 'M', 'Doe', '1996-08-12', 'Oxford Street', '02342312342', '06585', 'CS'),
(27, 'John', 'M', 'Doe', '1996-08-12', 'Oxford Street', '02342312342', '06585', 'CS'),
(28, 'John', 'M', 'Doe', '1996-08-12', 'Oxford Street', '02342312342', '06585', 'CS'),
(29, 'John', 'M', 'Doe', '1996-08-12', 'Oxford Street', '02342312342', '06585', 'CS'),
(30, 'John', 'M', 'Doe', '1996-08-12', 'Oxford Street', '02342312342', '06585', 'CS'),
(31, 'John', 'M', 'Doe', '1996-08-12', 'Oxford Street', '02342312342', '06585', 'CS'),
(32, 'John', 'M', 'Doe', '1996-08-12', 'Oxford Street', '02342312342', '06585', 'CS'),
(33, 'John', 'M', 'Doe', '1996-08-12', 'Oxford Street', '02342312342', '06585', 'CS'),
(34, 'John', 'M', 'Doe', '1996-08-12', 'Oxford Street', '02342312342', '06585', 'CS'),
(35, 'John', 'M', 'Doe', '1996-08-12', 'Oxford Street', '02342312342', '06585', 'CS'),
(36, 'John', 'M', 'Doe', '1996-08-12', 'Oxford Street', '02342312342', '06585', 'CS'),
(37, 'John', 'M', 'Doe', '1996-08-12', 'Oxford Street', '02342312342', '06585', 'CS'),
(38, 'John', 'M', 'Doe', '1996-08-12', 'Oxford Street', '02342312342', '06585', 'CS');
