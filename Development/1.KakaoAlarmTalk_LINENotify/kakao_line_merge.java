[수정한 부분]XOAR 메신저연동 (KAKAO / LINE)

1. AlarmHandler.java

+ 추가
import java.io.BufferedWriter;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.URL;
import javax.net.ssl.HttpsURLConnection;

(41)
	private static final String strEndpoint = "https://notify-api.line.me/api/notify";


/*
			 * ##############################################################
			 *  5. KAKAO 발송
			 * ############################################################
			 */
			if (cond.get("kakao_use_yn").equals("Y")) {
				m2.put("sms_msg", smsMsg);
				m2.put("event_time", DateTime.getFormatString("yyyy-MM-dd HH:mm:ss"));
				
				dao.insertAlarmKAKAO(m2);
			}
			
			/*
			 * ##############################################################
			 *  6. LINE 발송
			 * ############################################################
			 */
			if (cond.get("line_use_yn").equals("Y")) {
				List<Map<String, Object>> tokenKEY = dao.selectLineToken();
				for (Map<String, Object> key : tokenKEY) {
					Object token = key.get("line_token");
					if (token instanceof String && !((String) token).isEmpty()) {
						int statusCode = -1;
						try {
							URL url = new URL(strEndpoint);
							HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();

							connection.setRequestMethod("POST");
							connection.addRequestProperty("Authorization", "Bearer " + token); 
							connection.addRequestProperty("Content-Type", "application/x-www-form-urlencoded"); 

							connection.setConnectTimeout(15 * 1000); 
							connection.setDoOutput(true); 
							connection.setUseCaches(false);
							connection.connect();

							connection.setReadTimeout(1000); 

							try (OutputStream os = connection.getOutputStream();
									BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"))) {

								writer.write("message=" + smsMsg);
								writer.flush();
							} catch (Exception e) {
								throw e;
							}

							statusCode = connection.getResponseCode();

							if (statusCode == 200) {
								dao.insertAlarmLINE(m2);
							} else {
								throw new Exception(
										"Error:(StatusCode)" + statusCode + ", " + connection.getResponseMessage());
							}
							connection.disconnect();
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				}
			}		
			
2. AnalyzerEngineManager.java

+ 추가
import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.URL;

(113)
   private static final String strEndpoint = "https://notify-api.line.me/api/notify";

		//3. Apply KAKAO
		if (cond.get("kakao_use_yn").equals("Y")) {
			StringBuffer buffer = new StringBuffer();
			buffer.append(rule.get("event_nm"));
			buffer.append("["+event.get("field_val")+"]");
			
			alarm.put("sms_msg", buffer.toString());
			
			alarmDAO.insertAlarmEventKAKAO(alarm);
		}
		
		//4. Apply LINE
		if(cond.get("line_use_yn").equals("Y")) {
			StringBuffer buffer = new StringBuffer();
			buffer.append(rule.get("event_nm"));
			buffer.append("["+event.get("field_val")+"]");
			
			alarm.put("sms_msg", buffer.toString());
			
			List<Map<String,Object>> tokenKEY = alarmDAO.selectEventLineToken(alarm);
			for(Map<String,Object> key:tokenKEY) {
				
				Object token = key.get("line_token");
				if(token instanceof String && !((String) token).isEmpty()) {
					int statusCode = -1;
					try {
						URL url = new URL(strEndpoint);
						HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();

						connection.setRequestMethod("POST");
						connection.addRequestProperty("Authorization", "Bearer " + token); 
						connection.addRequestProperty("Content-Type", "application/x-www-form-urlencoded"); //

						connection.setConnectTimeout(15 * 1000);
						connection.setDoOutput(true);
						connection.setUseCaches(false); 
						connection.connect();

						connection.setReadTimeout(1000); 

						try (OutputStream os = connection.getOutputStream();
								BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"))) {

							writer.write("message=" + buffer.toString());
							writer.flush();
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
					}
				}
			}
		}

3. AlarmDAO.java

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
	
	public void insertAlarmEventKAKAO(Map<String, Object> m) {
		SqlSession session = factory.openSession();
		try {
			session.insert("AlarmMapper.insertAlarmEventKAKAO", m);
			session.commit();
		} finally {
			session.close();
		}
	}
	//#############################################################################
	// Line Notify Start
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
	
	public void insertAlarmLINE(Map<String, Object> m) {
		SqlSession session = factory.openSession();
		try {
			session.insert("AlarmMapper.insertAlarmLINE", m);
			session.commit();
		} finally {
			session.close();
		}
	}
	
	public void insertAlarmEventLINE(Map<String, Object> m) {
		SqlSession session = factory.openSession();
		try {
			session.insert("AlarmMapper.insertAlarmEventLINE", m);
			session.commit();
		} finally {
			session.close();
		}
	}

4. AlarmMapper.xml

	(267)
		<!--#########################################################-->
	<!--Alarm Common-->
	<!--#########################################################-->
	<select id="selectAlarmCondition" resultType="map">
		SELECT mail_use_yn,
		       sms_use_yn,
		       snd_use_yn,
		       kakao_use_yn,
		       line_use_yn,
		       MAX(IF(alarm_cd='1', alarm_yn, 'N')) alarm1_yn,
		       MAX(IF(alarm_cd='1', alarm_cond, 0)) alarm1_cond,
		       #MAX(IF(alarm_cd='2', alarm_yn, 'N')) alarm2_yn,
		       #MAX(IF(alarm_cd='2', alarm_cond, 0)) alarm2_cond,
		       MAX(IF(alarm_cd='3', alarm_yn, 'N')) alarm3_yn,
		       MAX(IF(alarm_cd='3', alarm_cond, 0)) alarm3_cond,
		       MAX(IF(alarm_cd='4', alarm_yn, 'N')) alarm4_yn,
		       MAX(IF(alarm_cd='4', alarm_cond, 0)) alarm4_cond,
		       MAX(IF(alarm_cd='5', alarm_yn, 'N')) alarm5_yn,
		       MAX(IF(alarm_cd='5', alarm_cond, 0)) alarm5_cond,
		       MAX(IF(alarm_cd='6', alarm_yn, 'N')) alarm6_yn,
		       MAX(IF(alarm_cd='6', alarm_cond, 0)) alarm6_cond,
		       MAX(IF(alarm_cd='7', alarm_yn, 'N')) alarm7_yn,
		       MAX(IF(alarm_cd='7', alarm_cond, 0)) alarm7_cond,
		       MAX(IF(alarm_cd='8', alarm_yn, 'N')) alarm8_yn,
		       MAX(IF(alarm_cd='8', alarm_cond, 0)) alarm8_cond,
		       MAX(IF(alarm_cd='9', alarm_yn, 'N')) alarm9_yn,
		       MAX(IF(alarm_cd='9', alarm_cond, 0)) alarm9_cond,		       
		       MAX(IF(alarm_cd='10', alarm_yn, 'N')) alarm10_yn,
		       MAX(IF(alarm_cd='10', alarm_cond, 0)) alarm10_cond,
		       MAX(IF(alarm_cd='11', alarm_yn, 'N')) alarm11_yn,
		       MAX(IF(alarm_cd='11', alarm_cond, 0)) alarm11_cond
		FROM (
			SELECT a.code_id alarm_cd,
			       IF(b.alarm_cd IS NULL, 'N', 'Y') alarm_yn,
			       b.alarm_cond
			FROM	COM_CODE a
			LEFT OUTER JOIN ALARM_CONDITION b
			  ON a.code_id = b.alarm_cd
			WHERE a.code_type = 'CS0018'
		) t,
		ALARM_MNG t2
	</select>


	<!--#########################################################-->
	<!--Alarm KAKAO Start-->
	<!--#########################################################-->	
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
			CONCAT('eyeCloudSIM Alert:' ,#{event_time}, ' ', #{sms_msg}),
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

	<!--#########################################################-->
	<!--Alarm LINE Start-->
	<!--#########################################################-->	
	<select id="selectLineToken" resultType="map">
		SELECT  a.user_id,
				line_token
		FROM    ALARM_RECEIVER a,
				COM_USER b
		WHERE   a.user_id = b.user_id;
	</select>
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

5. comuser_list_to_select.js

(58) 
	drawGrid = function($grid){
		var gridSource = {
			datatype: "json",
			datafields: [
				{ name: "user_id", type: "string"},
				{ name: "user_nm", type: "string"},
				{ name: "mobile_no", type: "string"},
				{ name: "mail_addr", type: "string"},
				{ name: "role_nm", type: "string"},
				{ name: "group_nm", type: "string"},
				{ name: "cust_nm", type: "string"},
				{ name: "reg_dt", type: "string"},
				{ name: "line_token", type: "string"}
			],
			root: 'rows',
			beforeprocessing: function(data){
				if (data != null){
					gridSource.totalrecords = data.totalRows;
				}
			},

			cache: false,
			url: mCfg.urlList
		},

		dataadapter = new $.jqx.dataAdapter(gridSource, {
			formatData : function(data) {
				var params = {}, param, flds = $(mCfg.formId).serializeArray();
				for(param in flds) {
					params[flds[param].name] = flds[param].value;
				};
				$.extend(data, params);
				
				return data;
			},
			loadError: function(xhr, status, error){
				alert(error);
			}
		});

(98)
			$grid.jqxGrid({
			keyboardnavigation: false,
			enablebrowserselection: true,
			source: dataadapter,
			width: '100%',
			pagesize: ctrlCookie.getValue(),
			virtualmode: true,
			selectionmode: 'checkbox',
			enablehover:false,
			rendergridrows: function(obj){
				return obj.data;
			},
			columns: [			
				{
					text: 'No', columntype: 'number', width:40, cellsalign:'center',
					cellsrenderer: function (row, column, value, defaulthtml) {
						return $(defaulthtml).text(value + 1)[0].outerHTML;
					}
				},
				{ text: _SL.getMessage("FLD.COM.0148"), datafield: 'user_id',  },
				{ text: _SL.getMessage("FLD.COM.0131"), datafield: 'user_nm',
					cellsrenderer: function(row, column, value, defaulthtml, columnproperties, rowdata){
						return $(defaulthtml).html('<button type="button" class="btn-link">' + value + '</button>')[0].outerHTML;
					}
				},
				{ text: 'Token', datafield: 'line_token' },
			/*	{ text: _SL.getMessage("FLD.COM.0008"), datafield: 'group_nm' , width:'15%'},*/
				{ text: _SL.getMessage("FLD.COM.0150"), datafield: 'mobile_no', cellsalign:'center', width:'15%'},
				{ text: _SL.getMessage("FLD.COM.0151"), datafield: 'mail_addr', width:'20%'},
			]
		});

6. alarm_manager.jsp
(20)
	<div class="ranges-group">
							<div class="range-2"><label><input type="checkbox" name="mail_use_yn" value="Y" ${alarmMng.mail_use_yn eq 'Y' ? 'checked' : ''}> <spring:message code="LBL.ALARM.0003"/></label></div>
							<div class="range-2"><label><input type="checkbox" name="sms_use_yn" value="Y" ${alarmMng.sms_use_yn  eq 'Y' ? 'checked' : ''}> <spring:message code="LBL.ALARM.0004"/></label></div>
							<div class="range-2"><label><input type="checkbox" name="snd_use_yn" value="Y" ${alarmMng.snd_use_yn  eq 'Y' ? 'checked' : ''}> <spring:message code="LBL.ALARM.0005"/></label></div>
						<!-- KAKAO ALARM & LINE ALARM -->
							<div class="range-2"><label><input type="checkbox" name="kakao_use_yn" value="Y" ${alarmMng.kakao_use_yn  eq 'Y' ? 'checked' : ''}>카카오톡</label></div>
							<div class="range-2"><label><input type="checkbox" name="line_use_yn" value="Y" ${alarmMng.line_use_yn  eq 'Y' ? 'checked' : ''}>라인</label></div>
	</div>

7. comuser_form.jsp
(90)
			<tr>
				<th scope="row">Line Token</th>
				<td colspan="2"><input type="text" name="line_token" class="form-input" maxlength="300"></td>
			</tr>
			<tr>

8. UserMapper.xml
(61)
	<select id="selectComUserListAll" resultType="map">
		SELECT
		    user_id,
		    user_nm,
		    passwd,
		    tel_no,
		    mobile_no,
		    mail_addr,
		    role_id,
		    group_cd,
		    auth_ip,
		    description,
		    DATE_FORMAT(STR_TO_DATE(reg_dt, '%Y%m%d%H%i%s'), '%Y-%m-%d %H:%i:%s') as reg_dt,
		    (SELECT role_nm FROM COM_USER_ROLE b WHERE b.role_id = a.role_id) as role_nm,
		    login_menu_id,
		    line_token
		FROM 
			COM_USER a
		<where>
			<if test="role_id != -1">
				AND role_id != -1
			</if>
			<if test="u_del_yn != null and u_del_yn != ''">
			AND del_yn = #{u_del_yn}
			</if>
		</where>
		ORDER BY user_nm
	</select>

(290)
		<insert id="insertComUser" parameterType="map">
		INSERT INTO COM_USER (
			user_id,
		    user_nm,
		    passwd,
		    pswd_upd_dt,
		    tel_no,
		    mobile_no,
		    mail_addr,
		    role_id,
		    group_cd,
		    auth_ip,
		    sync_yn,
		    description,
		    reg_dt,
		    proc_id,
		    proc_ip,
		    proc_dt,
		    login_menu_id,
		    line_token
		)
		VALUES ( 
			#{user_id},
		    #{user_nm},
		    #{passwd},
		    DATE_FORMAT(NOW(),'%Y%m%d%H%i%S'),
		    #{tel_no},
		    #{mobile_no},
		    #{mail_addr},
		    #{role_id},
		    #{group_cd},
		    #{auth_ip},
		    #{sync_yn},
		    #{description},
			DATE_FORMAT(NOW(),'%Y%m%d%H%i%S'),
		    #{proc_id},
		    #{proc_ip},
			DATE_FORMAT(NOW(),'%Y%m%d%H%i%S'),
			#{login_menu_id},
			#{line_token}
		)
	</insert>

(171)
	<select id="selectComUser" resultType="map">
		 SELECT
		    user_id,
		    user_nm,
		    passwd,
		    tel_no,
		    mobile_no,
		    mail_addr,
		    role_id,
		    group_cd,
		    auth_ip,
		    description,
		    reg_dt,
		    login_menu_id,
		    alarm_mode,
		    sync_yn,
		    del_yn,
		    line_token,
		    user_img,
		    (SELECT role_nm FROM COM_USER_ROLE b WHERE b.role_id = a.role_id) as role_nm
		FROM 
			COM_USER a
		WHERE
			user_id = #{user_id}
	</select>

(118)
		<select id="selectComUserList" resultType="map">
		SELECT
		    user_id,
		    user_nm,
		    passwd,
		    tel_no,
		    mobile_no,
		    mail_addr,
		    role_id,
		    group_cd,
		    auth_ip,
		    description,
		    last_conn_dt,
		    reg_dt,
		    pswd_upd_dt,
		    user_img,
		    line_token,
		    (SELECT role_nm FROM COM_USER_ROLE b WHERE b.role_id = a.role_id) as role_nm
		FROM 
			COM_USER a
		<where>
			<if test="role_id != -1">
				AND role_id != -1
			</if>
			<if test="login_user_ids != null and login_user_ids.size > 0 ">
				AND user_id IN (
				<foreach item="login_id" collection="login_user_ids" separator=",">
					#{login_id}
				</foreach>
				)
			</if>
			<if test="s_user_id != null and s_user_id != ''">
			AND user_id LIKE CONCAT('%',#{s_user_id},'%')	
			</if>
			<if test="s_user_nm != null and s_user_nm != ''">
			AND user_nm LIKE CONCAT('%',#{s_user_nm},'%')
			</if>
			<if test="s_group_cd != null and s_group_cd != ''">
			AND group_cd = #{s_group_cd}
			</if>
			AND del_yn = "N"
		</where>
		ORDER BY user_id
		<choose>
			<when test="recordstartindex==null">
				LIMIT 0, 10
			</when>
			<otherwise>
				LIMIT #{recordstartindex}, #{pagesize}
			</otherwise>
		</choose>
	</select>

(343)
	<update id="updateComUser" parameterType="map">
		UPDATE COM_USER SET
			<if test="passwd != null and passwd != ''">
				pswd_upd_dt = CASE WHEN passwd != #{passwd} THEN DATE_FORMAT(NOW(),'%Y%m%d%H%i%S') ELSE pswd_upd_dt END,
				passwd = CASE WHEN passwd != #{passwd} THEN #{passwd} ELSE passwd END,
			</if>
			user_nm = #{user_nm},
		    tel_no = #{tel_no},
		    mobile_no = #{mobile_no},
		    mail_addr = #{mail_addr},
		    role_id = #{role_id},
		    group_cd = #{group_cd},
		    auth_ip = #{auth_ip},
		    sync_yn = #{sync_yn},
		    description = #{description},
		    proc_id = #{proc_id},
		    proc_ip = #{proc_ip},
		    proc_dt = DATE_FORMAT(NOW(),'%Y%m%d%H%i%S'),
		    login_menu_id = #{login_menu_id},
		    line_token = #{line_token}
		WHERE
			user_id = #{user_id}
	</update>

9. AlarmManagerMapper.xml
(~6)
		<select id="selectAlarmMng" resultType="map">
		SELECT
		    mail_use_yn,
		    sms_use_yn,
		    snd_use_yn,
		    kakao_use_yn,
		    line_use_yn
		FROM 
			ALARM_MNG
	</select>	
	
	<update id="updateAlarmMng" parameterType="map">
		UPDATE ALARM_MNG SET
			mail_use_yn = #{mail_use_yn},
		    sms_use_yn = #{sms_use_yn},
		    snd_use_yn = #{snd_use_yn},
		    kakao_use_yn = #{kakao_use_yn},
		    line_use_yn = #{line_use_yn}
	</update>
	
	<!-- ***** ALARM_CONDITION ***** -->
	<select id="selectAlarmConditionList" resultType="map">
		SELECT
		    alarm_cd, <!-- 코드 -->
		    alarm_cond <!-- 발령조건 -->
		FROM 
			ALARM_CONDITION
	</select>	

10. AlarmManagerController.java
(87~)
			// 1. 경보방법
		map.put("mail_use_yn", "Y".equals((String)map.get("mail_use_yn")) ? "Y" : "N");
		map.put("sms_use_yn",  "Y".equals((String)map.get("sms_use_yn"))  ? "Y" : "N");
		map.put("snd_use_yn",  "Y".equals((String)map.get("snd_use_yn"))  ? "Y" : "N");
		map.put("kakao_use_yn", "Y".equals((String)map.get("kakao_use_yn")) ? "Y" :"N");
		map.put("line_use_yn", "Y".equals((String)map.get("line_use_yn")) ? "Y" : "N");

11. MainMapper.xml
(684)
	<!-- ########## Alarm Manager START ########## -->
	<select id="selectAlarmMng" resultType="map">
		SELECT mail_use_yn,
			   sms_use_yn,
			   snd_use_yn,
			   kakao_use_yn,
			   line_use_yn
		FROM ALARM_MNG
	</select>

12. comuser_form.js
(28)
		// JQuery 객체 변수
	m$ = {
		form : $(mCfg.formId),
		userId		: $(mCfg.formId + ' [name=user_id]'),
		userNm		: $(mCfg.formId + ' [name=user_nm]'),
		telNo		: $(mCfg.formId + ' [name=tel_no]'),
		mobileNo	: $(mCfg.formId + ' [name=mobile_no]'),
		lineTo		: $(mCfg.formId + ' [name=line_token]'),
		
		slKeyId : $(mCfg.formId + ' [name=slKey]')
	},

 ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ

[db_history.sql]


-----------------------------------------------------------------
-- 날짜 : 2021-06-30
-- 이름 : 이지훈
-- 모듈 : www
-- 주내용 : 카카오톡 라인 알림톡 관련
------------------------------------------------------------------
CREATE TABLE `ALARM_LINE` (
  `line_id` int(11) NOT NULL,
  `from_user_id` varchar(30) DEFAULT NULL,
  `from_token` varchar(100) DEFAULT NULL,
  `to_user_id` varchar(30) NOT NULL,
  `to_token` varchar(100) DEFAULT NULL,
  `sms_msg` varchar(100) NOT NULL,
  `stat_flag` char(1) NOT NULL DEFAULT '0',
  `stat_log` text DEFAULT NULL,
  `reg_time` varchar(14) NOT NULL,
  `update_time` varchar(14) DEFAULT NULL,
  PRIMARY KEY (`line_id`),
  KEY `IE_STAT_FLAG` (`stat_flag`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8

#ALARM_MNG
ALTER TABLE ALARM_MNG ADD kakao_use_yn CHAR(1) DEFAULT NULL;
ALTER TABLE ALARM_MNG ADD line_use_yn CHAR(1) DEFAULT NULL;
ALTER TABLE ALARM_MNG MODIFY COLUMN kakao_use_yn CHAR(1) AFTER snd_use_yn;
ALTER TABLE ALARM_MNG MODIFY COLUMN line_use_yn CHAR(1) AFTER kakao_use_yn;

#COM_USER
ALTER TABLE COM_USER ADD line_token VARCHAR(300) DEFAULT NULL;
ALTER TABLE COM_USER MODIFY COLUMN line_token VARCHAR(100) AFTER mail_addr;
  