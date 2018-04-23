package com.wyf.servlet;

import anno.WyfController;
import anno.WyfParam;
import result.BaseResult;
import result.JspResult;

@WyfController
public class IndexController {
    private static String defaultJspPath = "/WEB-INF/Index/index2.jsp";

    public BaseResult index() throws Exception {
        return new JspResult("/WEB-INF/index1.jsp");
    }


    public BaseResult index2() throws Exception {
        JspResult jspResult = new JspResult();
        jspResult.putAttrbuite("result","hello how are you");
        return jspResult;
    }

    public BaseResult index3(@WyfParam("aaa")int i,@WyfParam("bbb") String j) throws Exception {
        JspResult jspResult = new JspResult();
        jspResult.putAttrbuite("result",i+j);
        return jspResult.setJspPath(defaultJspPath);
    }

    public BaseResult index4(int aa,String bb,boolean cc) throws Exception {
        JspResult jspResult = new JspResult();
        jspResult.putAttrbuite("result",aa+bb+cc);
        return jspResult.setJspPath(defaultJspPath);
    }
}
