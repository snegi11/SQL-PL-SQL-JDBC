/* SRISHTI NEGI , A20351640 */

/*** Inserting Values In FamilyPackage ***/

INSERT INTO FamilyPackage("Id", Address, Phone) VALUES (1,'23 Beacon St. Hillside IL','708-555-9384');
INSERT INTO FamilyPackage("Id", Address, Phone) VALUES (2,'4930 Dickens Ave Chicago IL', '312-555-9403');
INSERT INTO FamilyPackage("Id", Address, Phone) VALUES (3,'345 Fullerton St. Chicago IL', '773-555-0032');
INSERT INTO FamilyPackage("Id", Address, Phone) VALUES (4,'34 Maple Ln Elmhurst IL', '312-555-9382');
INSERT INTO FamilyPackage("Id", Address, Phone) VALUES (5,'563 Harvard Ave Lisle IL', '630-555-9321');
COMMIT;

/*** Inserting Values In Type ***/

INSERT INTO "Type"("Type",Description) VALUES ('Craft','Knitting, sewing, ect');
INSERT INTO "Type"("Type",Description) VALUES ('Art','Paining, sculpting, ect');
INSERT INTO "Type"("Type",Description) VALUES ('Exercise','Any courses having to do with physical activity');
INSERT INTO "Type"("Type",Description) VALUES ('Languages','Anything to do with writing, literature, or communication');
INSERT INTO "Type"("Type",Description) VALUES ('Kids','Courses geared towards children 13 and younger');
COMMIT;

/*** Inserting Values In RecCenterMember ***/

INSERT INTO RecCenterMember("Id",F_name,L_name,Dob,Family_Id) VALUES (1,'Abby','Smith',TO_DATE('05-21-1983','MM-DD-YYYY'),1);
INSERT INTO RecCenterMember("Id",F_name,L_name,Dob,Family_Id) VALUES (2,'Mike','O’Shea',TO_DATE('07-04-1968','MM-DD-YYYY'),2);
INSERT INTO RecCenterMember("Id",F_name,L_name,Dob,Family_Id) VALUES (3,'April','O’Shea',TO_DATE('06-23-1954','MM-DD-YYYY'),2);
INSERT INTO RecCenterMember("Id",F_name,L_name,Dob,Family_Id) VALUES (4,'Vijay','Gupta',TO_DATE('08-01-1945','MM-DD-YYYY'),NULL);
INSERT INTO RecCenterMember("Id",F_name,L_name,Dob,Family_Id) VALUES (5,'Lisa','Tang',TO_DATE('11-05-2000','MM-DD-YYYY'),3);
INSERT INTO RecCenterMember("Id",F_name,L_name,Dob,Family_Id) VALUES (6,'Harry','Smith',TO_DATE('02-03-1972','MM-DD-YYYY'),NULL);
INSERT INTO RecCenterMember("Id",F_name,L_name,Dob,Family_Id) VALUES (7,'Justin','Smith',TO_DATE('02-02-1983','MM-DD-YYYY'),1);
INSERT INTO RecCenterMember("Id",F_name,L_name,Dob,Family_Id) VALUES (8,'Lisa','Brown',TO_DATE('12-28-1959','MM-DD-YYYY'),NULL);
INSERT INTO RecCenterMember("Id",F_name,L_name,Dob,Family_Id) VALUES (9,'Harry','Tang',TO_DATE('04-03-1948','MM-DD-YYYY'),3);
INSERT INTO RecCenterMember("Id",F_name,L_name,Dob,Family_Id) VALUES (10,'Dongmei','Tang',TO_DATE('03-02-1942','MM-DD-YYYY'),3);
INSERT INTO RecCenterMember("Id",F_name,L_name,Dob,Family_Id) VALUES (11,'Laura','Dickinson',TO_DATE('11-11-1998','MM-DD-YYYY'),NULL);
INSERT INTO RecCenterMember("Id",F_name,L_name,Dob,Family_Id) VALUES (12,'Victor','Garcia',TO_DATE('04-05-2006','MM-DD-YYYY'),5);
INSERT INTO RecCenterMember("Id",F_name,L_name,Dob,Family_Id) VALUES (13,'Emily','Citrin',TO_DATE('05-04-1993','MM-DD-YYYY'),NULL);
INSERT INTO RecCenterMember("Id",F_name,L_name,Dob,Family_Id) VALUES (14,'Maria','Garcia',TO_DATE('07-07-2007','MM-DD-YYYY'),5);
INSERT INTO RecCenterMember("Id",F_name,L_name,Dob,Family_Id) VALUES (15,'Cassie','O’Shea',TO_DATE('06-02-1988','MM-DD-YYYY'),2);
INSERT INTO RecCenterMember("Id",F_name,L_name,Dob,Family_Id) VALUES (16,'Cassandra','McDonald',TO_DATE('07-01-1990','MM-DD-YYYY'),NULL);
INSERT INTO RecCenterMember("Id",F_name,L_name,Dob,Family_Id) VALUES (17,'Jessie','Knapp',TO_DATE('09-12-1981','MM-DD-YYYY'),4);
INSERT INTO RecCenterMember("Id",F_name,L_name,Dob,Family_Id) VALUES (18,'Monica','Knapp',TO_DATE('09-17-1982','MM-DD-YYYY'),4);
INSERT INTO RecCenterMember("Id",F_name,L_name,Dob,Family_Id) VALUES (19,'Leslie','Blackburn',TO_DATE('01-19-1986','MM-DD-YYYY'),NULL);
INSERT INTO RecCenterMember("Id",F_name,L_name,Dob,Family_Id) VALUES (20,'Sandra','Svoboda',TO_DATE('09-09-1999','MM-DD-YYYY'),NULL);
COMMIT;

