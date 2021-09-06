public class HousePark  {
    String lastname = "박";

    public static void main(String[] args) {
        HousePark pey = new HousePark();
        HousePark pes = new HousePark();
    }
}

/*
박씨 집안을 나타내는 HousePark이라는 클래스이다. 위와 같은 클래스를 만들고 객체를 생성하면 객체마다 객체변수 lastname을 저장하기 위한 메모리를 별도로 할당해야 한다.
하지만 가만히 생각해 보면 HousePark 클래스의 lastname은 어떤 객체이던지 동일한 값인 "박"이어야 할 것 같지 않은가? 
이렇게 항상 값이 변하지 않는 경우라면 static 사용 시 메모리의 이점을 얻을 수 있다.
*/

public class HousePark  {
    static String lastname = "박";

    public static void main(String[] args) {
        HousePark pey = new HousePark();
        HousePark pes = new HousePark();
    }
}

/*
위와 같이 lastname 변수에 static 키워드를 붙이면 자바는 메모리 할당을 딱 한번만 하게 되어 메모리 사용에 이점을 볼 수 있게된다.
※ 만약 HousePark 클래스의 lastname값이 변경되지 않기를 바란다면 static 키워드 앞에 final이라는 키워드를 붙이면 된다.
final 키워드는 한번 설정되면 그 값을 변경하지 못하게 하는 기능이 있다. 변경하려고 하면 예외가 발생한다.
*/


// 웹 사이트 방문시마다 조회수를 증가시키는 Counter 프로그램이 다음과 같이 있다고 가정 해 보자.
public class Counter  {
    int count = 0;
    Counter() {
        this.count++;
        System.out.println(this.count);
    }

    public static void main(String[] args) {
        Counter c1 = new Counter();
        Counter c2 = new Counter();
    }
}
//프로그램을 수행해 보면 다음과 같은 결과값이 나온다.
/*
    1
    1
c1, c2 객체 생성시 count 값을 1씩 증가하더라도 c1과 c2의 count는 서로 다른 메모리를 가리키고 있기 때문에 원하던 결과(카운트가 증가된)가 나오지 않는 것이다.
객체변수는 항상 독립적인 값을 갖기 때문에 당연한 결과이다.
*/

//이번에는 다음 예제를 보자.
public class Counter  {
    static int count = 0;
    Counter() {
        this.count++;
        System.out.println(this.count);
    }

    public static void main(String[] args) {
        Counter c1 = new Counter();
        Counter c2 = new Counter();
    }
}
//int count = 0 앞에 static 키워드를 붙였더니 count 값이 공유되어 다음과 같이 방문자수가 증가된 결과값이 나오게 되었다.
/*
    1
    2
보통 변수의 static 키워드는 프로그래밍 시 메모리의 효율보다는 두번째 처럼 공유하기 위한 용도로 훨씬 더 많이 사용하게 된다.
*/

//// static method
// static이라는 키워드가 메소드 앞에 붙으면 이 메소드는 스태틱 메소드(static method)가 된다. 

public class Counter {
    static int count = 0;
    Counter(){
        this.count++;
    }
    
    public static int getCount(){
        return count;
    }
    
    public static void main(String[] args){
        Counter c1 = new Counter();
        Counter c2 = new Counter();
        
        System.out.println(Counter.getCount());
    }   
}
//※ 스태틱 메소드 안에서는 인스턴스 변수 접근이 불가능 하다. 위 예에서 count는 static 변수이기 때문에 스태틱 메소드(static method)에서 접근이 가능한 것이다.
// 인스턴스란 ? 컴퓨터 내에서는 메모리에 실제로 올라와서 쓸 수 있는 상태가 된 것을 의미한다.

// 클래스변수 VS 인스턴스변수 ?
// 1. 클래스 변수란 클래스 내에서 static을 붙여서 선언하는 변수다. 한번 선언되면 해당 클래스의 모든 인스턴스가 같은 저장공간을 가리킨다. 전역변수라고 부르기도 한다.
public class Webtest{
    static int a = 123;
    public static void main(String[] args) {
        Webtest ex1 = new Webtest();
        Webtest ex2 = new Webtest();
        System.out.println(ex1.a);
        ex2.a = 321;
        System.out.println(ex1.a);
    }
}
/* 결과
        123
        321
*/
// 그 이유는 클래스 변수인 a는 모든 인스턴스에서 하나의 저장공간을 공유하기 때문에 다른 인스턴스에서 변경하면 다른 인스턴스도 변경된 값을 가지게 되기 때문이다.
public class Webtest{
    static int a = 123;
    
    public static void main(String[] args){
        System.out.println(a);
    }
}
// 이와 같이 인스턴스없이 바로 변수에 접근해서 사용할 수 있다.

//2. 인스턴스변수
// 인스턴스 변수는 클래스 변수와 마찬가지로 클래스 내에 선언한다. 차이점은 인스턴스에 종속되어 인스턴스 생성시 마다 새로운 저장 공간을 할당한다. 즉, 저장공간이 공유되지 않는다
// 또한 인스턴스에 종속되기 때문에 꼭 인스턴스객체에서 호출해 주어야 한다. 
public class Webtest {
    
    static int a = 123;
    int b = 321;
    
    public static void main(String[] args) {
        System.out.println(a);
        //System.out.println(b); *error 인스턴스 변수인 b는 인스턴스 없이 접근할 수 없기 때문이다.또한, ex1과 ex2의 출력 차이를 통해 저장공간이 공유되지 않는 것을 알 수 있다.
        
        Webtest ex1 = new Webtest();
        Webtest ex2 = new Webtest();
        
        ex1.b = 456;
        
        System.out.println(ex1.b);
        System.out.println(ex2.b);
    }
}
/* 결과
        123
        456
        321
*/

/*
보통 스태틱 메소드는 유틸리티 성 메소드를 작성할 때 많이 사용된다. 예를 들어 "오늘의 날짜 구하기", "숫자에 콤마 추가하기"등의 메소드를 작성할 때에는 클래스 메소드를 사용하는 것이 유리하다.
다음은 "날짜"를 구하는 Util 클래스의 예이다.
*/
import java.text.SimpleDateFormat;
import java.util.Date;


public class Util {
    public static String getCurrentDate(String fmt) {
        SimpleDateFormat sdf = new SimpleDateFormat(fmt);
        return sdf.format(new Date());
    }

    public static void main(String[] args) {
        System.out.println(Util.getCurrentDate("yyyyMMdd"));
    }
}
