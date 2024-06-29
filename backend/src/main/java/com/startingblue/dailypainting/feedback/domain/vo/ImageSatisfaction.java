package com.startingblue.dailypainting.feedback.domain.vo;

import com.startingblue.dailypainting.feedback.exception.ImageSatisfactionValueException;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public final class ImageSatisfaction {

    private static final int MIN_VALUE = 1;
    private static final int MAX_VALUE = 5;
    @Min(MIN_VALUE)
    @Max(MAX_VALUE)
    private int imageSatisfaction;

    public ImageSatisfaction(final int imageSatisfaction) {
        validate(imageSatisfaction);
        this.imageSatisfaction = imageSatisfaction;
    }

    private void validate(final int imageSatisfaction) {
        if (imageSatisfaction < MIN_VALUE || imageSatisfaction > MAX_VALUE) {
            throw new ImageSatisfactionValueException();
        }
    }
}
