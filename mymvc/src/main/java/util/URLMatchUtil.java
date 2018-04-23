package util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * URL解析类
 * eg：将对应的请求/User/add 解析成UserController#add
 */
public class URLMatchUtil {

    private static final String URL_MATCH_PATTERN = "/(.+?)/(.+?)\\.do";

    private String controllerName;
    private String methodName;

    public String getControllerName() {
        return controllerName;
    }

    public void setControllerName(String controllerName) {
        this.controllerName = controllerName;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public URLMatchUtil(String url) {
        Pattern compile = Pattern.compile(URL_MATCH_PATTERN);
        Matcher matcher = compile.matcher(url);
        if (!matcher.find()) {
            throw new RuntimeException("url:" + url + "，不是一个标准的可以被解析的url");
        }
        String controllerName = matcher.group(1);
        String methodName = matcher.group(2);

        this.controllerName = controllerName;
        this.methodName = methodName;
    }

    public String getControllerAndMethodName() {
        return controllerName + "Controller#" + methodName;
    }
}
