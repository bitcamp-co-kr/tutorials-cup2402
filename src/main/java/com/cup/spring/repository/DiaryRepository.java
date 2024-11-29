package com.cup.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cup.spring.model.DiaryEntry;

//public class DiaryRepository {
//
//}
// JPA 를 사용해서 DB 접근을 하는 코드는 JPA 에 미리 정해진 방식의 코드가 생성 자동으로 됨
@Repository
public interface DiaryRepository extends JpaRepository<DiaryEntry, Long>{

}
