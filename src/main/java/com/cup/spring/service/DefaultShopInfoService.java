package com.cup.spring.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.cup.spring.dto.ServiceDTO;
import com.cup.spring.repository.ShopInfo;
import com.cup.spring.repository.ShopInfoRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@RequiredArgsConstructor
// ShopInfoService 인터페이스에 선언된 메서드들을 완성(구현)하는 클래스다.
// 서비스의 역할 : 컨트롤러로부터 일의 처리를 위임받는다.
//		실제 우리 서비스에서 실행해야 할 중요한 처리들을 담담한다.
@Service
public class DefaultShopInfoService implements ShopInfoService {
	
	private final ShopInfoRepository shopinfoRepository;

	@Override
	public ServiceDTO getServiceInfo() {
		// TODO Auto-generated method stub
 		// setter 또는 생성자를 이용해서 필드 값 초기화 방법 - 순서를 잘못 적어서 잘못된 값이 들어가는 휴먼에러
		// Builder 로 초기화하는 방법 : 생성자
		long count = shopinfoRepository.count();
		if( count > 0 ) {
			List<ShopInfo> info = shopinfoRepository.findAll();
			// 배열의 요소번호-0 1...... , 요소개수 12 [11]
			ShopInfo a = info.get(info.size()-1);	// 현재 저장된 요소 중 마지막 요소의 번호
			// Entity 는 DB table 을 의미 -> 사용자에게 응답하지 않는다.
			// DTO 컨트롤러용 자료형으로 변환하여 반환하다.
			// Mapper : 변환기를 사용해서 간단히 할 수 잇다.
			return ServiceDTO.builder()
					.name(a.getName())
					.address(a.getAddress())
					.phone(a.getPhone())
					.email(a.getEmail())
					.build();
		}
		// count == 0, 현재 저장된게 없다.
		return ServiceDTO.builder()
						.name("CUPshop")
						.address("부산광역시 부산가톨릭대학교")
						.phone("xxx-1111-3333")
						.email("cupshop@test.com")
						.build();
	}

	@Override
	public void updateInfo(ServiceDTO dto) {
		log.info("updateinfo : {}", dto);
		// ModelMapper, StructMapper
		ShopInfo entity = ShopInfo.builder()
				.name(dto.getName())
				.email(dto.getEmail())
				.phone(dto.getPhone())
				.address(dto.getAddress())
				.build();
		// PK 가 없으면 insert 명령으로 추가하기 	entity.getSiPk()
		//	있으면 update 명령으로 수정하기
		shopinfoRepository.save(entity);		
		
	}

}
