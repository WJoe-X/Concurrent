package concurrent;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * @auther: WJoe
 * @Description:
 * @Date : 15:48 2018/7/23
 */
public class CountDownLatchTest {
    private static CountDownLatch c = new CountDownLatch(4);
    private Executor executor = Executors.newFixedThreadPool(4);

    private ConcurrentHashMap<String, Integer> sheetCount = new ConcurrentHashMap<>();

    public void count() {
        int result = 0;

        for (int i = 0; i < 4; i++) {
            executor.execute(new Runnable() {
                @Override
                public void run() {
                    c.countDown();
                    sheetCount.put(Thread.currentThread().getName(), 1);

                    try {
                        c.await();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
            });
        }

        for (Map.Entry<String, Integer> sheet : sheetCount.entrySet()) {
            result += sheet.getValue();
            System.out.println(sheet.getKey());
        }
        System.out.println(result);

    }

    public static void main(String[] args) {
       CountDownLatchTest countDownLatchTest = new CountDownLatchTest();
       countDownLatchTest.count();
    }


}
