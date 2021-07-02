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


/*##############################################################
* 5. KAKAO 발송
*############################################################*/

	if (cond.get("kakao_use_yn").equals("Y")) {
			logger.info("############################################ KAKAO_AGENT START !");
			m2.put("sms_msg", smsMsg);
			dao.insertAlarmKAKAO(m2);
		}
//#############################################################################
// Kakao Alarm Start
//#############################################################################	

	public void insertAlarmKAKAO(Map<String, Object> m) {
		SqlSession session = factory.openSession();
		try {
			session.insert("AlarmMapper.insertAlarmKAKAO", m);
			session.commit();
		} finally {
			session.close();
		}
	}


//<!--#########################################################-->
//<!--Alarm KAKAO Start-->
//<!--#########################################################-->	
    
	<insert id="insertAlarmKAKAO" parameterType="map">	
		INSERT INTO KKO_MSG
		(
			REQDATE,
			STATUS,
			PHONE,
			CALLBACK,
			PROFILE_KEY, 
			MSG,
	 		TEMPLATE_CODE,
	 		FAILED_TYPE,
	 		FAILED_SUBJECT,
	 		FAILED_MSG
	 		)
		SELECT DATE_FORMAT(NOW(), '%Y%m%d%H%i%s'),
			   '1',
			   REPLACE(mobile_no, '-', ''),
			   (SELECT REPLACE(mobile_no, '-', '') FROM COM_USER WHERE user_id = #{from_user_id}),
			   #{sms_msg},
			   'A001_01',
			   'SMS',
			   '제목',
			   '재전송 내용 SMS')
		FROM   ALARM_RECEIVER a,
			       COM_USER b,
			       (SELECT @rownum := 0) r
		WHERE  a.user_id = b.user_id
		AND    mobile_no IS NOT NULL
		AND    mobile_no != ''
	</insert>
  
  
