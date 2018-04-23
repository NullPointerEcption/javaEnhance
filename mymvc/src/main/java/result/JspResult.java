package result;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 返回结果jsp
 */
public class JspResult implements BaseResult {

    private String jspPath;
    private Map<String, Object> model;


    /**
     * 默认的不带任何参数的JspResult
     */
    public JspResult() {
        this.model = new HashMap<>();
    }

    /**
     * 直接跳转，不附加数据
     *
     * @param jspPath 路径
     */
    public JspResult(String jspPath) {
        this.jspPath = jspPath;
        this.model = new HashMap<>();
    }


    /**
     * 跳转到指定jsp 并附加model
     *
     * @param jspPath
     * @param model
     */
    public JspResult(String jspPath, Map<String, Object> model) {
        this.jspPath = jspPath;
        this.model = model;
    }

    public String getJspPath() {
        return jspPath;
    }

    public JspResult setJspPath(String jspPath) {
        this.jspPath = jspPath;
        return this;
    }

    /**
     * 存入数据到Attribute中
     *
     * @param key   键
     * @param value 值
     */
    public void putAttrbuite(String key, Object value) {
        this.model.put(key, value);
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        if (jspPath==null||!jspPath.endsWith(".jsp")) {
            throw new RuntimeException("你可能跳转的不是jsp页面，而是:" + jspPath);
        }

        //放入数据
        model.forEach(
                (k, v) -> request.setAttribute(k, v)
        );

        try {
            request.getRequestDispatcher(jspPath).forward(request, response);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }
}
