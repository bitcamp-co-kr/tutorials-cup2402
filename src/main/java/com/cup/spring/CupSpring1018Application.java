package com.cup.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing	// JPA : MappedSuperClass
@SpringBootApplication
public class CupSpring1018Application {

	// 웹 애플리케이션의 시작점
	public static void main(String[] args) {
		// 서버 실행
		// 서버 우리 프로그램 연결
		// URL 어느 함수 실행 연결
		SpringApplication.run(CupSpring1018Application.class, args);
	}

}
