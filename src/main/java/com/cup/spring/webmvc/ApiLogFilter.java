package com.cup.spring.webmvc;

import java.io.IOException;

import org.springframework.stereotype.Component;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ApiLogFilter implements Filter {
	// 연결된 URL 이 뭔지를 찾아봐야 한다.
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		log.info("apiLogFilter");
		// 다음 넘어가기
//		chain.doFilter(request, response);
		
	}

}
