package com.cup.spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

// http://localhost:8080/blog/list
@RequestMapping("/blog")
@Controller
public class BlogController {

	// 브라우저 -> 서버 : Query String : 사용자가 데이터를 볼 수 있다.
	@GetMapping("/list")
	public String getList( Model model ) {
		// 스프링 프레임워크 Model 객체를 생성 관리 - 공유를 하도록 지원
		// 블로그에 등록된 게시글의 목록 반환
		
		model.addAttribute("name", "Mario"); // name = "Mario"
		
		// blog-list.html
		return "blog-list";
	}
	@GetMapping("/post")
	public String getPost( String title, Model model ) {
		// 스프링 프레임워크 Model 객체를 생성 관리 - 공유를 하도록 지원
		// 블로그에 등록된 게시글의 목록 반환
		
		model.addAttribute("name", title);
		
		// blog-list.html
		return "blog-list";
	}
	
	// 브라우저 -> 서버 : body : 사용자가 직접 볼 수 없다.
	@PostMapping("/edit")
	public String editPost(String content, Model model) {
		// 게시글 새로 등록(저장)하기
		
		model.addAttribute("name", content);
		return "blog-list";// .html
	}

}
