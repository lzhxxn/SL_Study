/*##############################################################
* 5. KAKAO - AlarmHandler.java
*############################################################*/
	if (cond.get("kakao_use_yn").equals("Y")) {
		logger.info("############################################ KAKAO_AGENT START !");
		m2.put("sms_msg", smsMsg);
		m2.put("event_time", DateTime.getFormatString("yyyy-MM-dd HH:mm:ss"));
		dao.insertAlarmKAKAO(m2);
	}
//#############################################################################
// AlarmDAO.java
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
//<!--AlarmMapper.xml-->
//<!--#########################################################-->	
    <insert id="insertAlarmKAKAO" parameterType="map">	
		INSERT INTO KKO_MSG
		(
			REQDATE,
			SENTDATE,
			STATUS,
			PHONE,
			CALLBACK,
			PROFILE_KEY, 
			MSG,
	 		TEMPLATE_CODE,
	 		EVENT_TIME
	 		)
		SELECT DATE_FORMAT(NOW(), '%Y%m%d%H%i%s'),
			   DATE_FORMAT(NOW(), '%Y%m%d%H%i%s'),
			   '1',
			   REPLACE(mobile_no, '-', ''),
			   '01075358543',
			   'seculayer',
			   #{sms_msg},
			   #{template_code},
			   #{event_time}
		FROM   ALARM_RECEIVER a,
			       COM_USER b,
			       (SELECT @rownum := 0) r
		WHERE  a.user_id = b.user_id
		AND    mobile_no IS NOT NULL
		AND    mobile_no != ''
	</insert>
  
  
