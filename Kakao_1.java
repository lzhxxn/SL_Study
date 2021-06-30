/*##############################################################
* 5. KAKAO 발송
*############################################################*/

	if(cond.get("kakao_use_yn").equals("Y")) {
	logger.info("############################################ KAKAO_AGENT START !");
	m2.put("sms_msg", smsMsg);

	dao.insertAlarmKAKAO(m2);
	} else {
	logger.info("############################################ KAKAO_AGENT ERROR !");
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
  
  
