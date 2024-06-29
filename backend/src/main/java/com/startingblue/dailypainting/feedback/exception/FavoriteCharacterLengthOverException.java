package com.startingblue.dailypainting.feedback.exception;

public final class FavoriteCharacterLengthOverException extends RuntimeException {

    private static final String FAVORITE_CHARACTER_LENGTH_OVER_EXCEPTION_MESSAGE = "좋아하는 캐릭터는 500자 이내어야 합니다.";

    public FavoriteCharacterLengthOverException() {
        super(FAVORITE_CHARACTER_LENGTH_OVER_EXCEPTION_MESSAGE);
    }
}
