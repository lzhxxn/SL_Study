#################################################################
-- ��¥ : 2021-09-27
-- �̸� : ������
-- ��� : www
-- �ֳ��� : ����ڰ���_������û
#################################################################
#COM_USER
ALTER TABLE COM_USER ADD lock_yn CHAR(1) DEFAULT 'N';
ALTER TABLE COM_USER ADD apply_status VARCHAR(1) DEFAULT '2';
ALTER TABLE COM_USER MODIFY apply_status VARCHAR(1) DEFAULT NULL
COMMENT '1 : ������û����, 2 : ����, 3 : ����';

#COM_CODE
INSERT INTO `COM_CODE` (`code_type`, `code_id`, `code_name`, `flag1`, `flag2`, `code_cont`, `user_view`, `required`, `order_no`) VALUES('*','CS0083','������û����','','','������û����','Y','Y','0');
INSERT INTO `COM_CODE` (`code_type`, `code_id`, `code_name`, `flag1`, `flag2`, `code_cont`, `user_view`, `required`, `order_no`) VALUES('CS0083','1','��û','','','','N','Y','0');
INSERT INTO `COM_CODE` (`code_type`, `code_id`, `code_name`, `flag1`, `flag2`, `code_cont`, `user_view`, `required`, `order_no`) VALUES('CS0083','2','����','','','','N','Y','1');
INSERT INTO `COM_CODE` (`code_type`, `code_id`, `code_name`, `flag1`, `flag2`, `code_cont`, `user_view`, `required`, `order_no`) VALUES('CS0083','3','����','','','','N','Y','2');

