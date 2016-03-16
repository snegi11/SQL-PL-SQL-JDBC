/* SRISHTI NEGI , A20351640 */

/*2.What is the phone number of the O’Shea’s family?*/

SELECT DISTINCT PHONE
FROM FAMILYPACKAGE JOIN 
RECCENTERMEMBER ON RECCENTERMEMBER.Family_Id = FAMILYPACKAGE."Id"
WHERE L_Name= 'O’Shea';

/*3.Give the name of the members who have enrolled in the most expensive class since data have been kept.*/

SELECT F_name, L_NAME
FROM RECCENTERMEMBER 
JOIN ENROLLMENT ON RECCENTERMEMBER."Id"=ENROLLMENT.Member_Id
WHERE ENROLLMENT."Cost"=(SELECT MAX("Cost") FROM ENROLLMENT);

/*4.Give the names of the instructors who is not a member of the RecCenter.*/

SELECT F_NAME,L_NAME FROM INSTRUCTOR WHERE MEMBER_ID IS NULL 
OR MEMBER_ID NOT IN (SELECT "Id" FROM RECCENTERMEMBER);

/*5.Display the names of members that have enrolled in at least 3 different types of classes*/

SELECT F_name, L_name
FROM RECCENTERMEMBER
JOIN ENROLLMENT ON RECCENTERMEMBER."Id"=ENROLLMENT.MEMBER_ID
JOIN "Class" ON ENROLLMENT.Class_Id="Class"."Id" 
GROUP BY F_name,L_name
HAVING COUNT(*)>=3;

/*6.Show all people ever enrolled in a craft class.*/

SELECT F_name, L_name
FROM RECCENTERMEMBER JOIN ENROLLMENT ON RECCENTERMEMBER."Id"=ENROLLMENT.MEMBER_ID
JOIN "Class" ON ENROLLMENT.Class_Id="Class"."Id" 
WHERE "Type"='Craft';

/*7.Give the names of the members who were born before 1980 but after 1950.*/

SELECT F_name, L_name FROM RecCenterMember 
WHERE EXTRACT(YEAR FROM DOB)<1980 AND EXTRACT(YEAR FROM DOB)>1950;

/*8.	Give the distinct types of classes that were offered in 2008 and 2009 along with their descriptions.*/

SELECT "Type", Description FROM "Type" WHERE 
"Type" IN(SELECT DISTINCT "Type" FROM "Class" WHERE "Year"=2008 OR "Year"=2009);
