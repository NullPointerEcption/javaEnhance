package d12ProxyPattern.jdkproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class TestJDKProxy implements InvocationHandler {

    private Object target;

    public Object bind(Object target) {
        this.target = target;
        return Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        System.out.println("============proxy====start=============");
        if (method.equals(ITest.class.getDeclaredMethod("test2", String.class))) {
            if (args[0] == null || args[0].toString().contains(" ")) {
                throw new RuntimeException("the method test2 cannot pass  null or empty string  as parameter");
            }
        }
        Object result = method.invoke(target, args);
        System.out.println("============proxy=====end============");
        return result;
    }
}
