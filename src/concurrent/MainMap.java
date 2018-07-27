package concurrent;


import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @auther: WJoe
 * @Description:
 * @Date : 10:50 2018/7/18
 */
public class MainMap {


    Map<String,String> map = new HashMap<>();


    Map<String,String> concurrentMao = new ConcurrentHashMap<>();

    public static void main(String[] args) {
        List<String> strings = Arrays.asList("6", "1", "3", "1","2");
        Collections.sort(strings);
    }


}
