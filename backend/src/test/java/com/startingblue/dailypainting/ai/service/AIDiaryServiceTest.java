package com.startingblue.dailypainting.ai.service;

import com.startingblue.dailypainting.diary.dto.DiarySaveRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class AIDiaryServiceTest {

    @Autowired
    private AIDiaryService aiDiaryService;

    @Test
    @DisplayName("일기를 이미지로 변환하기 통합 테스트")
    void 일기를_이미지로_변환하기_통합_테스트() {
        // Given
        int birthYear = 2003;
        String gender = "남성";
        String weather = "좋음";
        List<String> emotions = new ArrayList<String>();
        emotions.add("행복");
        List<String> metPeople = new ArrayList<String>();
        metPeople.add("친구");
        String content = "오늘은 날씨가 좋았다.";
        DiarySaveRequest diarySaveRequest = new DiarySaveRequest(birthYear, gender, weather, emotions, metPeople, content);

        // When
        CompletableFuture<String> result = aiDiaryService.generateImageFromDiary(diarySaveRequest);

        // Then
        assertThat(result.join()).isNotNull();

    }

}