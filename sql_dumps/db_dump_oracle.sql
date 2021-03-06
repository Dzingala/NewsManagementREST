--------------------------------------------------------
--  DDL for Sequence AUTHOR_SEQ
--------------------------------------------------------

   CREATE SEQUENCE  "AUTHOR_SEQ"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 201 CACHE 20 NOORDER  NOCYCLE ;
--------------------------------------------------------
--  DDL for Sequence COMMENTS_SEQ
--------------------------------------------------------

   CREATE SEQUENCE  "COMMENTS_SEQ"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 161 CACHE 20 NOORDER  NOCYCLE ;
--------------------------------------------------------
--  DDL for Sequence NEWS_SEQ
--------------------------------------------------------

   CREATE SEQUENCE  "NEWS_SEQ"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 381 CACHE 20 NOORDER  NOCYCLE ;
--------------------------------------------------------
--  DDL for Sequence ROLES_SEQ
--------------------------------------------------------

   CREATE SEQUENCE  "ROLES_SEQ"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 1 CACHE 20 NOORDER  NOCYCLE ;
--------------------------------------------------------
--  DDL for Sequence ROLES_SEQ1
--------------------------------------------------------

   CREATE SEQUENCE  "ROLES_SEQ1"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 141 CACHE 20 NOORDER  NOCYCLE ;
--------------------------------------------------------
--  DDL for Sequence TAG_SEQ
--------------------------------------------------------

   CREATE SEQUENCE  "TAG_SEQ"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 141 CACHE 20 NOORDER  NOCYCLE ;
--------------------------------------------------------
--  DDL for Sequence USERS_SEQ
--------------------------------------------------------

   CREATE SEQUENCE  "USERS_SEQ"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 1 CACHE 20 NOORDER  NOCYCLE ;
--------------------------------------------------------
--  DDL for Sequence USERS_SEQ1
--------------------------------------------------------

   CREATE SEQUENCE  "USERS_SEQ1"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 21 CACHE 20 NOORDER  NOCYCLE ;
--------------------------------------------------------
--  DDL for Sequence USERS_SEQ2
--------------------------------------------------------

   CREATE SEQUENCE  "USERS_SEQ2"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 301 CACHE 20 NOORDER  NOCYCLE ;
