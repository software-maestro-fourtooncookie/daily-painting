package com.startingblue.dailypainting.feedback.domain.vo;

import com.startingblue.dailypainting.feedback.exception.FavoriteCharacterLengthOverException;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public final class FavoriteCharacter {

    private static final int MAX_LENGTH = 500;

    @Column(length = MAX_LENGTH)
    private String favoriteCharacter;

    public FavoriteCharacter(final String favoriteCharacter) {
        validate(favoriteCharacter);
        this.favoriteCharacter = favoriteCharacter;
    }

    private void validate(final String favoriteCharacter) {
        if (favoriteCharacter.length() > MAX_LENGTH) {
            throw new FavoriteCharacterLengthOverException();
        }
    }
}
