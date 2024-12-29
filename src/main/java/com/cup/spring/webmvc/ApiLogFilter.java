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
//@Component
//@WebFilter(urlPatterns="/")
//@Order(1)	// 여러 개의 필터 등록 시 순서 지정 가능, 안하면 마지막 순서
public class ApiLogFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		log.info("doFilter -- entering -------------------");
		chain.doFilter(request, response);
		log.info("doFilter -- outtering -------------------");
	}

}
