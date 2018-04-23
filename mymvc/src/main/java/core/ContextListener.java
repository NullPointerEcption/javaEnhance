package core;

import anno.WyfController;
import com.google.common.collect.ImmutableSet;
import com.google.common.reflect.ClassPath;
import converter.BooleanConverter;
import converter.IntConverter;
import converter.LongConverter;
import converter.StringConvert;
import result.BaseResult;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.io.IOException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

/**
 * 上下文监听器 当容器一启动 九江指定包下的所有Controller 并且方法返回值为BaseResult 类型的方法进行管理
 */
@WebListener
public class ContextListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {

        //region 加载所有的Controller
        String basePkg = sce.getServletContext().getInitParameter("controller.basepackage");
        ClassLoader contextClassLoader = Thread.currentThread().getContextClassLoader();
        try {
            ClassPath classPath = ClassPath.from(contextClassLoader);
            ImmutableSet<ClassPath.ClassInfo> topLevelClasses = classPath.getTopLevelClasses(basePkg);
            topLevelClasses.forEach(clz -> {
                registeIntoControllerMap(clz);
            });
        } catch (IOException e) {
            throw new RuntimeException("解析" + basePkg + "包下的Controller出现异常：" + e);
        }
        //endregion

        //region 注册并管理所有的Converter
        TypeConvertManager.getInstance().registerConverter(String.class,new StringConvert())
        .registerConverter(int.class,new IntConverter())
        .registerConverter(Integer.class,new IntConverter())
        .registerConverter(long.class,new LongConverter())
        .registerConverter(Long.class,new LongConverter())
        .registerConverter(boolean.class,new BooleanConverter())
        .registerConverter(Boolean.class,new BooleanConverter());
        //endregion

    }

    private void registeIntoControllerMap(ClassPath.ClassInfo clz) {
        //获得Controller的完全限定名称
        String controllerName = clz.getName();
        //获取修饰符为public的类
        //获取以Controller结尾的类
        //获得每个标记了@Controller注解的类 并获得其中所有返回值类型为BaseResult类型的方法
        Class<?> controllerClass = clz.load();
        WyfController hashWyfControllerAnno = controllerClass.getAnnotation(WyfController.class);
        if (!Modifier.isPublic(controllerClass.getModifiers())
                || !controllerName.endsWith("Controller")
                || hashWyfControllerAnno == null) {
            return;
        }

        Method[] controllerMethods = controllerClass.getMethods();
        for (int i = 0; i < controllerMethods.length; i++) {
            //方法是public的 并且返回值类型是BaseResult的
            if (Modifier.isPublic(controllerMethods[i].getModifiers())
                    && BaseResult.class.isAssignableFrom(controllerMethods[i].getReturnType())) {
                String controllerNameAndMethodName = clz.getSimpleName() + "#" + controllerMethods[i].getName();
                ControllerMapping.getInstance().registeAction(controllerNameAndMethodName, controllerMethods[i]);
            }
        }


    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }
}
