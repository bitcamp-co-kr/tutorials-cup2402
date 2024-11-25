package com.cup.spring.controller;

import com.cup.spring.service.DiaryService;
import com.cup.spring.model.DiaryEntry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/diary")
public class DiaryController {

    @Autowired
    private DiaryService diaryService;

    // 모든 기록 조회
    @GetMapping
    public List<DiaryEntry> getAllEntries() {
        return diaryService.getAllDiaryEntries();
    }

    // 새 기록 추가
    @PostMapping
    public DiaryEntry addEntry(@RequestParam String title, @RequestParam String content,
                               @RequestParam double latitude, @RequestParam double longitude,
                               @RequestParam String imageUrl) {
        return diaryService.addDiaryEntry(title, content, latitude, longitude, imageUrl);
    }

    // 특정 기록 조회
    @GetMapping("/{id}")
    public DiaryEntry getEntryById(@PathVariable int id) {
        return diaryService.getDiaryEntryById(id).orElse(null);
    }

    // 기록 수정
    @PutMapping("/{id}")
    public boolean updateEntry(@PathVariable int id, @RequestParam String title, @RequestParam String content,
                               @RequestParam double latitude, @RequestParam double longitude,
                               @RequestParam String imageUrl) {
        return diaryService.updateDiaryEntry(id, title, content, latitude, longitude, imageUrl);
    }

    // 기록 삭제
    @DeleteMapping("/{id}")
    public boolean deleteEntry(@PathVariable int id) {
        return diaryService.deleteDiaryEntry(id);
    }
}
