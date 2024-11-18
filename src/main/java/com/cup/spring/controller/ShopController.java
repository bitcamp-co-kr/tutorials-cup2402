package com.cup.spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

// 사이트주소/shop/
@RequestMapping("/shop")
@Controller
public class ShopController {
	
	@GetMapping({"","/"})
	public String list(Model model) {
		// URL 경로에 따라 파일들의 경로를 확인
		return "shop";
	}

	// /shop/views/번호 URL의 위치 기준으로 값을 전달 받는 것
	// PathVariable : 추출, 뽑는다.
	@GetMapping("/views/{id}")
	public String views(@PathVariable("id") String prodid, Model model) {
		System.out.println("views " + prodid);
		// URL 경로에 따라 파일들의 경로를 확인
		return "shop-single";
	}
	

}
