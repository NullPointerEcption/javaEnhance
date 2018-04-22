package core;

import java.lang.reflect.Method;
import java.util.HashMap;

public class ControllerMapping {

    public ControllerMapping(){}

    private static ControllerMapping instance = new ControllerMapping();

    public static ControllerMapping getInstance() {
        return instance;
    }

    private static HashMap<String,Method> ctontrollerAndMethods =new HashMap<>();

    /**
     * 将一个Action的方法注册进来
     * @param controllerName 控制器的名字
     * @param methodName 方法的名字
     * @param method 对应的方法
     */
    public void registeAction(String controllerName,String methodName,Method method) {
        ctontrollerAndMethods.put(controllerName+"#"+methodName,method);
    }

    /**
     * 获取具体的方法实现
     * @param controllerName 控制器的名字
     * @param methodName 方法名字
     * @return
     */
    public Method getMethod(String controllerName,String methodName){
        return ctontrollerAndMethods.get(controllerName+"#"+methodName);
    }
}
