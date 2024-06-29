package com.startingblue.dailypainting.ai.service.scenario;

import com.fasterxml.jackson.databind.JsonNode;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class ScenarioServiceTest {

    @Autowired
    ScenarioService scenarioService;

    @Test
    void 일기를_Senario에_보내기() {
        // Given
        String diaryContent = "Weather : 좋음\n" +
                "Emotions: 행복\n" +
                "Person who met: 친구\n" +
                "Diary: 오늘은 날씨가 좋았다.";
        // When
        JsonNode scenario = scenarioService.sendDiaryToLLM(diaryContent);
        // Then
        assertThat(scenario).isNotNull();
    }
}