* 1. pom.xml
```
<!--  Scheduler  -->
<dependency>
	<groupId>org.quartz-scheduler</groupId>
	<artifactId>quartz</artifactId>
	<version>${quartz.version}</version>
</dependency>
```
* 2. cron-trigger.properties
```
test.trigger.cronexpr=0 * * * * ?
```
* 3. servlet-context.xml
```
<!-- 1. Cron 대상이 되는 클래스 정의 -->
<bean name="testScheduleJob" class="org.springframework.scheduling.quartz.JobDetailFactoryBean">
	<property name="jobClass" value="com.jihoon.web.scheduler.testScheduleJob" />
	<property name="jobDataAsMap">
	<map>	<entry key="service" value-ref="searchEventTagService" />	</map>
	</property>
</bean>
```
```
<!-- 2. Cron 시간 설정 -->
<bean id="test" class="org.springframework.scheduling.quartz.CronTriggerFactoryBean">
	<property name="jobDetail" ref="testScheduleJob" />
	<property name="cronExpression" value="${test.trigger.cronexpr}" />	
</bean>
```
```
<!-- 3. Cron 실행 -->
<bean id="schedulerFactory" class="org.springframework.scheduling.quartz.SchedulerFactoryBean">
	<property name="quartzProperties">
		<props>	<prop key="xxxxx">xx</prop>	</props>
		</property>
	<property name="triggers">
	<list>
		<ref bean="test" />
	</list>
	</property>
</bean>
```
* 4. testSchedleJob.java
```
package com.seculayer.web.scheduler;

import org.apache.log4j.Logger;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.xxx.web.system.SearchEventTagService;

public class testScheduleJob extends QuartzJobBean{
	
	static Logger logger = Logger.getLogger(testScheduleJob.class);
	
	private Object svc;
	
	public void setService(Object svc) {
		this.svc = svc;
	}

	protected void executeInternal(JobExecutionContext context)
			throws JobExecutionException {
		logger.info("TestScheduleJob Excute Start!!!");
		
		try {
			((SearchEventTagService)svc).jihoon();
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		logger.info("TestScheduleJob Excute End!!!");
	
	}

}
```
* 5. SearchEventTagService.java
```
	public void jihoon() {
		System.out.println("jihoonjihoonjihoon");
		
	}
```
