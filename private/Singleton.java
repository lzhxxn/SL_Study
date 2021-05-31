package study;

class Singleton {
	private static Singleton one;
	private Singleton() { //private 키워드로 외부 클래스에서 Singleton 클래스의 생성자로의 접근을 막았기 때문이다. 
	}
	
	public static Singleton getInstance() { // getInstance라는 static 메소드를 이용하여 Singleton 객체를 돌려 받을 수 있다.
		if(one==null) {
			one = new Singleton();
		}
		return one;
	}
}

public class SingletonTest{
	public static void main(String[] args) {
		Singleton singleton1 = Singleton.getInstance();
		Singleton singleton2 = Singleton.getInstance();
		System.out.println(singleton1 == singleton2);
	}
}

// Singleton 클래스에 one이라는 static 변수를 두고 getInstance 메소드에서 one 값이 null인 경우에만 객체를 생성하도록 하여 one 객체가 단 한번만 만들어지도록 했다.
// getInstance 메소드의 동작원리를 살펴보자.
// 최초 getInstance가 호출 되면 one이 null이므로 new에 의해서 객체가 생성이 된다. 이렇게 한번 생성이 되면 one은 static 변수이기 때문에 그 이후로는 null이 아니게 된다.
// 그런 후에 다시 getInstance 메소드가 호출되면 이제 one은 null이 아니므로 이미 만들어진 싱글톤 객체인 one을 항상 리턴하게 된다. 

// main 메소드에서 getInstance를 두번 호출하여 각각 얻은 객체가 같은 객체인지 알아본 결과 "true"가 출력되어 같은 객체임을 확인 할 수 있다.
