package com.cup.spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cup.spring.dto.ServiceDTO;
import com.cup.spring.service.ShopInfoService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

// 특정 URL 이 클래스로 연결이 되도록 설정 : 자동화
// http://localhost:8080/about/*
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/about")
@Controller
public class AboutController {

	// 스프링 컨테이너로부터 받는다 DI
	private final ShopInfoService shopinfoService;
	
	// HTTP 요청 method(명령)
	@GetMapping({"", "/"})
	public String about( Model model) {
		// 1. 사용자 확인 : 로그인 했냐 안했냐
		// 2. 권한이 있냐
		
		model.addAttribute("serviceName", "CUPshop"); // key : value 형식 데이터 모음
		model.addAttribute("service", shopinfoService.getServiceInfo());

		return "about";
		// Thymeleaf 사용 : html 파일의 경로를 반환
	}

}
