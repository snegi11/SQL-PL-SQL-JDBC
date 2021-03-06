/* SRISHTI NEGI , A20351640 */


/*Create FamilyPackage Table*/
 
CREATE TABLE FamilyPackage
(       
"Id" INT NOT NULL,
Address VARCHAR(50) NOT NULL,
Phone VARCHAR(20) NOT NULL,
PRIMARY KEY ("Id"),
UNIQUE (phone)
);


/* Create Type Table*/

CREATE TABLE "Type"
(
"Type" VARCHAR(15) NOT NULL,
Description VARCHAR(60) NOT NULL,
PRIMARY KEY("Type")
);

/*Create RecCenterMember Table*/

CREATE TABLE RecCenterMember(
"Id" INT NOT NULL,
F_name CHAR(10) NOT NULL,
L_name CHAR(10) NOT NULL,
Dob DATE NOT NULL,
PRIMARY KEY("Id"),
Family_Id INT,
FOREIGN KEY(Family_Id) REFERENCES FamilyPackage("Id")
);

/*Create Instructor Table*/

CREATE TABLE Instructor(
"Id" INT NOT NULL PRIMARY KEY,
F_name CHAR(10) NOT NULL,
L_name CHAR(10) NOT NULL,
Member_Id INT,
FOREIGN KEY(Member_Id) REFERENCES RecCenterMember("Id")
);

/*Create Class Table*/

CREATE TABLE "Class" (
"Id" INT NOT NULL PRIMARY KEY,
Title VARCHAR(30)NOT NULL ,
"Type" VARCHAR(15) NOT NULL,
Instructor INT NOT NULL,
Season VARCHAR(20)NOT NULL,
"Year" NUMBER NOT NULL, 
CONSTRAINT chk_Season CHECK (Season IN ('Spring', 'Summer', 'Fall', 'Winter')),
FOREIGN KEY ("Type") REFERENCES "Type" ("Type"),
FOREIGN KEY (Instructor) REFERENCES Instructor("Id")
);

/*Create Enrollment Table*/

CREATE TABLE Enrollment (
Class_Id INT NOT NULL,
Member_Id INT NOT NULL, 
"Cost" INT NOT NULL,
PRIMARY KEY ( Class_Id , Member_Id ),
FOREIGN KEY (Class_Id) REFERENCES "Class"("Id"),
FOREIGN KEY (Member_Id) REFERENCES RecCenterMember("Id")
);


