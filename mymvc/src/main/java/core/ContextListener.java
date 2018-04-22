package core;

import com.google.common.collect.ImmutableSet;
import com.google.common.reflect.ClassPath;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.io.IOException;

/**
 * 上下文监听器 当容器一启动 九江指定包下的所有Controller 并且方法返回值为BaseResult 类型的方法进行管理
 *
 */
@WebListener
public class ContextListener implements ServletContextListener{

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        String basePkg = sce.getServletContext().getInitParameter("controller.basepackage");
        ClassLoader contextClassLoader = Thread.currentThread().getContextClassLoader();
        try {
            ClassPath classPath = ClassPath.from(contextClassLoader);
            ImmutableSet<ClassPath.ClassInfo> topLevelClasses = classPath.getTopLevelClasses(basePkg);
            topLevelClasses.forEach(clz->{
               registeIntoControllerMap(clz);
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void registeIntoControllerMap(ClassPath.ClassInfo clz) {
        String controllerName = clz.getSimpleName();


    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }
}
