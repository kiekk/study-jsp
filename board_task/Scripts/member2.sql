
CREATE TABLE "MEMBER2"(
	ID VARCHAR2(50),
	PW VARCHAR2(50),
	NAME VARCHAR2(50),
	EMAIL VARCHAR2(100),
	CONSTRAINT MEMBER2_PK PRIMARY KEY(ID)
);

CREATE TABLE HOBBY(
	ID VARCHAR2(50),
	HOBBY VARCHAR2(100),
	CONSTRAINT HOBBY_FK FOREIGN KEY(ID) REFERENCES "MEMBER2"(ID)
);

SELECT * FROM "HOBBY";
DROP TABLE "HOBBY";
SELECT * FROM "MEMBER2";
DROP TABLE "MEMBER2";

