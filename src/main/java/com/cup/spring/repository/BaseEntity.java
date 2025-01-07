package com.cup.spring.repository;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;

/**
 * 모든 테이블 공통되는 컬럼 정의
 * - 이 클래스와 하위/자식 클래스를 합쳐서 하나의 테이블
 * - JPA 지원하는 기능 사용
 * -	인터페이스, 추상클래스는 미완성 상태 : 자체만으로 실행될 수 없다.
 * -	실행: 클래스, 인스턴스 생성 가능한 완성 상태여야 한다.
 */
@MappedSuperclass
@EntityListeners(value = { AuditingEntityListener.class })
@Getter
public abstract class BaseEntity {

	@CreatedDate
	@Column(updatable = false)
	private LocalDateTime createdDate;// created_date
	@LastModifiedDate
	private LocalDateTime modifiedDate;// modified_date
	
	private LocalDateTime deletedDate;
	private boolean active; // 사용 여부 설정
	private boolean withdraw; // 삭제 여부 설정
	// 이 클래스를 상속받은 자식클래스도 private 멤버 접근 할 수 없다.
	//	자식 클래스에서 직접 접근할 수 있게 하려면, protected 설정해야 한다.
	
	public void disable() {
		this.active = false;
	}
	public void enable() {
		this.active = true;
	}
}