/*** Inserting Values In Instructor ***/

INSERT INTO Instructor("Id",F_name,L_name,Member_Id) VALUES (1,'Annie','Heard', NULL	);
INSERT INTO Instructor("Id",F_name,L_name,Member_Id) VALUES (2,'Monica','Knapp', 18	);
INSERT INTO Instructor("Id",F_name,L_name,Member_Id) VALUES (3,'James','Robertson', NULL	);
INSERT INTO Instructor("Id",F_name,L_name,Member_Id) VALUES (4,'April','O''Shea', 2);
INSERT INTO Instructor("Id",F_name,L_name,Member_Id) VALUES (5,'Harry','Tang', 9);
COMMIT;

/*** Inserting Values In Class ***/

INSERT INTO "Class" ("Id",Title,"Type",Instructor,Season,"Year") VALUES (1,'Needle points','Craft',2,'Spring',2010);
INSERT INTO "Class" ("Id",Title,"Type",Instructor,Season,"Year") VALUES (2,'Photography','Art',1,'Fall',2008);
INSERT INTO "Class" ("Id",Title,"Type",Instructor,Season,"Year") VALUES (3,'Woodworking','Craft',4,'Spring',2009);
INSERT INTO "Class" ("Id",Title,"Type",Instructor,Season,"Year") VALUES (4,'Chinese (Intro.)','Languages',1,'Winter',2008);
INSERT INTO "Class" ("Id",Title,"Type",Instructor,Season,"Year") VALUES (5,'Team games','Kids',1,'Summer',2008);
INSERT INTO "Class" ("Id",Title,"Type",Instructor,Season,"Year") VALUES (6,'Yoga (Intro.)','Exercise',2,'Fall',2009);
INSERT INTO "Class" ("Id",Title,"Type",Instructor,Season,"Year") VALUES (7,'Origami (Adv.)','Craft',4,'Fall',2009);
INSERT INTO "Class" ("Id",Title,"Type",Instructor,Season,"Year") VALUES (8,'Oil painting','Art',3,'Spring',2009);
INSERT INTO "Class" ("Id",Title,"Type",Instructor,Season,"Year") VALUES (9,'Yoga (Adv.)','Exercise',1,'Spring',2008);
INSERT INTO "Class" ("Id",Title,"Type",Instructor,Season,"Year") VALUES (10,'Chinese (Intro.)','Languages',3,'Spring',2009);
COMMIT;

/*** Inserting Values In Enrollment ***/

INSERT INTO Enrollment(Class_Id, Member_Id, "Cost") VALUES (3,3,20);
INSERT INTO Enrollment(Class_Id,Member_Id,"Cost") VALUES (1,9,15);
INSERT INTO Enrollment(Class_Id,Member_Id,"Cost") VALUES (2,9,20);
INSERT INTO Enrollment(Class_Id,Member_Id,"Cost") VALUES (4,10,30);
INSERT INTO Enrollment(Class_Id,Member_Id,"Cost") VALUES (3,10,10);
INSERT INTO Enrollment(Class_Id,Member_Id,"Cost") VALUES (5,5,10);
INSERT INTO Enrollment(Class_Id,Member_Id,"Cost") VALUES (4,9,30);
INSERT INTO Enrollment(Class_Id,Member_Id,"Cost") VALUES (1,11,25);
INSERT INTO Enrollment(Class_Id,Member_Id,"Cost") VALUES (2,19,40);
INSERT INTO Enrollment(Class_Id,Member_Id,"Cost") VALUES (7,14,10);
INSERT INTO Enrollment(Class_Id,Member_Id,"Cost") VALUES (8,12,5);
INSERT INTO Enrollment(Class_Id,Member_Id,"Cost") VALUES (1,1,30);
INSERT INTO Enrollment(Class_Id,Member_Id,"Cost") VALUES (6,1,15);
INSERT INTO Enrollment(Class_Id,Member_Id,"Cost") VALUES (9,1,20);
INSERT INTO Enrollment(Class_Id,Member_Id,"Cost") VALUES (8,1,25);
INSERT INTO Enrollment(Class_Id,Member_Id,"Cost") VALUES (1,13,18);
INSERT INTO Enrollment(Class_Id,Member_Id,"Cost") VALUES (2,20,9);
INSERT INTO Enrollment(Class_Id,Member_Id,"Cost") VALUES (10,4,15);
INSERT INTO Enrollment(Class_Id,Member_Id,"Cost") VALUES (1,2,3);
COMMIT;