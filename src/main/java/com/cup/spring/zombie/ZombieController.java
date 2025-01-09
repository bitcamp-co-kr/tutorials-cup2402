package com.cup.spring.zombie;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cup.spring.zombie.repository.ZombieRecord;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequiredArgsConstructor
public class ZombieController {
	
	private final ZombieService zombieService;
	
	// 기록 저장 API
	@PostMapping("/api/records") 
	public ResponseEntity<ResponseZombie> saveRecords(RequestZombie data, Model model){
	  try {
   
	    if (data.getPlayerName() == null || data.getSurvivalTime()  <= 1 ) {
	      return ResponseEntity.ok().body(ResponseZombie.builder().error("").build());
	    }

	    ZombieRecord records = ZombieRecord.builder()
	    		.player_name(data.getPlayerName())
	    		.survival_time(data.getSurvivalTime())
	    		.build();

	    zombieService.save(records);
	    
	    return ResponseEntity.ok().body(ResponseZombie.builder().success("").build());
	  } catch (Exception e) {
		  log.warn(e.getMessage());
		  return ResponseEntity.internalServerError().build();
	  }
	}

	// 기록 조회 API (상위 10개)
	@GetMapping("/api/records")
	public ResponseEntity<List<ZombieRecord>> listRecord(Model model){
		return ResponseEntity.ok(zombieService.list());
	}


}
