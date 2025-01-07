package com.cup.spring.webmvc;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

// 컨트롤러 실행 전, 후 Spring AOP
@Slf4j
@Component
public class ControllerLogInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("==================== BEGIN ====================");
        log.info("Request URI ==> " + request.getRequestURI());
        // 직접 URL 를 선별 확인해서 처리하는 코드
        return HandlerInterceptor.super.preHandle(request, response, handler);
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
    	log.info("Response status ==> {}", response.getStatus());
        log.info("==================== END ======================");
        // 뷰 실행 전, 에러 발생 시 사용자에게 보여줄 정보
       	HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
        log.info("==================== Completed ======================");
        // 응답 전
		HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
	}
    
    

}