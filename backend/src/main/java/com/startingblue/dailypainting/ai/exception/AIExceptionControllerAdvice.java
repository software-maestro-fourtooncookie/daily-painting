package com.startingblue.dailypainting.ai.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice("com.startingblue.dailypainting.ai")
public class AIExceptionControllerAdvice {

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(AIError.class)
    public AIErrorResponse handleRuntimeException(AIError e) {
        log.error("[exceptionHandle] : {}", e.getMessage());
        return new AIErrorResponse("INTERNAL_SERVER_ERROR", "AI 구성 중 에러 발생");
    }


}
