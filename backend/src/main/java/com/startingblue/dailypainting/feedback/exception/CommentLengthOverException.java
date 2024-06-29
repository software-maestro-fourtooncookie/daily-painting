package com.startingblue.dailypainting.feedback.exception;

public final class CommentLengthOverException extends RuntimeException {

    private static final String COMMENT_LENGTH_OVER_EXCEPTION_MESSAGE = "추가 의견은 500자 이내어야 합니다.";

    public CommentLengthOverException() {
        super(COMMENT_LENGTH_OVER_EXCEPTION_MESSAGE);
    }
}
