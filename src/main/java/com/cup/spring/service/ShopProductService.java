package com.cup.spring.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.cup.spring.dto.ProductDTO;
import com.cup.spring.repository.Product;
import com.cup.spring.repository.ProductRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
// 인터페이스 : 연결하기 위한 규칙, 정의하는 것
//	내가 만든 코드와 프레임워크에 대한 서로 연결되는 부분에 정의
@Service
public class ShopProductService implements ProductService {
	
	private final ProductRepository productRepositoy;

	public List<ProductDTO> getAll() {
		
		List<Product> list = productRepositoy.findAll();
		if( list == null || list.size() == 0) {
			// DB 에 정보가 없다.
			log.info("get all : empty data");
			return null;
		}else {
			// 1. 반복문
			List<ProductDTO> dtos = new ArrayList<>();
			for(Product prd : list ) {
				dtos.add(
						ProductDTO.builder()
						.id(prd.getPrdPk())
						.imageUrl(prd.getPrdThumbURL())
						.prdName(prd.getPrdTitle())
						.price( String.valueOf( prd.getPrdPrice() ))
						.size(prd.getPrdSize())
						.build()
				);
			}
			return dtos;
			// 2. stream api : map
		}
		
		// not reachable
	}

}
