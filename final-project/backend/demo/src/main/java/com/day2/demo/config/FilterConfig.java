package com.day2.demo.config;

import com.day2.demo.filter.RequestLogFilter;
import com.day2.demo.filter.RequestLogOncePerRequestFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfig {

//    @Bean // methodun adı ile bean oluşturulur!
//    public FilterRegistrationBean<RequestLogFilter> filterRegistrationBean() {
//        var bean = new FilterRegistrationBean<RequestLogFilter>();
//        bean.setOrder(1);
//        bean.setFilter(new RequestLogFilter());
//        bean.addUrlPatterns("/users/*");
//        return bean;
//    }
//
//    @Bean
//    public FilterRegistrationBean<RequestLogOncePerRequestFilter> oncePerRequestLogFilter(){
//        var bean = new FilterRegistrationBean<RequestLogOncePerRequestFilter>();
//        bean.setOrder(100);
//        bean.setFilter(new RequestLogOncePerRequestFilter());
//        bean.addUrlPatterns("/*");
//        return bean;
//    }
}
