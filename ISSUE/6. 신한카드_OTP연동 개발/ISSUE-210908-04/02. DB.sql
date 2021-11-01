#################################################################
-- 날짜 : 2021-10-25
-- 이름 : 이지훈
-- 모듈 : www
-- 주내용 : OTP 연동개발
#################################################################
#COM_USER
ALTER TABLE COM_USER ADD user_otp VARCHAR(50) DEFAULT NULL;
ALTER TABLE COM_USER MODIFY COLUMN user_otp VARCHAR(50) AFTER user_id;