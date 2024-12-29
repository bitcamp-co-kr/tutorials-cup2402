package com.cup.spring.repository;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
public class Product {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long prdPk;
	
	private String prdTitle;
	private String prdGender;
	private String prdSale;
	private String prdItem;
	private String prdSize;
	private double prdPrice; // 표시 정가
	private String prdThumbURL;
	private String prdDesction;
	private String prdSpec;
	private String prdColor;
	private String prdBrand;

	
}
