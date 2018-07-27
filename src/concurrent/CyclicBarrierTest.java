package concurrent;

import java.util.Map;
import java.util.concurrent.*;

/**
 * @auther: WJoe
 * @Description: 回环栅栏，通过它可以实现让一组线程等待至某个状态之后再全部同时执行。
 * <p>
 * 叫做栅栏，大概是描述所有线程被栅栏挡住了，当都达到时，一起跳过栅栏执行，也算形象。我们可以把这个状态就叫做barrier
 * 用于多线程计算数据，最后合并计算
 * @Date : 15:47 2018/7/23
 */
public class CyclicBarrierTest implements Runnable {


    private CyclicBarrier c = new CyclicBarrier(4, this);
    private Executor executor = Executors.newFixedThreadPool(4);

    private ConcurrentHashMap<String ,Integer> sheetCount = new ConcurrentHashMap<>();

    private  void  count(){
        for (int i = 0; i <4 ; i++) {
            executor.execute(new Runnable() {
                @Override
                public void run() {
                    sheetCount.put(Thread.currentThread().getName(),1);
                    try {
                        c.await();
                    }catch (InterruptedException |BrokenBarrierException e){
                        e.printStackTrace();
                    }

                }
            });

        }

    }




    @Override
    public void run() {
         int result = 0;
        for (Map.Entry<String,Integer> sheet: sheetCount.entrySet()) {
            result +=sheet.getValue();
            System.out.println(sheet.getKey());
        }
        System.out.println(result);
    }

    public static void main(String[] args) {
        CyclicBarrierTest cyclicBarrierTest = new CyclicBarrierTest();
        cyclicBarrierTest.count();
    }
}
