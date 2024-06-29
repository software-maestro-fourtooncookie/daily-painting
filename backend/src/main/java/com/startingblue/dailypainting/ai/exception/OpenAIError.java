package com.startingblue.dailypainting.ai.exception;

public class OpenAIError extends AIError{
    public OpenAIError(String message, Exception e) {
        super(message, e);
    }
}
