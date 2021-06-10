package study;


public class StringUtilTest {

	public static void main(String[] args) {
		
		String keyword = "         soccer, baseball, basketball";
		System.out.println(keyword.trim());
		
		//객체(입력값)이 비어있는지 여부판단
		boolean flag = false;
		Object util = "   HelloWorld :-)  ";  //@return 입력값이 null이거나 빈칸이면 true리턴
		flag = StringUtil.isEmpty(util);
		System.out.println("isEmpty : " + flag);
		
		Object utils = "";
		if(StringUtil.isNotEmpty(utils)) {
			System.out.println("FULL");
		}else {
			System.out.println("EMPTY");
		}
		
		//@return obj의 문자열 변환, 만약 obj가 빈 값이면 str 반환 
		util = StringUtil.ifEmpty(utils, "공백싫어   !  !");
		System.out.println("ifEmpty(Object obj, String str) : " + util);
		
		//@return obj가 String이었다면 앞뒤공백제거하고 String리턴  그렇지 않으면 객제 정보를 String으로 리턴
		Object utilse = "        안녕 난 지훈   ";
		util = StringUtil.get(utilse);
		System.out.println("get(Object obj) : " + util);
		
		//* String을 Double로 변환
		//* 객체를 위에있는 get(Object obj)를 이용해 String화한 후 빈칸이 아니면 스트링을 Double로 변환
		String num = "123";
		Double getDouble = StringUtil.getDouble(num);
		System.out.println("getDouble(Object obj) : " + getDouble);
		
		
		//* 객체를 위에있는 get(Object obj)를 이용해 String화한 후 빈칸이 아니면 숫자인지 판별하여 true false 반환
		if(StringUtil.isNumber(utils)) { // utils : 공백싫어 ! !이기때문에 TRUE가 나온다.
			System.out.println("FALSE");
		}else{
			System.out.println("TRUE");
		}
		
		// split
		String[] split = StringUtil.split(keyword);
		for(String u : split)
			System.out.println("keyword : " + u );
		
		/*
		 * // join <-> split String[] join = {"헬로우", "미스터", "선샤인"};
		 * System.out.println(StringUtil.join(join)); => 헬로우미스터선샤인
		 */
		
		// getTextSize
		// * @param num size를 알고싶은 String 문장	
		int size = StringUtil.getTextSize(keyword);
		System.out.println(size);
		
		// arrayJoin
		String[] arr = {"두부", "계란", "밥"};
		String utilss = StringUtil.arrayJoin("/", arr);
		System.out.println(utilss);
		
		// lucene
		keyword= "안녕..//..난AI..{지훈}";
		String utilsss = StringUtil.luceneValueEscape(keyword);
		System.out.println(utilsss);
		
		// Trunc
		// val값이 null이거나빈칸이면 "0"리턴, val값이 있을 경우 정수부분만 리턴
		String trunc = "123.4567689";
		trunc = StringUtil.Trunc(trunc);
		System.out.println(trunc);
		
		System.out.println(StringUtil.format(123.2456, "0.####"));
		System.out.println(StringUtil.getSplitValue("안녕,난,AI", 2));
		System.out.println(StringUtil.getSplitIndexOf("안녕,난,AI", "AI"));
		
		
		
	}
}

/*
soccer, baseball, basketball
isEmpty : false
EMPTY
ifEmpty(Object obj, String str) : 공백싫어   !  !
get(Object obj) : 안녕 난 지훈
getDouble(Object obj) : 123.0
TRUE
keyword :          soccer
keyword :  baseball
keyword :  basketball
28
두부/계란/밥
안녕..\/\/..난AI..\{지훈\}
123
123.2456
AI
2
*/
