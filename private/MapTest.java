package study;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

//Map 선언시 Map<String, String> map = new HashMapM<String, String>();이렇게 주로 선언한다.
//Map과 뒤의 HashMap이 다른이유는 Map이 인터페이스 이기 때문이다. 인터페이스는 껍데기여서 선언만 가능하고 객체 생성은 불가능하다. 때문에 자식인 HashMap으로 객체생성.
//List도 같은 맥락이다. 자식인 ArrayList,Vector 등으로 객체를 생성한다.

public class MapTest {
	
	public static void main(String[] args) {

		Map<String, Object> map = new HashMap<String, Object>();
		
		// 데이터 저장하기
		map.put("이름", "지훈");
		map.put("나이", 28);
		map.put("직업", "개발자");
		
		System.out.println("=================");
		// 저장한 데이터 꺼내오기
		System.out.println("key 출력>>> " + map.keySet()); // [이름, 나이, 직업]
		System.out.println("value 출력>>> " + map.values()); // [지훈, 28, 개발자]
		System.out.println("key&value 출력>>> " + map.toString()); // {이름=지훈, 나이=28, 직업=개발자}
		System.out.println("해당 키값 출력>>> " + map.get("나이")); // 28
		
		// 데이터 삭제
		map.remove("이름");
		
		// 데이터 수정하기
		map.replace("나이", 27);
		
		Map<String, String> map2 = new HashMap<String, String>();
		map2.put("k1", "apple");
		map2.put("k2", "samsung");
		map2.put("k3", "kakao");
		map2.put("k3", "line"); // 중복되는 키 값중 마지막 데이터가 덮어쓴다.
		Collection<String> col = map2.values();
		Iterator<String> ite = col.iterator();
		
		System.out.println("<< 전체 m 데이터 >>");
		while(ite.hasNext()) {
			System.out.println(ite.next());
		}
		
		System.out.println("<< 전체 m 키값 출력 >>");
		Set<String> set = map2.keySet();
		Iterator<String> keyset = set.iterator();
		while (keyset.hasNext()) {
			String key = keyset.next();
			System.out.println(key + "에 저장된 데이터 : " + map2.get(key)); // m.get(key) == key (key값)에 대한 value값을 get.
		}
		
		System.out.println("=================");
        System.out.println(" 		");
        
    	//Map데이터를 List에 삽입하기
    	List<Map<String,Object>> listMap = new ArrayList<Map<String,Object>>();
    	
    	Map<String,Object> m1 = new HashMap<String,Object>();
    	m1.put("num", "1");
    	m1.put("id", "test1");
    	m1.put("pw", "1111");
    	m1.put("tel", "010-1111-2222");
    	m1.put("add", "서울시 서대문구_1");
        listMap.add(m1);
        
    	Map<String,Object> m2 = new HashMap<String,Object>();
    	m2.put("num", "2");
    	m2.put("id", "test2");
    	m2.put("pw", "2222");
        m2.put("tel", "010-2222-2222");
        m2.put("add", "서울시 서대문구_2");
        listMap.add(m2);
        
    	Map<String,Object> m3 = new HashMap<String,Object>();
    	m3.put("num", "3");
    	m3.put("id", "test3");
    	m3.put("pw", "3333");
        m3.put("tel", "010-3333-3333");
        m3.put("add", "서울시 서대문구_3");
        listMap.add(m3);
        
    	Map<String,Object> m4 = new HashMap<String,Object>();
    	m4.put("num", "4");
    	m4.put("id", "test4");
    	m4.put("pw", "4444");
        m4.put("tel", "010-4444-4444");
        m4.put("add", "서울시 서대문구_4");
        listMap.add(m3);
        
    	Map<String,Object> m5 = new HashMap<String,Object>();
    	m5.put("num", "5");
    	m5.put("id", "test5");
    	m5.put("pw", "5555");
        m5.put("tel", "010-5555-5555");
        m5.put("add", "서울시 서대문구_5");
        listMap.add(m5);
        
        for(Map<String,Object> strMap : listMap) {
        	//System.out.println(strMap);
        	System.out.print(strMap.get("num")+ " ");
        	System.out.print(strMap.get("id")+ " ");
        	System.out.print(strMap.get("pw")+ " ");
        	System.out.print(strMap.get("tel")+ " ");
        	System.out.println(strMap.get("add"));
       
        }
	}
	
	
}


/* Console

=================
key 출력>>> [이름, 나이, 직업]
value 출력>>> [지훈, 28, 개발자]
key&value 출력>>> {이름=지훈, 나이=28, 직업=개발자}
해당 키값 출력>>> 28
<< 전체 m 데이터 >>
apple
samsung
line
<< 전체 m 키값 출력 >>
k1에 저장된 데이터 : apple
k2에 저장된 데이터 : samsung
k3에 저장된 데이터 : line
=================
 		
1 test1 1111 010-1111-2222 서울시 서대문구_1
2 test2 2222 010-2222-2222 서울시 서대문구_2
3 test3 3333 010-3333-3333 서울시 서대문구_3
3 test3 3333 010-3333-3333 서울시 서대문구_3
5 test5 5555 010-5555-5555 서울시 서대문구

*/
