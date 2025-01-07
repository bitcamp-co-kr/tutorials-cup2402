package com.cup.spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;	// spring 에서 자동으로 생성, 관리, 주입
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cup.spring.dto.ServiceDTO;
import com.cup.spring.service.ShopInfoService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * AFrame project 기본 페이지 컨트롤러
 */
@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/aframe")
public class AframeController {	

	@GetMapping("/leejm")
	public String leejm(Model model) {
		return "aframe/leejeongmin";
	}
	
	@GetMapping("/seoju")
	public String seoju(Model model) {
		return "aframe/seojeonguk";
	}
	
	@GetMapping("/gkm")
	public String gkm(Model model) {
		return "aframe/GKM/public/index";
	}
}
