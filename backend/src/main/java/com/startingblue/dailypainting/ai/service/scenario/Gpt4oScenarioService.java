package com.startingblue.dailypainting.ai.service.scenario;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
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
public class Gpt4oScenarioService implements ScenarioService {

    private static final String LLM_API_URL = "https://api.openai.com/v1/chat/completions";
    private static final String LLM_MODEL = "gpt-4o";

    private final OpenAIAPIGenerator openAIAPIGenerator;

    @Value("${prompt.gpt4o.system}")
    private String LLM_SYSTEM_PROMPT;

    public JsonNode sendDiaryToLLM(final String diaryContent) {
        String requestBody = createRequestBody(diaryContent);
        String responseBody = openAIAPIGenerator.responseBodyFromAPI(requestBody, LLM_API_URL);
        JsonNode scenario = extractJsonFromLLMResponse(responseBody);
        log.info("LLM scenario: {}", scenario.toPrettyString());
        return scenario;
    }

    private JsonNode extractJsonFromLLMResponse(final String responseBody) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            JsonNode root = objectMapper.readTree(responseBody);
            JsonNode content = root.path("choices").get(0).get("message").get("content");
            ObjectMapper objectMapper2 = new ObjectMapper();
            return objectMapper2.readTree(content.asText());
        } catch (Exception e) {
            throw new JsonAIError("LLM responseBody Json parsing 에러", e);
        }
    }

    private String createRequestBody(String content) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();

            // JSON 객체 생성
            ObjectNode rootNode = objectMapper.createObjectNode();
            rootNode.put("model", LLM_MODEL);

            ArrayNode messagesArray = rootNode.putArray("messages");

            ObjectNode systemMessage = objectMapper.createObjectNode();
            systemMessage.put("role", "system");
            systemMessage.put("content", LLM_SYSTEM_PROMPT);
            messagesArray.add(systemMessage);

            ObjectNode userMessage = objectMapper.createObjectNode();
            userMessage.put("role", "user");
            userMessage.put("content", content);
            messagesArray.add(userMessage);

            return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(rootNode);
        } catch (Exception e) {
            throw new JsonAIError("Scenario requestBody 구성 에러", e);
        }
    }
}
