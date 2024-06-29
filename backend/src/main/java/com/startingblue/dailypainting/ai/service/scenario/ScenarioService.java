package com.startingblue.dailypainting.ai.service.scenario;

import com.fasterxml.jackson.databind.JsonNode;

public interface ScenarioService {

    JsonNode sendDiaryToLLM(final String diaryContent);

}
