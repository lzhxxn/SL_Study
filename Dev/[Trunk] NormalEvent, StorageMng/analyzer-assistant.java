<insert id="insertEquipStatus" parameterType="map">
		INSERT INTO MGR_EQP_STATUS 
		VALUES (
			#{eqp_id},
			#{stat_chk_type},
			#{stat_chk_port},
			null, 
			null, 
			null
		)
</insert>



public int insertEquipStatus(HashMap<String, Object> map) {
	int cnt = 0;
	SqlSession session = factory.openSession();
	try {
		cnt = session.insert("SchedulerMapper.insertEquipStatus", map);
		session.commit();
	} finally {
		session.close();
	}
	return cnt;
}


private final String INIT_STAT_CHK_TYPE	= "-1";	
private final String INIT_STAT_CHK_PORT	= "0";	


map.put("stat_chk_type", INIT_STAT_CHK_TYPE);
map.put("stat_chk_port", INIT_STAT_CHK_PORT);


if (dao.insertEquipment(map) > 0) {
	dao.insertEquipStatus(map);							
	if (conf.get("ha.mode") != null) {
		if ("master".equals(conf.get("ha.mode"))) {
			adapter.HttpURLConnection("https://" + conf.get("ha.slave.ip") + ":9105/ScheduleUpdateServlet?action_code=-2");
		} else {
			adapter.HttpURLConnection("https://" + conf.get("ha.master.ip") + ":9105/ScheduleUpdateServlet?action_code=-2");
		}
	}
}

