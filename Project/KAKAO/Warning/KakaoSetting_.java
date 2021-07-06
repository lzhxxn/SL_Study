/*
'cjagent.conf'
####################################################################
#
# CJAgent Config File
#
####################################################################
#
# Host Info
#
kko_ip=xx
kko_port=xx
kko_id=xx
kko_pwd=xx

sms_ip=게이트웨이아이피
sms_port=xx
sms_id=페일백아이디
sms_pwd=페일백패스워드

###################################################################
# 알림톡인 경우 AT (Default)
# 친구톡인 경우 FT
#
Kkotype=AT

####################################################################
# 데이터베이스 유형(db_name)
# ORACLE, MSSQL, MYSQL, POSTGRESQL
#
# database driver
#

### MYSQL -> MARIADB ###
db_name=MARIADB
db_driver=org.mariadb.jdbc.Driver
db_url=jdbc:mariadb://1.1.1.1
db_sql=sql_mysql.sql
..
*/


/*
`cjstart.sh`
JAVA_HOME=/xxx/app/java/zulu11

CJAGENT_HOME=/xxx/app/messenger

CJAGENT_NAMES=cjmplaceagent.jar

if [ $# == 0 ]
	then echo "Usage: cjstart.sh [start | stop | list | version]"; exit;
fi

# csh일 경우 setenv LANG ko_KR.eucKR, AIX의 경우 ko_KR.IBM-eucKR
export LANG=ko_KR.eucKR
..
...
*/



