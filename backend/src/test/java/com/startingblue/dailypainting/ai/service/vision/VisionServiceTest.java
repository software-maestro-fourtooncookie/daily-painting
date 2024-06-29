package com.startingblue.dailypainting.ai.service.vision;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class VisionServiceTest {

    @Autowired
    VisionService visionService;

    @Test
    void Vision에_시나리오_보내기_테스트() {
        // Given
        String visionPrompt = "Astronauts on Mars.";
        // When
        String imageUrl = visionService.sendScenarioToVision(visionPrompt);
        // Then
        assertThat(imageUrl).isNotNull();
    }

}