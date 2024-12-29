package com.cup.spring.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UploadResultDTO {

	private String uuid;
	private String filename;
	private boolean img;
	
	public String getUrl() {
		return uuid+"_"+filename;
	}
}
