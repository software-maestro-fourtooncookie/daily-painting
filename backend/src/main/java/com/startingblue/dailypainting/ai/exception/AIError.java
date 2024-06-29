package com.startingblue.dailypainting.ai.exception;

public abstract class AIError extends InternalError {
    public AIError(String message, Exception e) {
        super(message, e);
    }
}
