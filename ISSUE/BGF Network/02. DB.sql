#################################################################
-- 날짜 : 2021-07-22
-- 이름 : 이지훈
-- 모듈 : www
-- 주내용 : 카카오톡 알림톡 관련
#################################################################
#ALARM_MNG
ALTER TABLE ALARM_MNG ADD kakao_use_yn CHAR(1) DEFAULT NULL;
ALTER TABLE ALARM_MNG MODIFY COLUMN kakao_use_yn CHAR(1) AFTER snd_use_yn;
