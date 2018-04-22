package d2CommandPattern;

/**
 * Author: wangyufei
 * CreateTime:2018/03/04
 * Companion:Champion Software
 * 自定义一个Retry接口，只有一个run方法
 */
public abstract interface RetryAble{

    void run() throws  Exception;
}
