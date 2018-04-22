package d13SingletonPattern;

/**
 * 懒汉式单例模式终极写法
 */
public class SingleTon {
    private static volatile SingleTon instance;//volatile关键字

    private SingleTon() {
    }

    public static SingleTon getInstance() {
        //double-check
        if (instance == null) {
            //这么做的话，如果系统中同时有很多个线程调用这里因为每次只能有一个线程执行这段代码，所以这里的执行效率很低下
            //解决办法：在调用这段代码之前 判断一下instance的值 如果不为null 则不需要进来了
            synchronized (SingleTon.class) {//这个所对象只要是全局唯一的就可以了
                if (instance == null) {
                    instance = new SingleTon();
                }
            }
        }
        return instance;
    }
}
