package study;

//단일스레드
public class S_thread {
   public void display() {
      for(char i = 'A'; i<= 'Z'; i++) {
         System.out.print(i);
      }
      System.out.println();
   }
   public static void main(String[] args) {
      System.out.println("Main 스레드 시작 !!!");
      
      new S_thread().display();
      for(char i = 'a'; i <= 'z'; i++) {
         System.out.print(i);
      }
      System.out.println();
      System.out.println("Main 스레드 끝!!!");
   }
}


package study;

public class M_thread extends Thread {
   @Override
   public void run() {
      for(char i = 'A'; i<= 'Z'; i++) {
         System.out.print(i);
         try {
            Thread.sleep(500);
         }catch(InterruptedException e) {
            e.printStackTrace();
         }
      }
      System.out.println();
   }
}


package study;

public class M2_thread implements Runnable{
   
   @Override
   public void run() {
      for(char i = 'a'; i <= 'z'; i++) {
         System.out.print(i);
         try {
            Thread.sleep(500);
         }catch(InterruptedException e) {
            e.printStackTrace();
         }
      }
   }
}


package study;

public class MultiThread {
   public static void main(String[] args) throws InterruptedException {
      //M_thread m = new M_thread();
      //m.start();
      new M_thread().start();
      
      //M2_thread m2 = new M2_thread();
      //Thread thread = new Thread(m2);
      //thread.start();
      new Thread(new M2_thread()).start();
      
      for(int i = 0; i<= 9; i++) {
         System.out.print(i);
         Thread.sleep(500); // 0.5초동안 스레드의 흐름을 일시정지
      }
   }
}
