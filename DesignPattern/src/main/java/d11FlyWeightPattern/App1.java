package d11FlyWeightPattern;

import org.junit.Test;

/**
 * 享元模式
 */
public class App1 {
    @Test
    public void test() throws Exception {
        MyInteger i1=MyInteger.valueOf(22);
        MyInteger i2=MyInteger.valueOf(22);
        System.out.println(i1==i2);

        MyInteger i3=MyInteger.valueOf(129);
        MyInteger i4=MyInteger.valueOf(129);
        System.out.println(i3.equals(i4));

    }
}
class MyInteger{

    private int value;

    public MyInteger(int value) {
        this.value = value;
    }

    private static MyInteger[] myIntegersCache= new MyInteger[256];
    static {
        //缓存从-128~127之间的整形
        for (int i = 0; i < myIntegersCache.length; i++) {
            myIntegersCache[i] = new MyInteger(i - 128);
        }
    }


    public static MyInteger valueOf(int val){
        if(val>=-128&&val<=127){
            return myIntegersCache[val+128];
        }
        return new MyInteger(val);
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof  MyInteger){
            MyInteger other = (MyInteger) obj;
            return other.value==this.value;
        }
        return super.equals(obj);
    }
}
