package core;

import result.FileResult;
import result.JspResult;
import util.URLMatchUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@WebServlet("*.do")
public class DispatcherServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String requestURI = req.getRequestURI();
        System.out.println("请求的地址为:" + requestURI);
        String controllerAndMethod = URLMatchUtil.resolveURL(requestURI);

        Map<String, String[]> parameterMap = req.getParameterMap();
        System.out.println("请求所携带的参数为：");
        parameterMap.forEach((k,v) -> System.out.println(k.toString()+":"+v.toString()));

        //根据Url路径去匹配对应的Controller


        //执行对应的Controller的对应的method

        //执行完毕 返回结果








//        req.getRequestDispatcher("index.jsp").forward(req,resp);
//        req.getRequestDispatcher("index.jsp").forward(req,resp);
//        Map<String,String> data=new HashMap<>();
//        data.put("a","aaa");
//        data.put("b","bbb");
//        data.put("c","ccc");
//        new AjaxResult(data).execute(req,resp);

        JspResult jspResult = new JspResult("/WEB-INF/index.jsp");
        jspResult.putAttrbuite("name","wyf");
        jspResult.putAttrbuite("age",18);
        jspResult.execute(req,resp);

        //new FileResult("D:\\java视频\\第九套\\8、互联网分布式综合项目实战\\单点登录23.mp4").execute(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
