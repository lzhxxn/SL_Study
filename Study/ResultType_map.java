*** 데이터 검색인 SELECT에 대해 알아본다.**

### 여러 개의 결과 얻기
***
**sample_mapper.xml**
```
<mapper namespace="sample.mybatis"> 
<select id="selectTest" resultType="map">
select * from test_table 
</select> </mapper>
```
**Main.java**
```
package sample.mybatis;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
public class Main {
    public static void main(String[] args) throws Exception {
        try (InputStream in = Main.class.getResourceAsStream("/mybatis-config.xml")) {
            SqlSessionFactory factory = new SqlSessionFactoryBuilder().build( in );
            try (SqlSession session = factory.openSession()) {
                List < Map < String, Object >> result = session.selectList("sample.mybatis.selectTest");
                System.out.println(result);
            }
        }
    }
}
```
**실행결과**
[{id=1, value=fizz}, {id=2, value=buzz}]

> 검색용 SQL은 select 태그로 선언한다.
id 속성에 해당 SQL을 참조하는 이름을 설정한다.
resultType 속성에 검색 결과의 레코드를 어떤 형태로 매핑하는 방법을 정의한다.
여기에서 map을 지정하고 있기 때문에, Map 형태로 변환된다.
검색 결과가 여러 레코드를 반환하는 경우 SqlSession의 selectList () 메소드를 사용한다.
반환 값은 List 형이 된다.


### 한개의 결과 얻기
***

```
select * from test_table limit 1
```
쿼리문만 바꿔주면 된다. 

### 매개 변수
#### 단일 매개변수(parameter)
***
**sample_mapping.xml**
```
<?xml version="1.0" encoding="UTF-8" ?> 
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd"> <mapper namespace="sample.mybatis">
<select id="selectTest" resultType="map"> 
select * from test_table where id=#{id} 
</select> 
</mapper>
```
**Main.java**
```
package sample.mybatis;
import java.io.InputStream;
import java.util.Map;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
public class Main {
    public static void main(String[] args) throws Exception {
        try (InputStream in = Main.class.getResourceAsStream("/mybatis-config.xml")) {
            SqlSessionFactory factory = new SqlSessionFactoryBuilder().build( in );
            try (SqlSession session = factory.openSession()) {
                Map < String, Object > result = session.selectOne("sample.mybatis.selectTest", 1);
                System.out.println(result);
            }
        }
    }
}
```
**실행 결과**
[DEBUG] s.m.selectTest - ==> Preparing: select * from test_table where id=? 
[DEBUG] s.m.selectTest - ==> Parameters: 1(Integer)
[DEBUG] s.m.selectTest - <== Total: 1 
{id=1, value=fizz}

> #{...} 형식으로 매개 변수를 선언할 수 있다.
매개 변수는 SqlSession에서 SQL을 실행할 때 Statement ID(sample.mybatis.selectTest) 다음에 인수로 전달한다.

### Map으로 매개 변수를 전달
여러 매개 변수를 전달할 때에는 Map을 사용하는 방법도 있다.
***
**sample_mapping.xml**
```
<?xml version="1.0" encoding="UTF-8" ?> 
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd"> <mapper namespace="sample.mybatis">
<select id="selectTest" resultType="map"> 
select * from test_table where id=#{id} and value=#{value}
</select> 
</mapper>
```
**Main.java**
```
package sample.mybatis;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
public class Main {
    public static void main(String[] args) throws Exception {
        try (InputStream in = Main.class.getResourceAsStream("/mybatis-config.xml")) {
            SqlSessionFactory factory = new SqlSessionFactoryBuilder().build( in );
            try (SqlSession session = factory.openSession()) {
                Map < String, Object > param = new HashMap < > ();
                param.put("id", 10);
                param.put("value", "hogeeee");
                Map < String, Object > result = session.selectOne("sample.mybatis.selectTest", param);
                System.out.println(result);
            }
        }
    }
}
```
**실행 결과**
[DEBUG] s.m.selectTest - ==> Preparing: select * from test_table where id=? and value=? 
[DEBUG] s.m.selectTest - ==> Parameters: 10(Integer), hogeeee(String)


> 매개 변수는 Map으로도 전달할 수 있다.
Map으로 전달될 때에는 select 태그의 parameterType 설정이 없어도 동작한다.


