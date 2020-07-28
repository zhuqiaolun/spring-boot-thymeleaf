package com.demon.springbootthymeleaf.security;

import com.demon.springbootthymeleaf.util.MD5Util;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.session.HttpSessionEventPublisher;

import javax.annotation.Resource;

/**
 * @ClassName: WebSecurityConfigurer
 * @Description: 继承 WebSecurityConfigurerAdapter 自定义 Spring Security 配置
 * EnableGlobalMethodSecurity:开启 Spring Security 方法级安全注解 @EnableGlobalMethodSecurity
 * prePostEnabled :决定Spring Security的前注解是否可用 [@PreAuthorize,@PostAuthorize,..]
 * secureEnabled : 决定是否Spring Security的保障注解 [@Secured] 是否可用
 * jsr250Enabled ：决定 JSR-250 annotations 注解[@RolesAllowed..] 是否可用.
 * @Author: Demon
 * @Date: 2020/7/19 18:31
 */
@Configurable
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true,securedEnabled = true,jsr250Enabled = true)
public class WebSecurityConfigurer extends WebSecurityConfigurerAdapter {

    @Resource(name = "userDetailsService")
    private UserDetailsServiceImpl userDetailsService;

    @Resource(name = "myAccessDecisionManager")
    private MyAccessDecisionManager myAccessDecisionManager;

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new MyPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        super.configure(web);
        web.ignoring()
                .antMatchers("/js/**")
                .antMatchers("/css/**")
                .antMatchers("/images/**")
                .antMatchers("/jquery/**")
                .antMatchers("/layui-v2.5.5/**")
                .antMatchers("/login")
        ;
    }

    @SuppressWarnings("SpringElInspection")
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //禁用跨站csrf攻击防御
        http.csrf().disable();
        http.headers().frameOptions().disable();
        //表单配置
        http.formLogin()
                //用户未登录时，访问任何资源都转跳到该路径，即登录页面
                .loginPage("/login")
                //登录表单form中action的地址，也就是处理认证请求的路径
                .loginProcessingUrl("/login/auth")
                //登录表单form中用户名输入框input的name名，不修改的话默认是username
                .usernameParameter("username")
                //form中密码输入框input的name名，不修改的话默认是password
                .passwordParameter("password")
                //登录认证成功后默认转跳的路径
                .defaultSuccessUrl("/main")
                //登录认证失败后默认转跳的路径
                .failureUrl("/login?error=true")
                .and()
                .exceptionHandling()
                //无权限处理器
                .accessDeniedHandler(myAccessDecisionManager)
                .and()
                // 设置登出的路径和登出成功后访问的路径
                .logout().permitAll()
                .and()
                // 设置缓存，1周有效
                .rememberMe().tokenValiditySeconds(86400).key("mySessionKey")
                .and()
                .authorizeRequests()
                //不需要通过登录验证就可以被访问的资源路径
                .antMatchers("/error/**").permitAll()
                //根据账号权限访问
                .antMatchers("/**") .access("@rbacPermission.hasPermission(request, authentication)")
                .anyRequest().authenticated();
        //以下这句就可以控制单个用户只能创建一个session，也就只能在服务器登录一次
        http.
                sessionManagement().maximumSessions(1).maxSessionsPreventsLogin(true);
        http
                //配置拦截器
                .addFilter(new MyBasicAuthenticationFilter(this.authenticationManager()));
    }

    @Bean
    protected HttpSessionEventPublisher httpSessionEventPublisher() {
        return new HttpSessionEventPublisher();
    }


    /**
     * 自定义一个我们自己的加密类，继承PasswordEncoder，实现密码加密和密码匹配方法即可
     */
    class MyPasswordEncoder implements PasswordEncoder {

        @Override
        public boolean matches(CharSequence rawPassword, String encodedPassword) {
            return encodedPassword.equals(MD5Util.encode((String)rawPassword));
        }

        @Override
        public String encode(CharSequence rawPassword) {
            return MD5Util.encode((String)rawPassword);
        }

    }

}
