package com.startingblue.dailypainting.ai.service;

import com.startingblue.dailypainting.ai.exception.OpenAIError;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Component
public class OpenAIAPIGenerator {

    private static final RestTemplate restTemplate = new RestTemplate();
    private static final String BEARER_TOKEN_PREFIX = "Bearer ";

    @Value("${key.openai}")
    private String apiKey;

    public String responseBodyFromAPI(final String requestBody, final String url) {
        HttpHeaders headers = createHeaders();
        log.info("requestBody: \n{}", requestBody);
        HttpEntity<String> requestEntity = new HttpEntity<>(requestBody, headers);

        ResponseEntity<String> responseEntity;
        try {
            responseEntity = restTemplate.exchange(url, HttpMethod.POST, requestEntity, String.class);
            log.info("OpenAI Response: {}", responseEntity.getBody());
        } catch (Exception e) {
            throw new OpenAIError("OpenAI API 호출 에러", e);
        }
        return responseEntity.getBody();
    }

    private HttpHeaders createHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.AUTHORIZATION, BEARER_TOKEN_PREFIX + apiKey);
        headers.set(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        return headers;
    }

}
