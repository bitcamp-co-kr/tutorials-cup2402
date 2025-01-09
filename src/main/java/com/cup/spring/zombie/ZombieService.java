package com.cup.spring.zombie;

import java.util.List;

import com.cup.spring.zombie.repository.ZombieRecord;

public interface ZombieService {

	void save(ZombieRecord records);

	List<ZombieRecord> list();

}
