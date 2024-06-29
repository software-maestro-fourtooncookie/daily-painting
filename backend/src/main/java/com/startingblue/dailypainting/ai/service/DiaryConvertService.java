package com.startingblue.dailypainting.ai.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.startingblue.dailypainting.diary.dto.DiarySaveRequest;
import org.springframework.stereotype.Service;

@Service
public interface DiaryConvertService {

    String convertDiarySaveRequestToString(final DiarySaveRequest diarySaveRequest);

    String convertScenarioJsonToString(final JsonNode scenario);

}
