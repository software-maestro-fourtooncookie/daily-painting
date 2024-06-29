package com.startingblue.dailypainting.feedback.exception;

public final class ImageSatisfactionValueException extends RuntimeException {

    private static final String IMAGE_SATISFACTION_VALUE_EXCEPTION = "이미지 만족도 피드백은 1과 5 사이어야 합니다.";

    public ImageSatisfactionValueException() {
        super(IMAGE_SATISFACTION_VALUE_EXCEPTION);
    }
}
