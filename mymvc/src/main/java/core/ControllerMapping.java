package core;

import java.lang.reflect.Method;
import java.util.HashMap;

public class ControllerMapping {

    public ControllerMapping(){}

    private static ControllerMapping instance = new ControllerMapping();

    public static ControllerMapping getInstance() {
        return instance;
    }

    private static HashMap<String,Method> controllerAndMethods =new HashMap<>();

    /**
     * 将一个Action的方法注册进来
     * @param controllerName 控制器的名字
     * @param methodName 方法的名字
     * @param method 对应的方法
     */
    public void registeAction(String controllerName,String methodName,Method method) {
        controllerAndMethods.put(controllerName+"#"+methodName,method);
    }

    /**
     * 将一个Controller的方法注册进来
     * @param controllerNameAndMethodName  如UserController#add
     * @param method
     */
    public void registeAction(String controllerNameAndMethodName,Method method) {
        controllerAndMethods.put(controllerNameAndMethodName,method);
    }

    /**
     * 获取具体的方法实现
     * @param controllerName 控制器的名字
     * @param methodName 方法名字
     * @return
     */
    public Method getMethod(String controllerName,String methodName){
        return controllerAndMethods.get(controllerName+"#"+methodName);
    }

    /**
     * 传入控制器及其对应方法的名称 如UserController#add
     * @param controllerNameAndMethodName
     * @return
     */
    public Method getMethod(String controllerNameAndMethodName){
        return controllerAndMethods.get(controllerNameAndMethodName);
    }
}
