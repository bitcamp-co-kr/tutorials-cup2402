package com.cup.spring.repository;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;


@Builder
@Getter
// JPA
@Entity
public class ShopInfo {
	
	// 반드시 PK 지정을 해야 한다. 각 data 마다 고유한 값이 들어가야 한다.(중복 금지 : unique)
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long siPk;	// 식별자, Primary key(PK)

	@Column(nullable = false)
	private String name;
	@Column(nullable = true)
	private String address;
	@Column
	private String phone;
	@Column
	private String email;
	@Column
	private String workday;
	

}
