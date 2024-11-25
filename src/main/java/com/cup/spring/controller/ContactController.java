package com.cup.spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cup.spring.dto.ServiceDTO;
import com.cup.spring.service.ShopInfoService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/contact")
@Controller
public class ContactController {
	// 스프링 컨테이너로부터 받는다 DI
	private final ShopInfoService shopinfoService;
	
	@GetMapping({"","/"})
	public String contact( Model model) {
		
		model.addAttribute("serviceName", "CUPshop"); // key : value 형식 데이터 모음
		model.addAttribute("service", shopinfoService.getServiceInfo());

		return "contact";//.html
	}

}
