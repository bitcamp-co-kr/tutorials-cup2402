package com.cup.spring.zombie;

import java.util.List;

import org.springframework.stereotype.Service;

import com.cup.spring.zombie.repository.ZombieRecord;
import com.cup.spring.zombie.repository.ZombieRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class ZombieServiceImpl implements ZombieService{

	private final ZombieRepository zombieRepository;

	@Override
	public void save(ZombieRecord records) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<ZombieRecord> list() {
		return zombieRepository.findAll();
	}

}
