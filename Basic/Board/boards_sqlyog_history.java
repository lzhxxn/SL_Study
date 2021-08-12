INSERT INTO BOARDS (boards_id, boards_subject, boards_writer, boards_email, ) VALUES('414','default');

SHOW COLUMNS FROM BOARDS;
SELECT * FROM BOARDS;
SELECT * FROM BOARDS WHERE boards_id=1;
ALTER TABLE BOARDS ADD COLUMN boards_content VARCHAR(100) NULL DEFAULT NULL;
ALTER TABLE BOARDS ADD COLUMN proc_id VARCHAR (30) DEFAULT NULL;
ALTER TABLE BOARDS ADD COLUMN proc_ip VARCHAR (39) DEFAULT NULL;
ALTER TABLE BOARDS ADD COLUMN proc_dt VARCHAR (14) DEFAULT NULL;
ALTER TABLE BOARDS ADD COLUMN del_yn CHAR (1) DEFAULT 'N';
ALTER TABLE BOARDS MODIFY COLUMN boards_content VARCHAR(100) NULL DEFAULT NULL AFTER boards_subject; -- 컬럼순서변경
ALTER TABLE BOARDS MODIFY boards_id INT(10) NOT NULL AUTO_INCREMENT; 

SELECT boards_subject, boards_content, boards_writer, boards_email, boards_rdate FROM BOARDS WHERE boards_subject='sim';

INSERT INTO BOARDS (boards_subject, boards_content, boards_writer, boards_email, boards_rdate) VALUES ('seculayer', '조호현 차장님', 'Daniel', 'jihoon.lee@seculayer.com', NOW());
INSERT INTO BOARDS (boards_subject, boards_content, boards_writer, boards_email, boards_rdate) VALUES ('seculayer2', '유상진 과장님', 'Daniel', 'jihoon.lee@seculayer.com', NOW());
INSERT INTO BOARDS (boards_subject, boards_content, boards_writer, boards_email, boards_rdate) VALUES ('seculayer3', 'jihoon.lee', 'Daniel', 'jihoon.lee@seculayer.com', NOW());
INSERT INTO BOARDS (boards_subject, boards_content, boards_writer, boards_email, boards_rdate) VALUES ('sim', 'jihoon.lee', 'Daniel', 'jihoon.lee@seculayer.com', NOW());
INSERT INTO BOARDS (boards_subject, boards_content, boards_writer, boards_email, boards_rdate) VALUES ('sim', '안준영 사원님', '안준영님', 'jihoon.lee@seculayer.com', NOW());
UPDATE BOARDS SET boards_subject = 'seculayer1' WHERE boards_id = 1;
DELETE FROM BOARDS WHERE boards_id = 36;
COMMIT;

ALTER TABLE BOARDS DROP COLUMN del_yn;
ALTER TABLE BOARDS CHANGE user_id boards_writer VARCHAR(20) DEFAULT NULL;
SELECT * FROM BOARDS;
