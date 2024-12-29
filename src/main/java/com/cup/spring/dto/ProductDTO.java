package com.cup.spring.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ProductDTO {
	
	private String imageUrl;
	private String prdName;
	private String size;
	private String price;
	private String stars;
	private Long id;

}
