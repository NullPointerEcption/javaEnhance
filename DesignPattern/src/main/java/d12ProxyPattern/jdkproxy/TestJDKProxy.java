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
    /**
     proxy - the proxy instance that the method was invoked on

     method - the Method instance corresponding to the interface method invoked on the proxy instance.
         The declaring class of the Method object will be the interface that the method was declared in,
         which may be a superinterface of the proxy interface that the proxy class inherits the method through.

     args - an array of objects containing the values of the arguments
         passed in the method invocation on the proxy instance,
         or null if interface method takes no arguments.
         Arguments of primitive types are wrapped in instances of the appropriate primitive wrapper class,
         such as java.lang.Integer or java.lang.Boolean.
     */
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
