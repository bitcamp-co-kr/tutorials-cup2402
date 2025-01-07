package com.cup.spring.controller;

// 코드를 복사해올때는 import 도 확인해서 가져오기
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.cup.spring.dto.ServiceDTO;
import com.cup.spring.service.ShopInfoService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/manager")
@Controller
public class AdminController {
	// 실제로 사용하려는 객체(인스턴스)들을 스프링에서 생성, 관리한다.
	private final ShopInfoService shopinfoService;
	
	@GetMapping({"","/"})
	public String manager( Model model) {
		log.info("manage : {}", "for manager");
		// 1. 사용자 확인 : 로그인 했냐 안했냐
		// 2. 권한이 있냐

		// 사이트의 연락처 정보를 화면에 보여주기 위해
		ServiceDTO info = shopinfoService.getServiceInfo();
		model.addAttribute("service", info);
		
		return "manager/index";//.html
	}

	@GetMapping("/prod/add")
	public String addProduct(
			Model model
			) {		
		return "manager/product-add";
	}
	@PostMapping("/prod/add")
	public String addProduct(
			
			) {		
		return "manager/product-add";
	}

	@GetMapping("/info/edit")
	public String editInfo(
			ServiceDTO dto
			) {
//		log.info("edit info : {} {} {} {}", name, email, phone, address);
		log.info("edit info : {}", dto);

		// 전달받은 값으로 변경 처리
		shopinfoService.updateInfo(dto);
		
		return "redirect:/manager"; // html 이 아니고 URL을 써준다.
	}

}
