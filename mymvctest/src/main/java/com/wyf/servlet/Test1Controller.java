package com.wyf.servlet;

import anno.WyfController;
import result.AjaxResult;
import result.BaseResult;

@WyfController
public class Test1Controller {

    public BaseResult test() throws Exception {
        System.out.println("test1执行了。。。");
        return new AjaxResult(123);
    }
}
