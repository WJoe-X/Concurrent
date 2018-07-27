package JVM;

/**
 * @auther: WJoe
 * @Description:
 * @Date : 22:09 2018/7/18
 */
public class Singleton {

        private static Singleton singleTon = new Singleton();
        public static int count1;
        public static int count2 = 0;

        private   Singleton() {
            count1++;
            count2++;
        }

        public static Singleton getInstance() {
            return singleTon;
        }


        public static void main(String[] args) {
            Singleton singleTon = Singleton.getInstance();
            System.out.println("count1=" + singleTon.count1);
            System.out.println("count2=" + singleTon.count2);
        }

}
