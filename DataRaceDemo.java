import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Two shoppers adding items to a shared notepad
 */

class Shopper extends Thread {

    static int garlicCount = 0;
    static Lock pencil = new ReentrantLock();

    public void run() {
    	
    	
        for (int i=0; i<10; i++) {
        	pencil.lock();
            garlicCount++;
            pencil.unlock();
            System.out.println(Thread.currentThread()+ " is thinking...");
            try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            
            
        }
        
       
    }
   
}

public class DataRaceDemo {
    public static void main(String[] args) throws InterruptedException {
        Thread barron = new Shopper();
        Thread olivia = new Shopper();
        Thread rachel = new Shopper();
        
        barron.start();
        olivia.start();
        rachel.start();
        
        barron.join();
        olivia.join();
        rachel.join();
        
        System.out.println("We should buy " + Shopper.garlicCount + " garlic.");
    }
}