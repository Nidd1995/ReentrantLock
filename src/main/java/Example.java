import java.util.concurrent.locks.ReentrantLock;

public class Example {

    private final ReentrantLock lock = new ReentrantLock();
    private int count = 0;

    public static void main(String[] args) {
        final Example counter = new Example();
        Thread t1 = new Thread() {
            @Override
            public void run() {
                while (counter.getCount() < 6) {

                    try {
                        Thread.sleep(100);

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            }

        };

        Thread t2 = new Thread() {
            @Override
            public void run() {
                while (counter.getCount() < 6) {
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            }

        };

        t1.start();
        t2.start();
    }

    //Implicit Locking using synchronized keyword
//   public synchronized int getCount(){
//    System.out.println(Thread.currentThread().getName() + count);
//     return count++;
//  }

 //   locking using Reentrant Lock
    public int getCount() {
        lock.lock();

        try {
            System.out.println(Thread.currentThread().getName() + count);
            return count++;
        } finally {
            lock.unlock();
        }

    }


}
