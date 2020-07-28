package com.demon.springbootthymeleaf.security;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @ClassName: RbacPermission
 * @Description: RBAC数据模型控制权限，自定义实现 URL 权限控制
 * @Author: Demon
 * @Date: 2020/7/20 10:57
 */
@Component("rbacPermission")
public class RbacPermission {

    private AntPathMatcher antPathMatcher = new AntPathMatcher();

    public boolean hasPermission(HttpServletRequest request, Authentication authentication) {
        Object principal = authentication.getPrincipal();
        boolean hasPermission = false;
        if (principal instanceof UserDetailEntity) {
            // 读取用户所拥有的权限菜单
            List<String> menuUrls = ((UserDetailEntity) principal).getMenuUrls();
            System.out.println(menuUrls);
            System.out.println("请求路径：" + request.getRequestURI());
            for (String url : menuUrls) {
                if (antPathMatcher.match(request.getContextPath()+url, request.getRequestURI())) {
                    hasPermission = true;
                    break;
                }
            }
        }
        return hasPermission;
    }
}
