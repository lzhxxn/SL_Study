#################################################################
-- ��¥ : 2021-07-22
-- �̸� : ������
-- ��� : www
-- �ֳ��� : īī���� �˸��� ����
#################################################################
#ALARM_MNG
ALTER TABLE ALARM_MNG ADD kakao_use_yn CHAR(1) DEFAULT NULL;
ALTER TABLE ALARM_MNG MODIFY COLUMN kakao_use_yn CHAR(1) AFTER snd_use_yn;
