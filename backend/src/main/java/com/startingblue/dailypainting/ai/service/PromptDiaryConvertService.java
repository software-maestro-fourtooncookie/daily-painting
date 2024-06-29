package com.startingblue.dailypainting.ai.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.startingblue.dailypainting.diary.dto.DiarySaveRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class PromptDiaryConvertService implements DiaryConvertService {

    @Value("${prompt.promptconvert.diary}")
    private String DIARY_PROMPT;

    @Value("${prompt.promptconvert.person}")
    private String PERSON_PROMPT;

    @Value("${prompt.promptconvert.scene}")
    private String SCENE_PROMPT;

    @Override
    public String convertDiarySaveRequestToString(final DiarySaveRequest diarySaveRequest) {
        System.out.println("DIARY_PROMPT = " + DIARY_PROMPT);
        return String.format(DIARY_PROMPT,
                diarySaveRequest.getWeather(),
                String.join(", ", diarySaveRequest.getEmotions()),
                String.join(", ", diarySaveRequest.getMetPeople()),
                diarySaveRequest.getContent());
    }

    @Override
    public String convertScenarioJsonToString(final JsonNode scenario) {
        List<String> scenePrompts = new ArrayList<>();

        Iterator<JsonNode> scenarioElements = scenario.elements();

        while(scenarioElements.hasNext()) {
            JsonNode element = scenarioElements.next();

            List<String> personPrompts = new ArrayList<>();

            Iterator<JsonNode> personsElements = element.get("persons").elements();

            while (personsElements.hasNext()) {
                JsonNode person = personsElements.next();
                personPrompts.add(
                        String.format(PERSON_PROMPT,
                                person.get("name").asText(),
                                person.get("action").asText(),
                                person.get("facial expression").asText()
                        )
                );
            }

            String elementPrompt = String.format(SCENE_PROMPT,
                    scenario.get("background"),
                    scenario.get("time"),
                    scenario.get("weather"),
                    String.join(" ", personPrompts));

            scenePrompts.add(elementPrompt);
        }

        return String.join(", ", scenePrompts);
    }
}
