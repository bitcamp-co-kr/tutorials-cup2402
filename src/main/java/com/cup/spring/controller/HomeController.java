package com.cup.spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

// 요청 URL 을 받는 클래스
// 세부 주소에 따라 실행될 메서드들을 정의
// 역할 - 애노테이션을 붙여서 설정 자동화

@Controller
public class HomeController {

	// HTTP request get : 쿼리스트링으로 데이터를 보낸다. (목적 페이지 보여줘라)
	@GetMapping("/")
	public String home() {
		
		// 템플릿엔진이 반환하는 이름.html 을 찾는다.
		return "index"; //src/respources/templates/index.html
	}

}
