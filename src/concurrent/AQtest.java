package concurrent;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @auther: WJoe
 * @Description: AQS
 * @Date : 15:36 2018/7/24
 */
public class AQtest extends AbstractQueuedSynchronizer {


    public static void main(String[] args) {
        Lock lock = new ReentrantLock();

        ExecutorService executorService = Executors.newFixedThreadPool(4);

        for (int i = 0; i < 4; i++) {
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        lock.lock();
                        System.out.println(Thread.currentThread().getName());
                    } finally {
                        lock.unlock();
                    }


                }
            });
        }
    }


}
