package com.cup.spring.api;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cup.spring.dto.UploadFileDTO;
import com.cup.spring.dto.UploadResultDTO;

import lombok.extern.slf4j.Slf4j;

// API 개발
// RequestBody, ResponseBody - JSON 기반 데이터 송수신
// 파일 업로드 하는 기능 담아서, form 에 file 첨부할 수 있도록 한다.
// Multifile
// -	파일 입출력 스트림 
@Slf4j
@RestController
public class UploadController {

	@Value("${com.cup.upload.path")
	private String uploadpath;
	
	@PostMapping(value="/api/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public List<UploadResultDTO> fileSave(UploadFileDTO uploadfile) {
		// Key : Value 형식
		// Map<String, String>
		// JSON 형식 문자열로 서로 변환 처리가 필요하다.
		
		if( uploadfile.getFiles() != null) {
			final List<UploadResultDTO> list = new ArrayList<>();
			uploadfile.getFiles().forEach(multipartfile -> {
				String originame = multipartfile.getOriginalFilename();
				String uuid = UUID.randomUUID().toString();
				Path savepath = Paths.get(uploadpath, uuid+"_"+originame);
				
				try {
					multipartfile.transferTo(savepath);
				}catch (IOException e) {
					log.error(" {}", e);
				}
				
				list.add(UploadResultDTO.builder()
						.uuid(uuid)
						.filename(originame)
						.build());
			});
			return list;
		}
		return null;
	}
	
	@GetMapping("/view/file/{filename}")
	public ResponseEntity<Resource> viewFile(@PathVariable String filename){
		Resource resource = new FileSystemResource(uploadpath+File.separator+filename);
		String resourcename = resource.getFilename();
		log.info(" {}", resourcename);
		HttpHeaders headers = new HttpHeaders();
		try {
			headers.add("Content-Type", Files.probeContentType(resource.getFile().toPath()));
		}catch(Exception e) {
			log.error(" {}", e.getMessage());
			return ResponseEntity.internalServerError().build();
		}
		return ResponseEntity.ok().headers(headers).body(resource);
	}

}
