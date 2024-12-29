package com.cup.spring.dto;

import lombok.Builder;
import lombok.Data;

// 데이터를 교환하기 위한 type
// 프론트와 백엔드 간...
// 서비스 간 ...
@Builder
@Data
public class ServiceDTO {

	private String name;
	private String address;
	private String phone;
	private String email;
	
	// private 필드는 직접 접근 할 수 없다
	// method 를 통해 읽기,쓰기
	

}
