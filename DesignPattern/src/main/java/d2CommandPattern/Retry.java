package d2CommandPattern;

/**
 * Author: wangyufei
 * CreateTime:2018/03/04
 * Companion:Champion Software
 */
public class Retry {

    public static boolean tryRun(int maxTimes,RetryAble retryAble) {
        for (int i = 0; i < maxTimes; i++) {
            try {
                retryAble.run();
                return true;//可以执行到这里说明执行没有问题
            } catch (Exception e) {
                System.out.println("执行第"+(i+1)+"次，发生异常"+e);
            }
        }
        return false;
    }
}
