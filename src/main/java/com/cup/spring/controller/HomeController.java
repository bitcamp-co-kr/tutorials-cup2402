package com.cup.spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;	// spring 에서 자동으로 생성, 관리, 주입
import org.springframework.web.bind.annotation.GetMapping;

import com.cup.spring.dto.ServiceDTO;
import com.cup.spring.service.ShopInfoService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

// 요청 URL 을 받는 클래스
// 세부 주소에 따라 실행될 메서드들을 정의
// 역할 - 애노테이션을 붙여서 설정 자동화
// 모든 컨트롤러에는 로깅(logging) 기능을 추가해라 : 레벨별로 출력을 선택할 수 있다.
@Slf4j
@RequiredArgsConstructor
@Controller
public class HomeController {
	// final : 한 번 초기화, 이후 수정은 하지 않는다.
	//	반드시 초기화를 해야 하는 필드 : 스프링에서 자동으로 초기화한 객체를 생성하고
	//	필요한 곳에 넣어준다.(의존성을 주입한다. DI )
	private final ShopInfoService shopinfoService;
	
//	public HomeController(ShopInfoService shopinfoService) {
//		super();
//		this.shopinfoService = shopinfoService;
//	}
	
	// HTTP request get : 쿼리스트링으로 데이터를 보낸다. (목적 페이지 보여줘라)
	// http://사이트주소, http://사이트주소/ 
	@GetMapping({ "", "/", "/index" })
	public String home( Model model) {
		log.info("home: {}", "request :/index");

		// 지정된 URL 에 대한 처리를 진행
		
		// 전달할 data 를 Model 에 담기: Map
		model.addAttribute("serviceName", "CUPshop"); // key : value 형식 데이터 모음
		// 서비스에게 처리를 위임하고 그 결과를 응답한다.
		model.addAttribute("service", shopinfoService.getServiceInfo());

		// 템플릿엔진이 반환하는 이름.html 을 찾는다.
		return "index"; // src/main/resources/templates/index.html
	}


}
