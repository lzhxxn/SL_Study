#################################################################
-- 날짜 : 2021-09-27
-- 이름 : 이지훈
-- 모듈 : www
-- 주내용 : 사용자관리_계정신청
#################################################################
#COM_USER
ALTER TABLE COM_USER ADD lock_yn CHAR(1) DEFAULT 'N';
ALTER TABLE COM_USER ADD apply_status VARCHAR(1) DEFAULT '2';
ALTER TABLE COM_USER MODIFY apply_status VARCHAR(1) DEFAULT NULL
COMMENT '1 : 계정신청상태, 2 : 승인, 3 : 거절';

#COM_CODE
INSERT INTO `COM_CODE` (`code_type`, `code_id`, `code_name`, `flag1`, `flag2`, `code_cont`, `user_view`, `required`, `order_no`) VALUES('*','CS0083','계정신청상태','','','계정신청상태','Y','Y','0');
INSERT INTO `COM_CODE` (`code_type`, `code_id`, `code_name`, `flag1`, `flag2`, `code_cont`, `user_view`, `required`, `order_no`) VALUES('CS0083','1','신청','','','','N','Y','0');
INSERT INTO `COM_CODE` (`code_type`, `code_id`, `code_name`, `flag1`, `flag2`, `code_cont`, `user_view`, `required`, `order_no`) VALUES('CS0083','2','승인','','','','N','Y','1');
INSERT INTO `COM_CODE` (`code_type`, `code_id`, `code_name`, `flag1`, `flag2`, `code_cont`, `user_view`, `required`, `order_no`) VALUES('CS0083','3','거절','','','','N','Y','2');

