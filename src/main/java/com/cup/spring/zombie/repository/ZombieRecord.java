package com.cup.spring.zombie.repository;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Builder;

@Entity
@Builder
public class ZombieRecord {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long pk;
	private String player_name;
	private int survival_time;

}
