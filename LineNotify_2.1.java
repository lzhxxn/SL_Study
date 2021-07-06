		/*##############################################################
		 * 6. LINE 발송 AlarmHandler.java
		 *############################################################*/
		if(cond.get("line_use_yn").equals("Y")) {
			List<Map<String,Object>> tokenKEY = dao.selectLineToken();
			for(Map<String,Object> key:tokenKEY) {
				Object token = key.get("line_token");
				if(token instanceof String && !((String) token).isEmpty()) {
					logger.info("############################################ LINE_NOTIFY_API START !");
					int statusCode = -1;
					try {
			            URL url = new URL( strEndpoint );
			            HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();

			            connection.setRequestMethod( "POST" ); // 요청 방식 설정 (GET/POST 등)
						connection.addRequestProperty("Authorization",  "Bearer " + token); // request Header 설정 key-value 형식으로 다양한 요청 설정이 가능하다.
			            connection.addRequestProperty("Content-Type", "application/x-www-form-urlencoded"); // 

			            connection.setConnectTimeout(15*1000); // 서버 연결 제한 시간
			            connection.setDoOutput( true ); // Server에서 온 데이터를 출력 할수 있는 상태인지 여부를 확인한다. ( default : true )
			            connection.setUseCaches( false ); // 캐시에 저장된 결과가 아닌 동적으로 그 순간에 생성된 결과를 읽도록
			            connection.connect();

			            connection.setReadTimeout(1000); // 서버 연결 후 데이터 read 제한 시간 // URL호출시 발생하는 무한 대기 상태에 빠지지 않도록 connectionTimeOut 발생시, ReadTimeOut 발생시 시간설정 꼭하기 !
						//connection.setDoInput( true ); // Server에서 온 데이터를 입력 받을 수 있는 상태인지 여부 확인 ( defalut : false )

			            try(OutputStream os = connection.getOutputStream();
			            		BufferedWriter writer = new BufferedWriter( new OutputStreamWriter( os, "UTF-8") )){

			                writer.write( "message=" + smsMsg );
				            writer.flush();
//				            writer.close();
			            }catch(Exception e) {
			            	throw e;
			            }
			            

			            statusCode = connection.getResponseCode();

			            if ( statusCode == 200 ) {
				            dao.insertAlarmLINE(m2);
			            } else {
			                throw new Exception( "Error:(StatusCode)" + statusCode + ", " + connection.getResponseMessage() );
			            }
			            connection.disconnect();
			        } catch (Exception e) {
			            e.printStackTrace();
			            logger.info("############################################ LINE_NOTIFY_API ERROR !");
			        }	
			}
		}			
	}


	//#############################################################################
	// Line Notify Start (AlarmDAO.java)
	//#############################################################################	
	
	public List<Map<String,Object>> selectLineToken() {
		SqlSession session = factory.openSession();
		List<Map<String,Object>> list = null;
		try {
			list = session.selectList("AlarmMapper.selectLineToken");			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return list;
	}

	public void insertAlarmLINE(Map<String, Object> m) {
		SqlSession session = factory.openSession();
		try {
			session.insert("AlarmMapper.insertAlarmLINE", m);
			session.commit();
		} finally {
			session.close();
		}
	}

	//#############################################################################
	// Line Notify (AlarmMapper.xml)
	//#############################################################################	
	<select id="selectLineToken" resultType="map">
		SELECT  a.user_id,
			b.user_id,
			line_token
		FROM    ALARM_RECEIVER a,
			COM_USER b
		WHERE   a.user_id = b.user_id;
	</select>
	
		
//<!--#########################################################-->
//<!--Alarm LINE Start-->
//<!--#########################################################-->		
	<insert id="insertAlarmLINE" parameterType="map">
		INSERT INTO ALARM_LINE
		(
			line_id,
			from_user_id,
			from_token,
			to_user_id,
			to_token,
			sms_msg,
			stat_flag,
			reg_time
		)
		SELECT (SELECT IFNULL(MAX(line_id), 0) + (@rownum := @rownum + 1) FROM ALARM_LINE),
		       #{from_user_id},
		       (SELECT line_token FROM COM_USER WHERE user_id = #{from_user_id}),
		       a.user_id,
		       b.line_token,
		       #{sms_msg},
		       '0',
		       DATE_FORMAT(NOW(), '%Y%m%d%H%i%s')
		FROM   ALARM_RECEIVER a,
		       COM_USER b,
		       (SELECT @rownum := 0) r
		WHERE  a.user_id = b.user_id
		AND    line_token IS NOT NULL
		AND    line_token != ''
	</insert>
		
	//기타 웹개발
			       
	//AnalyzeEngineManager.java - ApplyAlarm();
			//3. Apply LINE
		if(cond.get("line_use_yn").equals("Y")) {
			StringBuffer buffer = new StringBuffer();
			buffer.append(rule.get("event_nm"));
			buffer.append("["+event.get("field_val")+"]");
			
			alarm.put("sms_msg", buffer.toString());
			
			List<Map<String,Object>> tokenKEY = alarmDAO.selectEventLineToken(alarm);
			for(Map<String,Object> key:tokenKEY) {
				
				Object token = key.get("line_token");
				if(token instanceof String && !((String) token).isEmpty()) {
					logger.info("#####Event LINE_NOTIFY START !");
					int statusCode = -1;
					try {
						URL url = new URL(strEndpoint);
						HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();

						connection.setRequestMethod("POST"); // 요청 방식 설정 (GET/POST 등)
						connection.addRequestProperty("Authorization", "Bearer " + token); // request Header 설정
																							// key-value 형식으로 다양한 요청
																							// 설정이 가능하다.
						connection.addRequestProperty("Content-Type", "application/x-www-form-urlencoded"); //

						connection.setConnectTimeout(15 * 1000); // 서버 연결 제한 시간
						connection.setDoOutput(true); // Server에서 온 데이터를 출력 할수 있는 상태인지 여부를 확인한다. ( default : true )
						connection.setUseCaches(false); // 캐시에 저장된 결과가 아닌 동적으로 그 순간에 생성된 결과를 읽도록
						connection.connect();

						connection.setReadTimeout(1000); // 서버 연결 후 데이터 read 제한 시간 // URL호출시 발생하는 무한 대기 상태에 빠지지 않도록
															// connectionTimeOut 발생시, ReadTimeOut 발생시 시간설정 꼭하기 !
						// connection.setDoInput( true ); // Server에서 온 데이터를 입력 받을 수 있는 상태인지 여부 확인 (
						// defalut : false )

						try (OutputStream os = connection.getOutputStream();
								BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"))) {

							writer.write("message=" + buffer.toString());
							writer.flush();
							// writer.close();
						} catch (Exception e) {
							throw e;
						}

						statusCode = connection.getResponseCode();
						
						if ( statusCode == 200 ) {
							alarmDAO.insertAlarmEventLINE(alarm);
						} else {
							throw new Exception( "Error:(StatusCode)" + statusCode + ", " + connection.getResponseMessage() );
						}
						connection.disconnect();
					} catch (Exception e) {
						e.printStackTrace();
						logger.info("#####Event LINE_NOTIFY ERROR !");
					}
				}
			}
		}
	
	//AlarmDAO.java
	public List<Map<String,Object>> selectEventLineToken(Map<String, Object> m) {
			SqlSession session = factory.openSession();
			List<Map<String,Object>> list = null;
			try {
				list = session.selectList("AlarmMapper.selectEventLineToken", m);			
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				session.close();
			}
			return list;
		}
	//AlarmMapper.xml
	//selectEventLineToken
	<select id="selectEventLineToken" resultType="map">
		SELECT  b.user_id,
				line_token
		FROM    MGR_SEARCH_EVENT a,
				MGR_SEARCH_ALARM_RECEIVER b,
				COM_USER c
		WHERE   a.ruleset_id = b.ruleset_id
		AND		b.user_id = c.user_id
		AND     a.ruleset_id = #{ruleset_id}
		AND     event_time   = #{event_time_src}
		AND     a.event_seq  = #{event_seq}
		AND     IFNULL(line_token, '') != ''
	</select>
	
	//insertAlarmEventLINE
	<insert id="insertAlarmEventLINE" parameterType="map">
		INSERT INTO ALARM_LINE (
			line_id,
			from_user_id,
			from_token,
			to_user_id,
			to_token,
			sms_msg,
			stat_flag,
			reg_time
		)
		SELECT (SELECT IFNULL(MAX(line_id), 0) + (@rownum := @rownum + 1) FROM ALARM_LINE),
				       #{from_user_id},
				       (SELECT line_token FROM COM_USER WHERE user_id = #{from_user_id}),
				       b.user_id,
				       c.line_token,
				       #{sms_msg},
				       '0',
				       DATE_FORMAT(NOW(), '%Y%m%d%H%i%s')
		FROM   MGR_SEARCH_EVENT a,
		       MGR_SEARCH_ALARM_RECEIVER b,
		       COM_USER c,
		       (SELECT @rownum := 0) s
		WHERE  a.ruleset_id = b.ruleset_id
		AND    b.user_id    = c.user_id
		AND    a.ruleset_id = #{ruleset_id}
		AND    event_time   = #{event_time_src}
		AND    event_seq    = #{event_seq}
		AND    IFNULL(mobile_no, '') != ''
		AND    (b.user_id, a.group_cd) NOT IN (
			SELECT r.user_id,
			       cust_id
			FROM   MGR_SEARCH_ALARM_RECEIVER r,
			       COM_USER_CUST u
			WHERE  r.user_id    = u.user_id
			AND    r.ruleset_id = #{ruleset_id}
			AND    cust_id     != IF(a.group_cd IS NOT NULL, a.group_cd, '')
		)
	</insert>
	
