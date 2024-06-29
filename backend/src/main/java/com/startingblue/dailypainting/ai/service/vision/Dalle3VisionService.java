package com.startingblue.dailypainting.ai.service.vision;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.startingblue.dailypainting.ai.exception.JsonAIError;
import com.startingblue.dailypainting.ai.service.OpenAIAPIGenerator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;

@RequiredArgsConstructor
@Slf4j
@Service
public class Dalle3VisionService implements VisionService {

    private static final String VISION_API_URL = "https://api.openai.com/v1/images/generations";

    private static final String VISION_MODEL = "dall-e-3";
    private static final int IMAGE_COUNT = 1;
    private static final String IMAGE_SIZE = "1024x1024";

    @Value("${prompt.dalle3.vision}")
    private String VISION_PROMPT;

    private final OpenAIAPIGenerator openAIAPIGenerator;

    public String sendScenarioToVision(final String scenario) {
        String visionPrompt = createVisionPromptFromScenario(scenario);
        String requestBody = createRequestBody(visionPrompt);
        String responseBody = openAIAPIGenerator.responseBodyFromAPI(requestBody, VISION_API_URL);
        String imageUrl = extractImageUrlFromVisionResponse(responseBody);
        log.info("Vision imageURL: {}", imageUrl);
        return imageUrl;
    }

    private String extractImageUrlFromVisionResponse(final String responseBody) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            JsonNode root = objectMapper.readTree(responseBody);
            JsonNode dataNode = root.path("data").get(0);
            return dataNode.path("url").asText();
        } catch (Exception e) {
            throw new JsonAIError("Vision responseBody Json parsing 에러", e);
        }
    }

    private String createVisionPromptFromScenario(String scenePrompts) {
        return String.format(VISION_PROMPT, scenePrompts);
    }

    private String createRequestBody(String content) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();

            // Create JSON object
            ObjectNode rootNode = objectMapper.createObjectNode();
            rootNode.put("model", VISION_MODEL);
            rootNode.put("prompt", content);
            rootNode.put("n", IMAGE_COUNT);
            rootNode.put("size", IMAGE_SIZE);

            // Convert JSON object to string
            return objectMapper.writeValueAsString(rootNode);
        } catch (Exception e) {
            throw new JsonAIError("Vision requestBody 구성 에러", e);
        }
    }
}
