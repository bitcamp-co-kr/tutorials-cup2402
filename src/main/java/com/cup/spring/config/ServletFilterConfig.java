package com.cup.spring.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.cup.spring.webmvc.ApiLogFilter;
import com.cup.spring.webmvc.ApiRequestFilter;
import com.cup.spring.webmvc.ControllerLogInterceptor;

// 스프링 기반 개발 시 설정용 클래스
@Configuration
public class ServletFilterConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new ControllerLogInterceptor());
    }

    @Bean
    public FilterRegistrationBean<ApiLogFilter> firstFilter(){
        // 객체 생성하는 클래스 : factory
    	// 외부에서 URL 설정하고 생성
    	FilterRegistrationBean<ApiLogFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new ApiLogFilter());
        registrationBean.addUrlPatterns("/api/v1/*");
        registrationBean.setOrder(1);
        registrationBean.setName("api-log-filter");
        return registrationBean;
    }

    @Bean
    public ApiRequestFilter apiRequestFilter() {
    	// Filter 구현 클래스에서 내부적으로 URL 을 확인하도록 함.
    	return new ApiRequestFilter("/api/v2/*");
    }
    
}
