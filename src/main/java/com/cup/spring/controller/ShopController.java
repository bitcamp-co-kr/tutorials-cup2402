package com.cup.spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cup.spring.dto.ServiceDTO;
import com.cup.spring.service.ShopInfoService;
import com.cup.spring.service.ShopProductService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

// 사이트주소/shop/
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/shop")
@Controller
public class ShopController {

	// 스프링 컨테이너로부터 받는다 DI
	private final ShopInfoService shopinfoService;
	private final ShopProductService productService;

	
	@GetMapping({"","/"})
	public String list(Model model) {
		// URL 경로에 따라 파일들의 경로를 확인
		model.addAttribute("serviceName", "CUPshop"); // key : value 형식 데이터 모음
		model.addAttribute("service", shopinfoService.getServiceInfo());
		
		model.addAttribute("productList", productService.getAll());
		
		return "shop";
	}

	// /shop/views/번호 URL의 위치 기준으로 값을 전달 받는 것
	// PathVariable : 추출, 뽑는다.
	@GetMapping("/views/{id}")
	public String views(@PathVariable("id") String prodid, Model model) {
		System.out.println("views " + prodid);
		// URL 경로에 따라 파일들의 경로를 확인
		model.addAttribute("serviceName", "CUPshop"); // key : value 형식 데이터 모음
		model.addAttribute("service", shopinfoService.getServiceInfo());
		return "shop-single";
	}
	

}
