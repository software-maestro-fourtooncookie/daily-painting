package com.startingblue.dailypainting.ai.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.startingblue.dailypainting.diary.dto.DiarySaveRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class DiaryConvertServiceTest {

    @Autowired DiaryConvertService diaryConvertService;

    @Test
    void DiarySaveRequest를_String으로_변환_테스트(){
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
        String diaryContent = diaryConvertService.convertDiarySaveRequestToString(diarySaveRequest);

        // Then
        assertThat(diaryContent).isNotNull();
    }

    @Test
    void Scenario를_String으로_변환_테스트() throws JsonProcessingException {
        // Given
        String scenarioString = "[\n" +
                "    {\n" +
                "        \"place\": \"home\",\n" +
                "        \"time\": \"morning\",\n" +
                "        \"weather\": \"clear\",\n" +
                "        \"persons\": [\n" +
                "            {\n" +
                "                \"name\": \"User\",\n" +
                "                \"action\": \"waking up\",\n" +
                "                \"facial expression\": \"puzzled\"\n" +
                "            }\n" +
                "        ]\n" +
                "    },\n" +
                "    {\n" +
                "        \"place\": \"walkway\",\n" +
                "        \"time\": \"morning\",\n" +
                "        \"weather\": \"clear\",\n" +
                "        \"persons\": [\n" +
                "            {\n" +
                "                \"name\": \"User\",\n" +
                "                \"action\": \"walking\",\n" +
                "                \"facial expression\": \"puzzled\"\n" +
                "            }\n" +
                "        ]\n" +
                "    },\n" +
                "    {\n" +
                "        \"place\": \"center\",\n" +
                "        \"time\": \"midday\",\n" +
                "        \"weather\": \"clear\",\n" +
                "        \"persons\": [\n" +
                "            {\n" +
                "                \"name\": \"User\",\n" +
                "                \"action\": \"set up various things\",\n" +
                "                \"facial expression\": \"confused\"\n" +
                "            },\n" +
                "            {\n" +
                "                \"name\": \"Team member\",\n" +
                "                \"action\": \"meeting\",\n" +
                "                \"facial expression\": \"focused\"\n" +
                "            }\n" +
                "        ]\n" +
                "    },\n" +
                "    {\n" +
                "        \"place\": \"center\",\n" +
                "        \"time\": \"evening\",\n" +
                "        \"weather\": \"clear\",\n" +
                "        \"persons\": [\n" +
                "            {\n" +
                "                \"name\": \"User\",\n" +
                "                \"action\": \"wrapping up the day\",\n" +
                "                \"facial expression\": \"disoriented\"\n" +
                "            }\n" +
                "        ]\n" +
                "    }\n" +
                "]";
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode scenario = objectMapper.readTree(scenarioString);

        // When
        String result = diaryConvertService.convertScenarioJsonToString(scenario);

        // Then
        assertThat(result).isNotNull();
    }

}