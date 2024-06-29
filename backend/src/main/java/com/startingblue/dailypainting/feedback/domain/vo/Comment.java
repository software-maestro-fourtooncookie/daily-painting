package com.startingblue.dailypainting.feedback.domain.vo;

import com.startingblue.dailypainting.feedback.exception.CommentLengthOverException;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public final class Comment {

    private static final int MAX_LENGTH = 500;

    @Column(length = MAX_LENGTH)
    private String comment;

    public Comment(final String comment) {
        validate(comment);
        this.comment = comment;
    }

    private void validate(final String comment) {
        if (comment.length() > MAX_LENGTH) {
            throw new CommentLengthOverException();
        }
    }
}
