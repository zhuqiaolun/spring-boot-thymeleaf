package com.demon.springbootthymeleaf.security;

import com.demon.springbootthymeleaf.support.ControllerTools;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @ClassName: MyAccessDecisionManager
 * @Description: 处理无权请求
 * @Author: Demon
 * @Date: 2020/7/20 14:10
 */
@Slf4j
@Component
public class MyAccessDecisionManager implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        boolean isAjax = ControllerTools.isAjaxRequest(request);
        System.out.println("CustomAccessDeniedHandler handle");
        if (!response.isCommitted()) {
            if (isAjax) {
                String msg = accessDeniedException.getMessage();
                log.info("accessDeniedException.message:" + msg);
                String accessDenyMsg = "{\"code\":\"403\",\"msg\":\"没有权限\"}";
                ControllerTools.print(response, accessDenyMsg);
            } else {
                request.setAttribute(WebAttributes.ACCESS_DENIED_403, accessDeniedException);
                response.setStatus(HttpStatus.FORBIDDEN.value());
                RequestDispatcher dispatcher = request.getRequestDispatcher("/403");
                dispatcher.forward(request, response);
            }
        }
    }

}
