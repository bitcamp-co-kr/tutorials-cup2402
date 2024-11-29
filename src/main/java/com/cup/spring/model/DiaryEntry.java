package com.cup.spring.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

// Db table 정의
@Entity
public class DiaryEntry {

	// table's PK 임을 선언하는 것
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // 기록 ID
	
    private String title; // 제목
    private String content; // 내용
    private double latitude; // 위도
    private double longitude; // 경도
    private String imageUrl; // 첨부 이미지 URL

    public DiaryEntry(Long id, String title, String content, double latitude, double longitude, String imageUrl) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.latitude = latitude;
        this.longitude = longitude;
        this.imageUrl = imageUrl;
    }

    // Getter 및 Setter : @Entity 가 붙은 클래스에는 setter 를 정의하지 않는다.
    //	개발자들의 규칙 : 이 클래스의 값들은 DB 와 바로 연결되는 상태여서 값이 함부로 수정되지 않게 막는다....
    //	수정이 필요하다면, setXXXX 말고 일반적인 이름 말고 좀 다른 이름 사용해라
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
