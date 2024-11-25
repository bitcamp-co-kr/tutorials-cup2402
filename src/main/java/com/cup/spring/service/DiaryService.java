package com.cup.spring.service;

import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DiaryService {

    // 여행 기록을 저장할 임시 데이터베이스 (예: List)
    private List<DiaryEntry> diaryEntries = new ArrayList<>();
    private int idCounter = 1; // 기록 ID를 위한 카운터

    // 여행 기록 추가
    public DiaryEntry addDiaryEntry(String title, String content, double latitude, double longitude, String imageUrl) {
        DiaryEntry entry = new DiaryEntry(idCounter++, title, content, latitude, longitude, imageUrl);
        diaryEntries.add(entry);
        return entry;
    }

    // 여행 기록 전체 조회
    public List<DiaryEntry> getAllDiaryEntries() {
        return diaryEntries;
    }

    // 특정 여행 기록 조회
    public Optional<DiaryEntry> getDiaryEntryById(int id) {
        return diaryEntries.stream().filter(entry -> entry.getId() == id).findFirst();
    }

    // 여행 기록 수정
    public boolean updateDiaryEntry(int id, String title, String content, double latitude, double longitude, String imageUrl) {
        Optional<DiaryEntry> optionalEntry = getDiaryEntryById(id);

        if (optionalEntry.isPresent()) {
            DiaryEntry entry = optionalEntry.get();
            entry.setTitle(title);
            entry.setContent(content);
            entry.setLatitude(latitude);
            entry.setLongitude(longitude);
            entry.setImageUrl(imageUrl);
            return true;
        }

        return false;
    }

    // 여행 기록 삭제
    public boolean deleteDiaryEntry(int id) {
        return diaryEntries.removeIf(entry -> entry.getId() == id);
    }
}
