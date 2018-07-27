package concurrent;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

/**
 * @auther: WJoe
 * @Description:
 * @Date : 12:29 2018/7/25
 */
public class ForkJoinTest extends RecursiveTask<Long> {
    private long start;
    private long end;
    private static final long THRESHOLD = 10000;// 临界值

    public ForkJoinTest(long start, long end) {
        this.start = start;
        this.end = end;
    }

    @Override
    protected Long compute() {
        if (end - start <= 1000) {
            // 不大于临界值直接计算和
            long sum = 0;
            for (long i = start; i <= end; i++) {
                sum += i;
            }
            return sum;
        } else {
            // 大于临界值继续进行拆分子任务
            long mid = (start + end) / 2;

            // 拆分子任务
            ForkJoinTest calculator1 = new ForkJoinTest(start, mid);
            calculator1.fork();

            // 拆分子任务
            ForkJoinTest calculator2 = new ForkJoinTest(mid + 1, end);
            calculator2.fork();

            //合并子任务结果
            return calculator1.join() + calculator2.join();
        }
    }


    public static void main(String[] args) {
        Long start = System.currentTimeMillis();
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        Long sum = forkJoinPool.invoke(new ForkJoinTest(1,1000000000L));
        System.out.println(forkJoinPool.getParallelism());
        System.out.println(forkJoinPool.getPoolSize());
        System.out.println(forkJoinPool.getActiveThreadCount());
        System.out.println(sum);
        Long mid = System.currentTimeMillis();
        System.out.println(mid-start);
        Long summ = 0L;
        for (Long i = 0L; i <= 1000000000L; i++) {
         summ += i;
        }
        System.out.println(summ);
        System.out.println(System.currentTimeMillis()-mid);
    }
}
