package com.cup.spring.zombie.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ZombieRepository extends JpaRepository<ZombieRecord, Long>{

}
