// `private static Logger logger 선언 이유`

// 1. Logger
/* Name
Logger들은 이름 기반으로 생성이 됩니다. LoggerFactory.getLogger(“NAME”)로 Logger를 호출하면 “NAME”에 대한 딱하나를 instance를 반환합니다. 
여러번 호출해도 같은 객체입니다. String 대신 .class로 클래스 정보를 넘겨주면 .getName()으로 클래스 이름을 사용하게 됩니다. 
흔히 Class객체를 넘겨주어 결국 이름은 packageName + ClassName으로 구성이 됩니다. 흔히 Logger는 특정 패키지 이하로 재한을 두고 정의를 합니다.*/

// 2. static이란?
/*
static은 '정적인', '움직임이 없는'으로 해석할 수 있는데, 이 static을 사용하면 메모리가 jvm의 static 메모리(해당 영역은 프로그램이 시작하고 종료될 때까지 살아있는다.) 에 올라간다.
static 메모리에 올라가기 때문에 초기화 과정이 필요없어 static이 선언된 변수, 메서드에 곧장 접근이 가능하다.
*/
public class Ketchup {
    // 변수에 static 사용 
    private static final String HEINZE = "heinze";
    private static final String OTTOGI = "ottogi";

    // 메서드에 static 사용 
    public static boolean isHeinzeKetchup(String brandName) {
        return brandName.equals(HEINZE);
    }

    public static boolean isOttogiKetchup(String brandName) {
        return brandName.equals(OTTOGI);
    }

}
/*
static을 사용한다는 의미는 해당 객체를 공유하겠다 는 의미이다.
여기저기서 해당 객체를 사용한다치면, 그 객체는 항상 동일한 객체라는 뜻이다.
(주의: 그래서 static이 선언된 변수의 값을 바꿔버리면 다른 곳에서 해당 변수의 값을 참조하는 부분의 값이 변한다.)
*/

// 3. final이란?
// final은 불변하도록 만드는 것이 아니라 재할당할 수 없도록 만드는 것이다. 상속을 하거나, 최초 초기화 이후 다시 초기화를 할 수 없다.

public class MutableTest {

    private final Map<String, Object> mutableMap = new HashMap<>();

    public void testFinal() {
        // 재할당 안됨
        // mutableMap = new HashMap<>();

        mutableMap.put("choco", "M&M");
        mutableMap.put("jelly", "haribo");

        System.out.println(mutableMap.toString());
    }

    public static void main(String[] args) {
        MutableTest mutableTest = new MutableTest();
        mutableTest.testFinal();
    }
}
/*
재할당은 안되지만 값은 변할 수 있다.
하지만 대게 Collections타입(Map,List,Set)에 해당하고, 기본 데이터 타입인 int, boolean, char 등등에서는 재할당이 안되니 값을 변경할 수 있는 방법이 없다. 
위의 예제에서 쓴 logger의 경우도 값이 변할 일이 없어보인다.
*/

// 정리
/*
` 그럼 상수로 사용할 때, private static final인가? `
private static final을 선언한 변수를 사용하면 재할당하지 못하며, 메모리에 한 번 올라가면 같은 값을 클래스 내부의 전체 필드, 메서드에서 공유한
private final을 선언한 변수를 사용하면 재할당하지 못하며, 해당 필드, 메서드별로 호출할 때마다 새로이 값이 할당(인스턴스화)한다.
그렇다면 상수로 사용하려고 할 때, 그 값은 변하지 않을 것인데 호출할 때마다 새롭게 인스턴스화할 필요가 없다. 한 번 메모리에 올려놓고 계속 같은 값을 갔다쓰면 될 일이다.
*/



// `getInstance()
//A클래스

public class A {
	//전역 객체변수로 사용하기 위해 static 객체변수로 생성
	static A instance;
	
	//생성자를 priavte로 만들어 접근을 막는다
	private A(){}
	
	//getInstance 메소드를 통해 한번만 생성된 객체를 가져온다.
	public static A getInstance(){
		if(instance == null){ //최초 한번만 new 연산자를 통하여 메모리에 할당한다.
			instance = new A();
		}		
		return instance;
	}
}


//B클래스

public class B {
	public B() {}
    }


//A클래스 VS B클래스

public class Main {

	public static void main(String[] args) {
    
   // getInstance() 메소드를 이용하여 변수를 달리하여, 3개의 객체를 불러옴
		A a1 = A.getInstance();
		A a2 = A.getInstance();
		A a3 = A.getInstance();

		System.out.println(a1);
		System.out.println(a2);
		System.out.println(a3);

		// 일반적으로 new 생성자를 이용하는 방법을 사용하여, 3개의 객체를 불러옴
		B b1 = new B();
		B b2 = new B();
		B b3 = new B();

		System.out.println(b1);
		System.out.println(b2);
		System.out.println(b3);

	}
}


//결과 Console
/*
algorithm.A@d716361
algorithm.A@d716361
algorithm.A@d716361
algorithm.B@3764951d
algorithm.A@4b1210ee
algorithm.A@4d7e1886

위에 getInstance()를 이용해서 먼저 출력한 a1, a2, a3 의 메모리 주소값을 보면 모두 같다. 이와 대비하여, new 생성자를 이용한 b1, b2, b3는 모두 주소값이 다른 것을 확인 할 수 있다.
*/
