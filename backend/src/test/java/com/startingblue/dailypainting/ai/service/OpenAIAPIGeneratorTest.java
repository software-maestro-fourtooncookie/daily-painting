package com.startingblue.dailypainting.ai.service;

import com.startingblue.dailypainting.ai.exception.OpenAIError;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class OpenAIAPIGeneratorTest {

    @Autowired OpenAIAPIGenerator openAIAPIGenerator;

    @Test
    void OpenAI_GPT4o_API_호출_테스트(){
        // Given
        String requestBody = "{" +
                "  \"model\": \"gpt-4o\"," +
                "  \"messages\": [" +
                "    {" +
                "      \"role\": \"system\"," +
                "      \"content\": \"You are a highly intelligent and creative person.\"" +
                "    }," +
                "    {" +
                "      \"role\": \"user\"," +
                "      \"content\": \"I am a highly intelligent and creative person.\"" +
                "    }" +
                "  ]" +
                "}";
        String url = "https://api.openai.com/v1/chat/completions";

        // When
        String imageUrl = openAIAPIGenerator.responseBodyFromAPI(requestBody, url);

        // Then
        assertThat(imageUrl).isNotNull();
    }

    @Test
    void OpenAI_GPT4o_API_호출_테스트_잘못된_RequestBody(){
        // Given
        String requestBody = ""; // 내용이 없음
        String url = "https://api.openai.com/v1/chat/completions";

        // When & Then
        assertThrows(OpenAIError.class, () -> {
            openAIAPIGenerator.responseBodyFromAPI(requestBody, url);
        });
    }

    @Test
    void OpenAI_DallE3_API_호출_테스트(){
        // Given
        String requestBody = "{" +
                "  \"model\": \"dall-e-3\"," +
                "  \"prompt\": \"a painting of a fluffy cat sitting on a couch\"," +
                "  \"n\": 1," +
                "  \"size\": \"1024x1024\"" +
                "}";
        String url = "https://api.openai.com/v1/images/generations";

        // When
        String imageUrl = openAIAPIGenerator.responseBodyFromAPI(requestBody, url);

        // Then
        assertThat(imageUrl).isNotNull();

    }

    @Test
    void OpenAI_DallE3_API_호출_테스트_잘못된_RequestBody(){
        // Given
        String requestBody = "{" +
                "  \"model\": \"dall-e-4\"," + // 잘못된 모델명
                "  \"prompt\": \"a painting of a fluffy cat sitting on a couch\"," +
                "  \"n\": 1," +
                "  \"size\": \"1024x1024\"" +
                "}";
        String url = "https://api.openai.com/v1/images/generations";

        // When & Then
        assertThrows(OpenAIError.class, () -> {
            openAIAPIGenerator.responseBodyFromAPI(requestBody, url);
        });
    }

    @Test
    void 잘못된_url_호출_테스트() {
        // Given
        String requestBody = "";
        String url = "";

        // When & Then
        assertThrows(OpenAIError.class, () -> {
            openAIAPIGenerator.responseBodyFromAPI(requestBody, url);
        });
    }

}