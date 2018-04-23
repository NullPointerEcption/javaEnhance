package core;

import anno.WyfParam;
import converter.interfaces.ITypeConverter;
import result.BaseResult;
import result.JspResult;
import util.CommonUtils;
import util.URLMatchUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Map;

@WebServlet("*.do")
public class DispatcherServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String requestURI = req.getRequestURI();
        System.out.println("请求的地址为:" + requestURI);
        URLMatchUtil urlMatchUtil = new URLMatchUtil(requestURI);
        String controllerAndMethod = urlMatchUtil.getControllerAndMethodName();

        //根据Url路径去匹配对应的Controller
        Method method = ControllerMapping.getInstance().getMethod(controllerAndMethod);
        if (method != null) {

            Map<String, String[]> parameterMap = req.getParameterMap();
            System.out.println("请求所携带的参数为：");
            parameterMap.forEach((k, v) -> System.out.println(k.toString() + ":" + v.toString()));

            //执行对应的Controller的对应的method
            Class<?> controller = method.getDeclaringClass();
            try {
                Object controllerObj = controller.newInstance();
                //执行方法
//                BaseResult result= (BaseResult) method.invoke(controllerObj, null);
                BaseResult result = registeMethodParam(controllerObj, method, req);
                handleResult(result, urlMatchUtil);
                //执行完毕 返回结果
                result.execute(req, resp);
            } catch (InstantiationException | IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                throw new RuntimeException("调用Controller目标方法失败:" + controller.getName() + method.getName(), e);
            }

        } else {
            resp.sendError(404);
        }
    }

    /**
     * 对Controller执行的结果做一些处理 比如设置默认的jsp页面路径
     *
     * @param result
     */
    private void handleResult(BaseResult result, URLMatchUtil urlMatchUtil) {
        if (result instanceof JspResult) {
            if (CommonUtils.isNullOrEmpty(((JspResult) result).getJspPath())) {
                ((JspResult) result).setJspPath("/WEB-INF/" + urlMatchUtil.getControllerName() + "/" + urlMatchUtil.getMethodName() + ".jsp");
            }
        }
    }

    /**
     * 对请求的方法进行参数的绑定
     *
     * @param controllerObj 控制器的实例
     * @param method        请求对应的方法
     * @param req           请求所携带的参数
     * @return
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    private BaseResult registeMethodParam(Object controllerObj, Method method, HttpServletRequest req) throws InvocationTargetException, IllegalAccessException {

        Parameter[] parameters = method.getParameters();//方法上面定义的参数
        Object[] realParams = new Object[parameters.length];//真实传入给方法的参数
        for (int i = 0; i < parameters.length; i++) {
            Parameter paramInfo = parameters[i];

            //1.方法上的请求的参数的名字 可能是WyfParam的value也可能是方法自己的参数的名字
            String paramName, paramValue = "";
            WyfParam wyfParamAnno = paramInfo.getAnnotation(WyfParam.class);
            if (wyfParamAnno != null) {
                paramName = wyfParamAnno.value();
            } else {
                paramName = paramInfo.getName();
            }

            //2.进行参数的绑定
            //2.1获取参数的类型信息
            Class<?> paramType = paramInfo.getType();
            //2.2获取参数的值
            paramValue = req.getParameter(paramName);
            ITypeConverter typeConverter = TypeConvertManager.getInstance().getTypeConverter(paramType);
            Object bindParaValue = typeConverter.convertType(paramValue);
            realParams[i] = bindParaValue;
        }
        return (BaseResult) method.invoke(controllerObj, realParams);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
