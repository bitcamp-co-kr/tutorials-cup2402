package com.cup.spring.service;

import com.cup.spring.dto.ServiceDTO;

// 스프링은 여러분의 코드를 인터페이스 문법으로 설정하려고 한다.
// 나는 이런 메서드를 가진 클래스를 만들어서 스프링에 포함시키고 싶다.
// 인터페이스 구현체를 찾는다(검색)
public interface ShopInfoService {
	
	public ServiceDTO getServiceInfo();

	public void updateInfo(ServiceDTO dto);

}
