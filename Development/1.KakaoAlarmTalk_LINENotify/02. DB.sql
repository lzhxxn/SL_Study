#################################################################
-- 날짜 : 2021-06-30
-- 이름 : 이지훈
-- 모듈 : www
-- 주내용 : 카카오톡 라인 알림톡 관련
#################################################################
CREATE TABLE `ALARM_LINE` (
  `line_id` int(11) NOT NULL,
  `from_user_id` varchar(30) DEFAULT NULL,
  `from_token` varchar(100) DEFAULT NULL,
  `to_user_id` varchar(30) NOT NULL,
  `to_token` varchar(100) DEFAULT NULL,
  `sms_msg` varchar(100) NOT NULL,
  `stat_flag` char(1) NOT NULL DEFAULT '0',
  `stat_log` text DEFAULT NULL,
  `reg_time` varchar(14) NOT NULL,
  `update_time` varchar(14) DEFAULT NULL,
  PRIMARY KEY (`line_id`),
  KEY `IE_STAT_FLAG` (`stat_flag`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#ALARM_MNG
ALTER TABLE ALARM_MNG ADD kakao_use_yn CHAR(1) DEFAULT NULL;
ALTER TABLE ALARM_MNG ADD line_use_yn CHAR(1) DEFAULT NULL;
ALTER TABLE ALARM_MNG MODIFY COLUMN kakao_use_yn CHAR(1) AFTER snd_use_yn;
ALTER TABLE ALARM_MNG MODIFY COLUMN line_use_yn CHAR(1) AFTER kakao_use_yn;

#COM_USER
ALTER TABLE COM_USER ADD line_token VARCHAR(300) DEFAULT NULL;
ALTER TABLE COM_USER MODIFY COLUMN line_token VARCHAR(100) AFTER mail_addr;
  
