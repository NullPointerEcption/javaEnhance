package d12ProxyPattern.cglib;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class TestCGLibProxy implements MethodInterceptor {

    private Object target;

    public Object getInstance(Object target) {
        this.target = target;
        //创建hancer
        Enhancer enhancer = new Enhancer();
        //设置目标对象为父类
        enhancer.setSuperclass(this.target.getClass());
        // 回调方法
        enhancer.setCallback(this);
        // 创建代理对象
        return enhancer.create();
    }

    /**
     proxy - "this", the enhanced object

     method - intercepted Method

     args - argument array; primitive types are wrapped

     methodProxy - used to invoke super (non-intercepted method); may be called as many times as needed
     */
    @Override
    public Object intercept(Object proxy, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
        System.out.println("==============cglib proxy start========================");
//        Object result = method.invoke(target, args);
        Object result = methodProxy.invokeSuper(proxy, args);//调用目标方法
        System.out.println("==============cglib proxy end========================");
        return result;
    }
}
