package com.demon.springbootthymeleaf.support;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @ClassName: ControllerTools
 * @Description: 判断是否 ajax 请求
 * @Author: Demon
 * @Date: 2020/7/20 14:52
 */
public class ControllerTools {

    public static boolean isAjaxRequest(HttpServletRequest request) {
        return "XMLHttpRequest".equals(request.getHeader("X-Requested-With"));
    }

    public static void print(HttpServletResponse response, String msg) throws IOException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        PrintWriter writer = response.getWriter();
        writer.write(msg);
        writer.flush();
        writer.close();
    }
}
