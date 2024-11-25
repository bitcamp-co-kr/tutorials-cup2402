package com.cup.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// DB 와 통신하는 역할
//	인터페이스는 구현체가 있어야 한다...
//	JPA 가 이 인터페이스의 구현체를 자동 생성한다.
//	- entity : DB table 과 1:1 매칭
//		필드   :    컬럼(column)

@Repository
public interface ShopInfoRepository extends JpaRepository<ShopInfo, Long> {

	// 기본적으로 생성되는 메서드 외에 추가로 메서드를 선언할 수 있다.
	
}
