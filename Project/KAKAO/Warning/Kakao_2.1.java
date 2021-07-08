/*
* ##############################################################
*  5. KAKAO 발송
* ############################################################
*/
			if (cond.get("kakao_use_yn").equals("Y")) {
				logger.info("############################################ KAKAO_AGENT START !");
				
				m2.put("sms_msg", smsMsg);
				m2.put("event_time", DateTime.getFormatString("yyyy-MM-dd HH:mm:ss"));
				
				dao.insertAlarmKAKAO(m2);
			}

    //3. Apply KAKAO
      if (cond.get("kakao_use_yn").equals("Y")) {
        StringBuffer buffer = new StringBuffer();
        buffer.append(rule.get("event_nm"));
        buffer.append("["+event.get("field_val")+"]");

        alarm.put("sms_msg", buffer.toString());

        alarmDAO.insertAlarmEventKAKAO(alarm);
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

	public void insertAlarmEventKAKAO(Map<String, Object> m) {
		SqlSession session = factory.openSession();
		try {
			session.insert("AlarmMapper.insertAlarmEventKAKAO", m);
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
			'07046037320',
			'6fafadad09e06fcc7af9f1614502f39cf20fd3bc',
			CONCAT('eyeCloudSIM Alert:' ,'SL', ' ', #{sms_msg}),
			'A001_01',
			'No',
			'No',
			'No'
		FROM   ALARM_RECEIVER a,
			   COM_USER b,
			   (SELECT @rownum := 0) r
		WHERE  a.user_id = b.user_id
		AND    mobile_no IS NOT NULL
		AND    mobile_no != '';
	</insert>
  
    <insert id="insertAlarmEventKAKAO" parameterType="map">	
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
			'07046037320',
			'6fafadad09e06fcc7af9f1614502f39cf20fd3bc',
			CONCAT('eyeCloudSIM Alert:' ,#{event_time}, ' ', #{sms_msg}),
			'A001_01',
			'No',
			'No',
			'No'
		FROM    MGR_SEARCH_EVENT a,
				MGR_SEARCH_ALARM_RECEIVER b,
				COM_USER c
		WHERE   a.ruleset_id = b.ruleset_id
		AND		b.user_id = c.user_id
		AND     a.ruleset_id = #{ruleset_id}
		AND     event_time   = #{event_time_src}
		AND     a.event_seq  = #{event_seq}
		AND     mobile_no IS NOT NULL
		AND     mobile_no != '';
	</insert> 
  
  
