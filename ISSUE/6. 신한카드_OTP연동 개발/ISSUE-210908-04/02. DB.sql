#################################################################
-- ��¥ : 2021-10-25
-- �̸� : ������
-- ��� : www
-- �ֳ��� : OTP ��������
#################################################################
#COM_USER
ALTER TABLE COM_USER ADD user_otp VARCHAR(50) DEFAULT NULL;
ALTER TABLE COM_USER MODIFY COLUMN user_otp VARCHAR(50) AFTER user_id;