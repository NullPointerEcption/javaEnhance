package d5StragetyPattern;

import org.junit.Test;

import java.io.File;
import java.io.FileFilter;

/**
 * Author: wangyufei
 * CreateTime:2018/03/04
 * Companion:Champion Software
 */
public class App4 {

    @Test
    public void method() {
        File file=new File("C:/");
        for (String s : file.list((d,f)->f.endsWith(".sys"))) {
            System.out.println(s);
        }

    }
}
