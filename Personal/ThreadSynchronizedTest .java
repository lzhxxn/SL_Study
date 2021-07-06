package study;

public class ThreadSynchronizedTest {
	
	public static void main(String[] args) { 
		
		Task task = new Task();
		
		Thread t1 = new Thread(task);
		Thread t2 = new Thread(task);
		t1.setName("t1-Thread");
		t2.setName("t2-Thread");
		
		t1.start();
		t2.start();
	}
}

class Account {
	int balance = 1000;
	
	public synchronized void withDraw(int money) {
		
		if(balance >= money) {
			try {
				Thread thread = Thread.currentThread(); //현재 쓰레드
				System.out.println(thread.getName() + " 출금 금액 ->> " + money);
				Thread.sleep(1000);
				balance -= money; //balance = balance - money
				System.out.println(thread.getName()+ " balance : " + balance);
			}catch(Exception e) {
			}
		}
	}
}

class Task implements Runnable {
	
	Account acc = new Account();
	
	@Override
	public void run() {
		
		while(acc.balance > 0) {
			
			// 100, 200, 300 중의 한 값을 임의로 선택해서 출금(withDraw)한다.
			int money = (int)(Math.random() * 3 + 1)* 100;
			
			acc.withDraw(money);
		}
	}
}
/*
synchronized 키워드를 사용함으로써 balance 공유데이터에 대한 thread-safe를 시켰기 때문에
데이터나 메서드 점유하고 있는 스레드가 온전히 자신의 작업을 마칠 수 있습니다.
*/
