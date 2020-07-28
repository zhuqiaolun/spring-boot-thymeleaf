package com.demon.springbootthymeleaf.security;

import com.demon.springbootthymeleaf.util.IpUtil;
import eu.bitwalker.useragentutils.UserAgent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @ClassName: MyBasicAuthenticationFilter
 * @Description:
 * @Author: Demon
 * @Date: 2020/7/20 14:46
 */
@Slf4j
public class MyBasicAuthenticationFilter extends BasicAuthenticationFilter {

    MyBasicAuthenticationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain chain) throws ServletException, IOException {
        System.out.println("进入 MyBasicAuthenticationFilter···");
        try {
            /* 打印请求的内容获取请求头中的User-Agent */
            UserAgent userAgent = UserAgent.parseUserAgentString(request.getHeader("User-Agent"));
            log.info("请求URL:{}" , request.getRequestURL().toString());
            log.info("请求浏览器:{}", userAgent.getBrowser().toString());
            log.info("请求浏览器版本:{}",userAgent.getBrowserVersion());
            log.info("请求操作系统:{}", userAgent.getOperatingSystem().toString());
            log.info("请求访问IP:{}" , IpUtil.getIpAddr(request));
            log.info("请求类型:{}", request.getMethod());
            //放行
            chain.doFilter(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus(HttpStatus.NOT_FOUND.value());
            RequestDispatcher dispatcher = request.getRequestDispatcher("/404");
            dispatcher.forward(request, response);
        }
    }

}