--------------------------------------------------------
--  DDL for Table AUTHOR
--------------------------------------------------------

  CREATE TABLE "AUTHOR" 
   (  "AUTHOR_ID" NUMBER(20,0), 
  "AUTHOR_NAME" NVARCHAR2(30), 
  "EXPIRED" TIMESTAMP (6)
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "SYSTEM" ;
--------------------------------------------------------
--  DDL for Table COMMENTS
--------------------------------------------------------

  CREATE TABLE "COMMENTS" 
   (  "COMMENT_ID" NUMBER(20,0), 
  "NEWS_ID" NUMBER(20,0), 
  "COMMENT_TEXT" NVARCHAR2(100), 
  "CREATION_DATE" TIMESTAMP (6)
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "SYSTEM" ;
--------------------------------------------------------
--  DDL for Table NEWS
--------------------------------------------------------

  CREATE TABLE "NEWS" 
   (  "NEWS_ID" NUMBER(20,0), 
  "TITLE" NVARCHAR2(30), 
  "SHORT_TEXT" NVARCHAR2(100), 
  "FULL_TEXT" NVARCHAR2(2000), 
  "CREATION_DATE" TIMESTAMP (6), 
  "MODIFICATION_DATE" DATE
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "SYSTEM" ;
--------------------------------------------------------
--  DDL for Table NEWS_AUTHOR
--------------------------------------------------------

  CREATE TABLE "NEWS_AUTHOR" 
   (  "NEWS_ID" NUMBER(20,0), 
  "AUTHOR_ID" NUMBER(20,0)
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "SYSTEM" ;
--------------------------------------------------------
--  DDL for Table NEWS_TAG
--------------------------------------------------------

  CREATE TABLE "NEWS_TAG" 
   (  "NEWS_ID" NUMBER(20,0), 
  "TAG_ID" NUMBER(20,0)
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "SYSTEM" ;
--------------------------------------------------------
--  DDL for Table ROLES
--------------------------------------------------------

  CREATE TABLE "ROLES" 
   (  "ROLE_NAME" VARCHAR2(50 BYTE), 
  "ROLE_ID" NUMBER(20,0)
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "SYSTEM" ;
--------------------------------------------------------
--  DDL for Table TAG
--------------------------------------------------------

  CREATE TABLE "TAG" 
   (  "TAG_ID" NUMBER(20,0), 
  "TAG_NAME" NVARCHAR2(30)
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "SYSTEM" ;
--------------------------------------------------------
--  DDL for Table USERS
--------------------------------------------------------

  CREATE TABLE "USERS" 
   (  "USER_ID" NUMBER(20,0), 
  "USER_NAME" NVARCHAR2(50), 
  "LOGIN" VARCHAR2(30 BYTE), 
  "PASSWORD" VARCHAR2(32 BYTE), 
  "ROLE_ID" NUMBER(20,0)
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "SYSTEM" ;

   COMMENT ON COLUMN "USERS"."PASSWORD" IS '32 symbols for allowing MD5 Hashing';
REM INSERTING into DZINHALA.AUTHOR
SET DEFINE OFF;
REM INSERTING into DZINHALA.COMMENTS
SET DEFINE OFF;
REM INSERTING into DZINHALA.NEWS
SET DEFINE OFF;
REM INSERTING into DZINHALA.NEWS_AUTHOR
SET DEFINE OFF;
REM INSERTING into DZINHALA.NEWS_TAG
SET DEFINE OFF;
REM INSERTING into DZINHALA.ROLES
SET DEFINE OFF;
REM INSERTING into DZINHALA.TAG
SET DEFINE OFF;
REM INSERTING into DZINHALA.USERS
SET DEFINE OFF;
--------------------------------------------------------
--  DDL for Index NEWS_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "NEWS_PK" ON "NEWS" ("NEWS_ID") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "SYSTEM" ;
--------------------------------------------------------
--  DDL for Index COMMENTS_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "COMMENTS_PK" ON "COMMENTS" ("COMMENT_ID") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "SYSTEM" ;
--------------------------------------------------------
--  DDL for Index TAG_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "TAG_PK" ON "TAG" ("TAG_ID") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "SYSTEM" ;
--------------------------------------------------------
--  DDL for Index ROLES_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "ROLES_PK" ON "ROLES" ("ROLE_ID") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "SYSTEM" ;
--------------------------------------------------------
--  DDL for Index AUTHOR_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "AUTHOR_PK" ON "AUTHOR" ("AUTHOR_ID") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "SYSTEM" ;
--------------------------------------------------------
--  DDL for Index USERS_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "USERS_PK" ON "USERS" ("USER_ID") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "SYSTEM" ;
--------------------------------------------------------
--  Constraints for Table NEWS
--------------------------------------------------------

  ALTER TABLE "NEWS" MODIFY ("NEWS_ID" NOT NULL ENABLE);
  ALTER TABLE "NEWS" MODIFY ("TITLE" NOT NULL ENABLE);
  ALTER TABLE "NEWS" MODIFY ("SHORT_TEXT" NOT NULL ENABLE);
  ALTER TABLE "NEWS" MODIFY ("FULL_TEXT" NOT NULL ENABLE);
  ALTER TABLE "NEWS" MODIFY ("CREATION_DATE" NOT NULL ENABLE);
  ALTER TABLE "NEWS" MODIFY ("MODIFICATION_DATE" NOT NULL ENABLE);
  ALTER TABLE "NEWS" ADD CONSTRAINT "NEWS_PK" PRIMARY KEY ("NEWS_ID")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "SYSTEM"  ENABLE;
--------------------------------------------------------
--  Constraints for Table USERS
--------------------------------------------------------

  ALTER TABLE "USERS" MODIFY ("USER_ID" NOT NULL ENABLE);
  ALTER TABLE "USERS" MODIFY ("USER_NAME" NOT NULL ENABLE);
  ALTER TABLE "USERS" MODIFY ("LOGIN" NOT NULL ENABLE);
  ALTER TABLE "USERS" MODIFY ("PASSWORD" NOT NULL ENABLE);
  ALTER TABLE "USERS" ADD CONSTRAINT "USERS_PK" PRIMARY KEY ("USER_ID")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "SYSTEM"  ENABLE;
--------------------------------------------------------
--  Constraints for Table AUTHOR
--------------------------------------------------------

  ALTER TABLE "AUTHOR" MODIFY ("AUTHOR_ID" NOT NULL ENABLE);
  ALTER TABLE "AUTHOR" MODIFY ("AUTHOR_NAME" NOT NULL ENABLE);
  ALTER TABLE "AUTHOR" ADD CONSTRAINT "AUTHOR_PK" PRIMARY KEY ("AUTHOR_ID")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "SYSTEM"  ENABLE;
--------------------------------------------------------
--  Constraints for Table NEWS_AUTHOR
--------------------------------------------------------

  ALTER TABLE "NEWS_AUTHOR" MODIFY ("NEWS_ID" NOT NULL ENABLE);
  ALTER TABLE "NEWS_AUTHOR" MODIFY ("AUTHOR_ID" NOT NULL ENABLE);
--------------------------------------------------------
--  Constraints for Table TAG
--------------------------------------------------------

  ALTER TABLE "TAG" MODIFY ("TAG_ID" NOT NULL ENABLE);
  ALTER TABLE "TAG" MODIFY ("TAG_NAME" NOT NULL ENABLE);
  ALTER TABLE "TAG" ADD CONSTRAINT "TAG_PK" PRIMARY KEY ("TAG_ID")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "SYSTEM"  ENABLE;
--------------------------------------------------------
--  Constraints for Table ROLES
--------------------------------------------------------

  ALTER TABLE "ROLES" MODIFY ("ROLE_NAME" NOT NULL ENABLE);
  ALTER TABLE "ROLES" MODIFY ("ROLE_ID" NOT NULL ENABLE);
  ALTER TABLE "ROLES" ADD CONSTRAINT "ROLES_PK" PRIMARY KEY ("ROLE_ID")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "SYSTEM"  ENABLE;
--------------------------------------------------------
--  Constraints for Table COMMENTS
--------------------------------------------------------

  ALTER TABLE "COMMENTS" MODIFY ("COMMENT_ID" NOT NULL ENABLE);
  ALTER TABLE "COMMENTS" MODIFY ("NEWS_ID" NOT NULL ENABLE);
  ALTER TABLE "COMMENTS" MODIFY ("COMMENT_TEXT" NOT NULL ENABLE);
  ALTER TABLE "COMMENTS" MODIFY ("CREATION_DATE" NOT NULL ENABLE);
  ALTER TABLE "COMMENTS" ADD CONSTRAINT "COMMENTS_PK" PRIMARY KEY ("COMMENT_ID")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "SYSTEM"  ENABLE;
--------------------------------------------------------
--  Constraints for Table NEWS_TAG
--------------------------------------------------------

  ALTER TABLE "NEWS_TAG" MODIFY ("NEWS_ID" NOT NULL ENABLE);
  ALTER TABLE "NEWS_TAG" MODIFY ("TAG_ID" NOT NULL ENABLE);
--------------------------------------------------------
--  Ref Constraints for Table COMMENTS
--------------------------------------------------------

  ALTER TABLE "COMMENTS" ADD CONSTRAINT "COMMENTS_FK1" FOREIGN KEY ("NEWS_ID")
    REFERENCES "NEWS" ("NEWS_ID") ENABLE;
--------------------------------------------------------
--  Ref Constraints for Table NEWS_AUTHOR
--------------------------------------------------------

  ALTER TABLE "NEWS_AUTHOR" ADD CONSTRAINT "NEWS_AUTHOR_FK1" FOREIGN KEY ("NEWS_ID")
    REFERENCES "NEWS" ("NEWS_ID") ENABLE;
  ALTER TABLE "NEWS_AUTHOR" ADD CONSTRAINT "NEWS_AUTHOR_FK2" FOREIGN KEY ("AUTHOR_ID")
    REFERENCES "AUTHOR" ("AUTHOR_ID") ENABLE;
--------------------------------------------------------
--  Ref Constraints for Table NEWS_TAG
--------------------------------------------------------

  ALTER TABLE "NEWS_TAG" ADD CONSTRAINT "NEWS_TAG_FK1" FOREIGN KEY ("TAG_ID")
    REFERENCES "TAG" ("TAG_ID") ENABLE;
  ALTER TABLE "NEWS_TAG" ADD CONSTRAINT "NEWS_TAG_FK2" FOREIGN KEY ("NEWS_ID")
    REFERENCES "NEWS" ("NEWS_ID") ENABLE;
--------------------------------------------------------
--  Ref Constraints for Table USERS
--------------------------------------------------------

  ALTER TABLE "USERS" ADD CONSTRAINT "USERS_FK1" FOREIGN KEY ("ROLE_ID")
    REFERENCES "ROLES" ("ROLE_ID") ENABLE;
--------------------------------------------------------
--  DDL for Trigger AUTHOR_TRG
--------------------------------------------------------

  CREATE OR REPLACE TRIGGER "AUTHOR_TRG" 
BEFORE INSERT ON DZINHALA.AUTHOR 
FOR EACH ROW 
BEGIN
  <<COLUMN_SEQUENCES>>
  BEGIN
    IF INSERTING AND :NEW.AUTHOR_ID IS NULL THEN
      SELECT AUTHOR_SEQ.NEXTVAL INTO :NEW.AUTHOR_ID FROM SYS.DUAL;
    END IF;
  END COLUMN_SEQUENCES;
END;
/
ALTER TRIGGER "AUTHOR_TRG" ENABLE;
--------------------------------------------------------
--  DDL for Trigger COMMENTS_TRG
--------------------------------------------------------

  CREATE OR REPLACE TRIGGER "COMMENTS_TRG" 
BEFORE INSERT ON DZINHALA.COMMENTS 
FOR EACH ROW 
BEGIN
  <<COLUMN_SEQUENCES>>
  BEGIN
    IF INSERTING AND :NEW.COMMENT_ID IS NULL THEN
      SELECT COMMENTS_SEQ.NEXTVAL INTO :NEW.COMMENT_ID FROM SYS.DUAL;
    END IF;
  END COLUMN_SEQUENCES;
END;
/
ALTER TRIGGER "COMMENTS_TRG" ENABLE;
--------------------------------------------------------
--  DDL for Trigger NEWS_TRG
--------------------------------------------------------

  CREATE OR REPLACE TRIGGER "NEWS_TRG" 
BEFORE INSERT ON DZINHALA.NEWS 
FOR EACH ROW 
BEGIN
  <<COLUMN_SEQUENCES>>
  BEGIN
    IF INSERTING AND :NEW.NEWS_ID IS NULL THEN
      SELECT NEWS_SEQ.NEXTVAL INTO :NEW.NEWS_ID FROM SYS.DUAL;
    END IF;
  END COLUMN_SEQUENCES;
END;
/
ALTER TRIGGER "NEWS_TRG" ENABLE;
--------------------------------------------------------
--  DDL for Trigger ROLES_TRG
--------------------------------------------------------

  CREATE OR REPLACE TRIGGER "ROLES_TRG" 
BEFORE INSERT ON ROLES 
FOR EACH ROW 
BEGIN
  <<COLUMN_SEQUENCES>>
  BEGIN
    IF INSERTING AND :NEW.ROLE_ID IS NULL THEN
      SELECT ROLES_SEQ1.NEXTVAL INTO :NEW.ROLE_ID FROM SYS.DUAL;
    END IF;
  END COLUMN_SEQUENCES;
END;
/
ALTER TRIGGER "ROLES_TRG" ENABLE;
--------------------------------------------------------
--  DDL for Trigger TAG_TRG
--------------------------------------------------------

  CREATE OR REPLACE TRIGGER "TAG_TRG" 
BEFORE INSERT ON DZINHALA.TAG 
FOR EACH ROW 
BEGIN
  <<COLUMN_SEQUENCES>>
  BEGIN
    IF INSERTING AND :NEW.TAG_ID IS NULL THEN
      SELECT TAG_SEQ.NEXTVAL INTO :NEW.TAG_ID FROM SYS.DUAL;
    END IF;
  END COLUMN_SEQUENCES;
END;
/
ALTER TRIGGER "TAG_TRG" ENABLE;
--------------------------------------------------------
--  DDL for Trigger USERS_TRG
--------------------------------------------------------

  CREATE OR REPLACE TRIGGER "USERS_TRG" 
BEFORE INSERT ON DZINHALA.USERS 
FOR EACH ROW 
BEGIN
  <<COLUMN_SEQUENCES>>
  BEGIN
    NULL;
  END COLUMN_SEQUENCES;
END;
/
ALTER TRIGGER "USERS_TRG" ENABLE;
--------------------------------------------------------
--  DDL for Trigger USERS_TRG1
--------------------------------------------------------

  CREATE OR REPLACE TRIGGER "USERS_TRG1" 
BEFORE INSERT ON USERS 
FOR EACH ROW 
BEGIN
  <<COLUMN_SEQUENCES>>
  BEGIN
    IF INSERTING AND :NEW.USER_ID IS NULL THEN
      SELECT USERS_SEQ2.NEXTVAL INTO :NEW.USER_ID FROM SYS.DUAL;
    END IF;
  END COLUMN_SEQUENCES;
END;
/
ALTER TRIGGER "USERS_TRG1" ENABLE;
