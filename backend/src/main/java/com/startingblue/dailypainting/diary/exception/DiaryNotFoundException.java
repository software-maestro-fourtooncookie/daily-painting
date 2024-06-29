package com.startingblue.dailypainting.diary.exception;

public final class DiaryNotFoundException extends RuntimeException {

    private static final String DIARY_NOT_FOUND_EXCEPTION_MESSAGE = "존재하지 않는 일기";

    public DiaryNotFoundException() {
        super(DIARY_NOT_FOUND_EXCEPTION_MESSAGE);
    }
}
