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
