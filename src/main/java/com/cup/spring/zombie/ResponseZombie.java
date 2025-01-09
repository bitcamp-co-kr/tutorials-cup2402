package com.cup.spring.zombie;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ResponseZombie {
	
	private String error;
	private String success;

}
