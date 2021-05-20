 // ##Thread
/*
- 메모리를 할당받아 실행 중인 프로그램을 프로세스라고 합니다.

- 프로세스 내의 명령어 블록으로 시작점과 종료점을 가진다.

- 실행중에 멈출 수 있으며 동시에 수행 가능하다.

- 어떠한 프로그램내에서 특히 프로세스 내에서 실행되는 흐름의 단위.

*/

// ## Thread 생성 
/*
- 2가지 방법
① 직접 상속 받아 스레드 생성
② Runnable 인터페이스를 구현해서 생성
- Thread 클래스 이용
- Thread 클래스로 부터 제공되는 run()메소드 오버라이딩해서 사용
- Ex)
class ThreadA extends Thread
{
public void run()
{

  // 수행할 문장들 기술
}
}
- 실제 사용
- ThreadA TA = new ThreadA();
  TA.start();
  */

// <예제 소스코드1 - ThreadTest.class>
/*
public class ThreadTest extends Thread
{
    public void run()
    {
        // 인터럽트 됬을때 예외처리
        try
        {
            for(int i = 0 ; i < 10 ; i++)
            {
                // 스레드 0.5초동안 대기
                Thread.sleep(500);
                System.out.println("Thread : " + i);
            }
        }catch(InterruptedException e)
        {
            System.out.println(e);
        }
    }
}
*/

// <예제 소스코드2 - Thread1.class>
/*
public class Thread1 
{
    public static void main(String args[])
    {
        ThreadTest t1 = new ThreadTest();
        ThreadTest t2 = new ThreadTest();
        
        // 1. 동시에 똑같은 숫자가 나오고(start)
        /*t1.start();
        t2.start();*/
        
        // 2. 번갈아가면서 나옴(run)
        t1.run();
        t2.run();
    }
}
*/
  
// <결과1 - .start()>
 /*
스레드 : 0
스레드 : 0
스레드 : 1
스레드 : 1
...
*/
