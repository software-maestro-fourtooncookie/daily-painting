package com.startingblue.dailypainting.diary.controller;

import com.startingblue.dailypainting.ai.service.AIDiaryService;
import com.startingblue.dailypainting.diary.domain.Emotion;
import com.startingblue.dailypainting.diary.domain.MetPerson;
import com.startingblue.dailypainting.diary.domain.Weather;
import com.startingblue.dailypainting.diary.dto.DiarySaveRequest;
import com.startingblue.dailypainting.diary.dto.DiaryFormResponse;
import com.startingblue.dailypainting.diary.dto.DiarySavedResponse;
import com.startingblue.dailypainting.diary.service.DiaryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;
import java.util.concurrent.CompletableFuture;

@RestController
@RequiredArgsConstructor
public final class DiaryController {

    private final DiaryService diaryService;
    private final AIDiaryService openAIService;

    @GetMapping("/api/diaries")
    public ResponseEntity<DiaryFormResponse> diaryHome() {
        DiaryFormResponse diaryFormResponse = new DiaryFormResponse(
                Weather.findAllNames(),
                Emotion.findAllNames(),
                MetPerson.findAllNames());

        return ResponseEntity.ok(diaryFormResponse);
    }

    @PostMapping("/api/diaries")
    public ResponseEntity<?> createDiary(@Valid @RequestBody DiarySaveRequest diarySaveRequest,
                                         BindingResult bindingResult) {

        Long savedDiaryId = diaryService.save(diarySaveRequest);

        CompletableFuture<String> future = openAIService.generateImageFromDiary(diarySaveRequest);
        String imageUrl = future.join();

        diaryService.updateDiaryImagePath(savedDiaryId, imageUrl);

        DiarySavedResponse diarySavedResponse = new DiarySavedResponse(savedDiaryId, imageUrl);

        return ResponseEntity.ok(diarySavedResponse);
    }
}
