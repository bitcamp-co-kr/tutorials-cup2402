package com.cup.spring.webmvc;

import java.io.IOException;

import org.springframework.util.AntPathMatcher;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import com.cup.spring.service.JWTFactory;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ApiRequestFilter extends OncePerRequestFilter {
	
	private AntPathMatcher antPathMatcher;
	private String urlpattern;
	private JWTFactory jwt;

	public ApiRequestFilter(String url) {
		this.antPathMatcher = new AntPathMatcher();
		this.urlpattern = url;
	}
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// 요청 시 직접 URL 을 확인하는 코드를 작성
		if(antPathMatcher.match(urlpattern, request.getRequestURI())) {		
			log.info("ApiRequestFilter ---- ");
			
			if( checkAuthHeader(request) ) {
				filterChain.doFilter(request, response);
			}
			log.info(" ---- ApiRequestFilter");
			return;
		}
		filterChain.doFilter(request, response);
	}

	private boolean checkAuthHeader(HttpServletRequest request) {
		boolean checkResult = false;

		String authHeader = request.getHeader("Authorization");
		if( StringUtils.hasText(authHeader) && authHeader.startsWith("Bearer ")) {
			log.info("{}", authHeader);
			try {
				String content = jwt.validateAndExtract(authHeader.substring(7));
				log.info("{}", content);
				checkResult = content.contains("servicekey");
			}catch (Exception e) {
				// TODO: handle exception
				log.error("{}", e.getMessage());
			}
		}
		return checkResult;
	}
}
