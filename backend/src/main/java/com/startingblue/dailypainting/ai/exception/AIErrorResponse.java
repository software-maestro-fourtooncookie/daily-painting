package com.startingblue.dailypainting.ai.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class AIErrorResponse {

        private final String code;
        private final String details;

}
