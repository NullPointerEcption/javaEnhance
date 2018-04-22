package d2CommandPattern;

import com.google.gson.GsonBuilder;
import org.apache.commons.io.IOUtils;
import org.junit.Test;

import java.net.URI;

/**
 * Author: wangyufei
 * CreateTime:2018/03/04
 * Companion:Champion Software
 */
public class App1 {

    @Test
    public void method() {
        boolean res = Retry.tryRun(3, new RetryAble() {
            @Override
            public void run() throws Exception {
                System.out.println("我没问题啊" + ((Object) null).toString());
            }
        });
        System.out.println("执行结果" + (res ? "成功" : "失败"));
    }

    @Test
    public void method2() {
//        Retry.tryRun(3, new RetryAble() {
//            @Override
//            public void run() throws Exception {
//                String content = IOUtils.toString(new URI("https://www.baidu.com"), "UTF-8");
//                System.out.println(content);
//            }
//        });

        Retry.tryRun(3,()->{
            System.out.println(IOUtils.toString(new URI("https://www.bai1du.com"),"UTF-8"));
        });

    }
}
