CREATE TABLE NSC_MEAP_SEQUENCE (
	"ID" VARCHAR(128) NOT NULL PRIMARY KEY,--����
	"CURRENT" BIGINT DEFAULT 0 NOT NULL,--��ǰֵ
	"STEP" INTEGER DEFAULT 1 NOT NULL--ÿ��ֵ
);

CREATE TABLE "NSC_MEAP_SCHEDULE" (
  "ID"	VARCHAR(255)	NOT NULL PRIMARY KEY,
  "DAYOFMONTH"	INTEGER	NOT NULL,
  "HOUR"	INTEGER	NOT NULL,
  "MINUTE"	INTEGER	NOT NULL,
  "MONTH"	INTEGER	NOT NULL,
  "SECOND"	INTEGER	NOT NULL,
  "TASK"	VARCHAR(255),
  "WEEKDAY"	INTEGER	NOT NULL
);

CREATE TABLE "NSC_MEAP_APP_TOKEN" (
  "ID"	VARCHAR(36)	NOT NULL PRIMARY KEY,
  "CREATEDTIME"	BIGINT	NOT NULL,
  "DEVICE"	VARCHAR(255) NOT NULL,
  "DEVICEID"	VARCHAR(255) NOT NULL,
  "PUBLICKEY"	VARCHAR(255),
  "PUSHTOKEN"	VARCHAR(255),
  "SALT"	VARCHAR(255),
  "SALTKEY"	VARCHAR(255),
  "USERTOKEN"	VARCHAR(255) NOT NULL,
  "VERIFY"	SMALLINT	NOT NULL
);
CREATE INDEX IDX_NMAT_VERIFY ON "NSC_MEAP_APP_TOKEN"("VERIFY");
CREATE INDEX IDX_NMAT_USERTOKEN ON "NSC_MEAP_APP_TOKEN"("USERTOKEN");
CREATE UNIQUE INDEX IDX_NMAT_DDP ON "NSC_MEAP_APP_TOKEN"("DEVICE","DEVICEID");

CREATE TABLE "NSC_MEAP_USER_TOKEN" (
  "ID"	VARCHAR(255)	NOT NULL PRIMARY KEY,
  "USERID"	VARCHAR(255) NOT NULL UNIQUE
);


CREATE TABLE "NSC_MEAP_FILE_TOKEN" (
  "ID"	VARCHAR(36)	NOT NULL PRIMARY KEY,
  "APPTOKEN"	VARCHAR(255) NOT NULL,
  "USERTOKEN"	VARCHAR(255) NOT NULL,
  "ALGORITHM"	VARCHAR(255),
  "CREATEDTIME"	BIGINT	NOT NULL,
  "DIGEST"	VARCHAR(255),
  "FILENAME"	VARCHAR(255),
  "FILEPOS"	BIGINT	NOT NULL,
  "FILESIZE"	BIGINT	NOT NULL,
  "FILESTATUS"	VARCHAR(255),
  "FILETYPE"	VARCHAR(255)
);
CREATE INDEX IDX_NMFT_USERTOKEN ON "NSC_MEAP_FILE_TOKEN"("USERTOKEN");
CREATE INDEX IDX_NMFT_APPTOKEN ON "NSC_MEAP_FILE_TOKEN"("APPTOKEN");

CREATE TABLE "NSC_MEAP_QUEUE_PUSH" (
  "ID"	VARCHAR(32)	NOT NULL PRIMARY KEY,
  "CALLBACK"	SMALLINT	NOT NULL,
  "CREATEDTIME"	BIGINT	NOT NULL,
  "DEVICE"	VARCHAR(255) NOT NULL,
  "MESSAGE"	VARCHAR(255),
  "MESSAGEID"	VARCHAR(255),
  "PRIORITY"	INTEGER	NOT NULL,
  "PUSHTOKEN"	VARCHAR(255),
  "TITLE"	VARCHAR(255),
  "TYPE"	VARCHAR(255)
);
CREATE INDEX IDX_NMQP_PRIORITY ON "NSC_MEAP_QUEUE_PUSH"("PRIORITY");
CREATE INDEX IDX_NMQP_DEVICE ON "NSC_MEAP_QUEUE_PUSH"("DEVICE");
CREATE INDEX IDX_NMQP_CREATEDTIME ON "NSC_MEAP_QUEUE_PUSH"("CREATEDTIME");


