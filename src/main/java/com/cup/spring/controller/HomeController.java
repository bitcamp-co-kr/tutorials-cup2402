package com.cup.spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

// 요청 URL 을 받는 클래스
// 세부 주소에 따라 실행될 메서드들을 정의
// 역할 - 애노테이션을 붙여서 설정 자동화

@Controller
public class HomeController {

	@RequestMapping("/")
	public String home() {
		
		// 템플릿엔진이 반환하는 이름.html 을 찾는다.
		return "home";// home.html 안에 처리된 데이터들을 담아서 최종 html 응답을 클라이언트에게 보낸다.
		// thymleaf : src/main/resources/templates/
	}
	@RequestMapping("/member")
	public String member() {
		
		// 템플릿엔진이 반환하는 이름.html 을 찾는다.
		return "home";// home.html 안에 처리된 데이터들을 담아서 최종 html 응답을 클라이언트에게 보낸다.
		// thymleaf : src/main/resources/templates/
	}

}
