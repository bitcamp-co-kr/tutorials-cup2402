package com.cup.spring.zombie;

import lombok.Data;

@Data
public class RequestZombie {

	private String player_name;
	private int survival_time;
	
	public String getPlayerName() {
		// TODO Auto-generated method stub
		return this.player_name;
	}

	public int getSurvivalTime() {
		// TODO Auto-generated method stub
		return this.survival_time;
	}

}
