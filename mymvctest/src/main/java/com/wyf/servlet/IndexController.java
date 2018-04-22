package com.wyf.servlet;

import anno.Controller;
import result.BaseResult;
import result.JspResult;

@Controller
public class IndexController {

    public BaseResult index() throws Exception {
        return new JspResult("/WEB-INF/index1.jsp");
    }

}
