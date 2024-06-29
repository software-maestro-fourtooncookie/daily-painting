package com.startingblue.dailypainting.feedback.domain;

import com.startingblue.dailypainting.diary.domain.Diary;
import com.startingblue.dailypainting.feedback.domain.vo.Comment;
import com.startingblue.dailypainting.feedback.domain.vo.FavoriteCharacter;
import com.startingblue.dailypainting.feedback.domain.vo.ImageQuality;
import com.startingblue.dailypainting.feedback.domain.vo.ImageSatisfaction;
import com.startingblue.dailypainting.feedback.domain.vo.ServiceSatisfaction;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Objects;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public final class Feedback {

    @Id
    @GeneratedValue
    private Long id;

    @Embedded
    private ServiceSatisfaction serviceSatisfaction;

    @Embedded
    private ImageQuality imageQuality;

    @Embedded
    private ImageSatisfaction imageSatisfaction;

    @Embedded
    private Comment comment;

    @Embedded
    private FavoriteCharacter favoriteCharacter;

    @OneToOne
    private Diary diary;

    public Feedback(final int serviceSatisfaction, final int imageQuality, final int imageSatisfaction, final String comment, final String favoriteCharacter, final Diary diary) {
        validate(diary);
        this.serviceSatisfaction = new ServiceSatisfaction(serviceSatisfaction);
        this.imageQuality = new ImageQuality(imageQuality);
        this.imageSatisfaction = new ImageSatisfaction(imageSatisfaction);
        this.comment = new Comment(comment);
        this.favoriteCharacter = new FavoriteCharacter(favoriteCharacter);
        this.diary = diary;
    }

    public void validate(final Diary diary) throws IllegalArgumentException {
        validateDiary(diary);
    }

    private void validateDiary(final Diary diary) {
        if (Objects.isNull(diary)) {
            throw new NullPointerException("일기는 null 값이 될 수 없습니다.");
        }
    }
}
