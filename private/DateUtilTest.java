  package study;

import java.util.Date;

public class DateUtilTest {
	
	public static void main(String[] args) {
		String util = DateTimeUtil.getTimestamp().toString(); //Timestamp.toString() => 2021-06-10 19:57:59.303
		System.out.println("getTimestamp() : " + util);
		
		util = DateTimeUtil.getDate();
		System.out.println("getDate() : " + util);
		
		util = DateTimeUtil.getDate(DateTimeUtil.TYPE_DATE);
		System.out.println("getDate(DateTimeUtil.TYPE_DATE) : " + util);
		
		util = DateTimeUtil.getDate(DateTimeUtil.TYPE_TIME);
		System.out.println("getDate(DateTimeUtil.TYPE_TIME) : " + util);
		
		util = DateTimeUtil.getDate(DateTimeUtil.TYPE_DEFAULT);
		System.out.println("getDate(DateTimeUtil.TYPE_DEFAULT) : " + util);
		
		util = DateTimeUtil.getDate(DateTimeUtil.TYPE_TIMESTAMP);
		System.out.println("getDate(DateTimeUtil.TYPE_TIMESTAM) : " + util);

		util = DateTimeUtil.format(DateTimeUtil.TYPE_DATE);
		System.out.println("format(DateTimeUtil.TYPE_DATE) : " + util);

		util = DateTimeUtil.format("19930409", DateTimeUtil.TYPE_DATE); //현재시간이 아닌 입력된 시간을 원하는 포맷형식으로 return
		System.out.println("format(String date, DateTimeUtil.TYPE_DATE) : " + util);

		Date date = DateTimeUtil.getCalendarFromString("20210610145130").getTime(); //Calender.getTime()을 하기위해서 getTime()의 리턴값은 Date형식
		System.out.println("getCalendarFromString(String date)  : " + date);
		
		util = DateTimeUtil.addMin(10);
		System.out.println("addMin(int add) : " + util);
		
		long diffMillis = DateTimeUtil.diffMillis("20210626", "20210627");
		System.out.println("diffMillis(String fromDate, String toDate) : " + diffMillis);
		

	}
}

/*
getTimestamp() : 2021-06-10 15:39:46.306
getDate() : 202106101539
getDate(DateTimeUtil.TYPE_DATE) : 20210610
getDate(DateTimeUtil.TYPE_TIME) : 153946
getDate(DateTimeUtil.TYPE_DEFAULT) : 202106101539
getDate(DateTimeUtil.TYPE_TIMESTAM) : 20210610153946324
format(DateTimeUtil.TYPE_DATE) : yyyy-MM-dd
format(String date, DateTimeUtil.TYPE_DATE) : 19930409
getCalendarFromString(String date)  : Thu Jun 10 14:51:30 KST 2021
addMin(int add) : 202106101549
diffMillis(String fromDate, String toDate) : 86400000
*/