CREATE TABLE "NSC_MEAP_QUEUE_OUTTER" (
  "ID"	VARCHAR(32)	NOT NULL PRIMARY KEY,
  "COMMAND"	VARCHAR(255),
  "CREATEDTIME"	BIGINT	NOT NULL,
  "EXPIREDTIME"	BIGINT	NOT NULL,
  "HASPROMPTTIMES"	INTEGER	NOT NULL,
  "NEEDPROMPT"	SMALLINT	NOT NULL,
  "NEXTPROMPTTIME"	BIGINT	NOT NULL,
  "PROMPTMINUTEPERIOD"	INTEGER	NOT NULL,
  "PROMPTTIMES"	INTEGER	NOT NULL,
  "PUSHPROMPTTEXT"	VARCHAR(255),
  "SUBJECT"	VARCHAR(255),
  "TOKEN"	VARCHAR(255) NOT NULL,
  "TOKENTYPE"	VARCHAR(255) NOT NULL
);
CREATE INDEX IDX_NMQO_CREATEDTIME ON NSC_MEAP_QUEUE_OUTTER("CREATEDTIME");
CREATE INDEX IDX_NMQO_EXPIREDTIME ON NSC_MEAP_QUEUE_OUTTER("EXPIREDTIME");
CREATE INDEX IDX_NMQO_NEEDPROMPT ON NSC_MEAP_QUEUE_OUTTER("NEEDPROMPT");
CREATE INDEX IDX_NMQO_NEXTPROMPTTIME ON NSC_MEAP_QUEUE_OUTTER("NEXTPROMPTTIME");
CREATE INDEX IDX_NMQO_TT_T ON NSC_MEAP_QUEUE_OUTTER("TOKENTYPE","TOKEN");


CREATE TABLE "TEST_USER" (
  "ID"	VARCHAR(32)	NOT NULL PRIMARY KEY,
  "USERNAME"	VARCHAR(255) NOT NULL UNIQUE,
  "PASSWORD"	VARCHAR(255) NOT NULL
);


CREATE TABLE "TEST_USER_LOGIN_RECORD" (
  "ID"	VARCHAR(32)	NOT NULL PRIMARY KEY,
  "CREATEDTIME"	BIGINT	NOT NULL,
  "LASTIP"	VARCHAR(255),
  "USERID"	VARCHAR(255)
);

--#�����sql
--ALTER TABLE "MEAP"."NSC_MEAP_USER_TOKEN"
--  ADD FOREIGN KEY
--    (USERID)
--  REFERENCES MEAP.TEST_USER
--    (ID)
--    ON DELETE NO ACTION
--    ON UPDATE NO ACTION
--    ENFORCED
--    ENABLE QUERY OPTIMIZATION;
--COMMIT;
--
--#��������index��sql
--CREATE UNIQUE INDEX "MEAP".useridindex
--  ON "MEAP"."NSC_MEAP_USER_TOKEN"
--    ( USERID ASC )
--  DISALLOW REVERSE SCANS;
--COMMIT;
--
--
--#��Ψһsql
--ALTER TABLE "MEAP"."TEST_USER_LOGIN_RECORD"
--  ADD CONSTRAINT weiyi UNIQUE
--    (CREATEDTIME);
--COMMIT;
--
--#��Ĭ��ֵsql
--ALTER TABLE "MEAP"."TEST_USER"
--  ALTER COLUMN "USERNAME" SET	DEFAULT 1;
--REORG TABLE "MEAP"."TEST_USER";
--COMMIT;