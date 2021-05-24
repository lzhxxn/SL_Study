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
