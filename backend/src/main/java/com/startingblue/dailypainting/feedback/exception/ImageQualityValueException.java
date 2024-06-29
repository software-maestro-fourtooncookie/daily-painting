package com.startingblue.dailypainting.feedback.exception;

public final class ImageQualityValueException extends RuntimeException {

    private static final String IMAGE_QUALITY_VALUE_EXCEPTION = "이미지 품질 피드백은 1과 5 사이어야 합니다.";

    public ImageQualityValueException() {
        super(IMAGE_QUALITY_VALUE_EXCEPTION);
    }
}
