package d14FactoryPattern;

import java.nio.charset.Charset;

/**
 * 简单工厂模式
 */
public class EasyFactory {

    public static void main(String[] args) {
        Charset u8 = Charset.forName("UTF-8");
        Charset gbk = Charset.forName("gbk");
        Charset gb2312= Charset.forName("gb2312");
        Charset unicode = Charset.forName("unicode");
        Charset u32= Charset.forName("utf-32");

        System.out.println(u8.getClass());
        System.out.println(gbk.getClass());
        System.out.println(gb2312.getClass());
        System.out.println(unicode.getClass());
        System.out.println(u32.getClass());

    }
}
