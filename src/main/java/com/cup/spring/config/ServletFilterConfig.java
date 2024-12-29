package com.cup.spring.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.cup.spring.webmvc.ApiLogFilter;
import com.cup.spring.webmvc.ControllerLogInterceptor;

@Configuration
public class ServletFilterConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new ControllerLogInterceptor());
    }


    @Bean
    public FilterRegistrationBean<ApiLogFilter> firstFilter(){
        FilterRegistrationBean<ApiLogFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new ApiLogFilter());
        registrationBean.addUrlPatterns("/api/*");
        registrationBean.setOrder(1);
        registrationBean.setName("api-log-filter");
        return registrationBean;
    }
}
