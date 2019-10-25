package com.bjq.security.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
                                       //WebMvcConfigurerAdapter
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers("/").permitAll()
                .antMatchers("/level1/**").hasRole("VIP1")
                .antMatchers("/level2/**").hasRole("VIP2")
                .antMatchers("/level3/**").hasRole("VIP3");

        //开启自动配置得登陆功能,效果,如果没有登陆权限就会来到登陆页面
        http.formLogin().usernameParameter("user").passwordParameter("pwd").loginPage("/userlogin");
        //1,/login来到登陆页面
        //2,重定向到login?error表示登陆失败
        //3,更多详细规定
        //4,默认post形式的/login代表处理登陆
        //5,一但定制loginPage；那么loginPage的post的请求就是登陆

        //开启自动配置的注销功能
        http.logout().logoutSuccessUrl("/");
        //1,访问/logout表示用户退出,清空session
        //2,注销成功会返回/login?logout页面
        //我们可以改变它的url地址使用.logoutSuccessUrl("/")来到首页


        //开启记住我功能
        http.rememberMe().rememberMeParameter("remember");
        //登陆成功以后，将cookie发给浏览器保存,以后访问页面带上这个cookie,只要通过检查就可以免登录默认有14天的保存时间
        //点击注销会删除cookie
    }

    //定义认证规则
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth

                .inMemoryAuthentication()
                .passwordEncoder(new MyPasswordEncoder()).withUser("root").password("root").roles("VIP1","VIP2").and()
                .withUser("admin").password("admin").roles("VIP2","VIP3")
                .and()
                .withUser("sum").password("sum").roles("VIP1","VIP3");
    }
}
