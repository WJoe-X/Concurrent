package JVM;

/**
 * @auther: WJoe
 * @Description:
 * @Date : 21:39 2018/7/18
 */
public class FieldResolution {

    interface  Interface0{
        int A= 0;
    }
    interface  Interface1 extends  Interface0{
        int A=1;
    }

    interface  Interface2{
        int A=2;
    }

    static  class Parent implements  Interface1{
        public  static  int A = 3;
    }

    static  class  sub extends  Parent implements Interface2{
        public  static  int A =4;
    }

    public static void main(String[] args) {
        System.out.println("--------------");
        System.out.println(sub.A);
        System.out.println("--------------");
    }
}
