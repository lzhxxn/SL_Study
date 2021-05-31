// ` _상속을 받아서 기능을 확장시키는 것(부모의 유전자를 물려받는다)_ `

/* --추상 메서드를 선언하는 이유
설계자가 특정 메서드를 각 클래스 별로 재 구현을 원하지만 부모 클래스에서 일반 메서드로 구현하면 자식 클래스에서 구현을 하지 않는 경우가 발생할 수 있다. 
이런 메서드를 추상 메서드로 선언하면 자식 클래스는 재 구현을 강요받는다. */

/*
--추상 클래스 설계 및 구현
택시와 버스는 사람을 목적지까지 운송해주는 운송수단이다. 즉, 운송수단 객체를 추상 클래스로 선언하고 이를 택시 객체와 버스 객체에서 상속받아 필요에 맞게 기능을 재구현 하도록 하겠다.

운송수단(부모 클래스)
탑승하다 : 선언 및 기능 구현
이동하다 : 선언
교통비 지불하다: 선언

택시(자식 클래스)
이동하다 : 재 구현(목적지에 도착하다)
교통비 지불하다: 재 구현(이동거리 따른 요금을 지불하다)
기타 기능 : 선언 및 구현

버스(자식 클래스)
이동하다 : 재 구현(목적지 근처에 정류장에 도착하다)
교통비 지불하다: 재 구현(승차 요금을 지불하다)
기타 기능 : 선언 및 구현

설계자는 자식 클래스에서 이동하는 방법과 교통비 지불 방법을 클래스 별 특성에 맞게 재 구현을 원할 경우 두 기능을 추상 메서드로 선언하고 자식 클래스에서 재 구현하도록 설계하면 된다.
*/

//예제
//부모 클래스
abstract class Vehicle {
 public Vehicle() {}
 
 void board(){
  System.out.println("탑승하다");
 }
 
 //목적지까지 이동하는 방법
 abstract void move();
 //요금을 지불하는 방법
 abstract void pay();
}

//자식 클래스 1
public class Taxi extends Vehicle {
 public Taxi() {}

 @Override
 void move() {
  System.out.println("목적지에 도착하다.");
 }

 @Override
 void pay() {
  System.out.println("이동거리 따른 요금을 지불하다");
 }
}

//자식 클래스2
public class Bus extends Vehicle {
 public Bus() {}

 @Override
 void move() {
  System.out.println("목적지 근처에 정류장에 도착하다");
 }

 @Override
 void pay() {
  System.out.println("승차 요금을 지불하다");
 }
} 

//실행 클래스

public class AbstractTest {
 public static void main(String[] args) {
  Vehicle t = new Taxi();
  Vehicle b = new Bus();
  
  t.board();
  t.move();
  t.pay();
  
  b.board();
  b.move();
  b.pay();
 }
} 

// 결과화면
/*
탑승하다
목적지에 도착하다.
이동거리 따른 요금을 지불하다
탑승하다
목적지 근처에 정류장에 도착하다
승차 요금을 지불하다

위 설계를 바탕으로 개발을 하였다. board()는 모든 운송수단의 공통 기능으로 정의하고 이동 방법과 요금 납부를 자식 클래스에게 구현을 위임하였다.
결과 화면을 보면 "탑승하다"의 기능은 Vehicle 클래스의 메서드를 사용했으며, "이동하다", "지불하다"의 기능은 자식 클래스의 기능이 사용됨을 확인할 수 있다.
*/
