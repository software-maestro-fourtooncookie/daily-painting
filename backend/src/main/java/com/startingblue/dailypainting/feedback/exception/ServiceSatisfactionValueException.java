package com.startingblue.dailypainting.feedback.exception;

public final class ServiceSatisfactionValueException extends RuntimeException {

    private static final String SERVICE_SATISFACTION_VALUE_EXCEPTION = "서비스 만족도 피드백은 1과 5 사이어야 합니다.";

    public ServiceSatisfactionValueException() {
        super(SERVICE_SATISFACTION_VALUE_EXCEPTION);
    }
}
