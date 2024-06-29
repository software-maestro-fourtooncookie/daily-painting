package com.startingblue.dailypainting.feedback.domain.vo;

import com.startingblue.dailypainting.feedback.exception.ImageQualityValueException;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public final class ImageQuality {

    private static final int MIN_VALUE = 1;
    private static final int MAX_VALUE = 5;
    @Min(MIN_VALUE)
    @Max(MAX_VALUE)
    private int imageQuality;

    public ImageQuality(final int imageQuality) {
        validate(imageQuality);
        this.imageQuality = imageQuality;
    }

    private void validate(final int imageQuality) {
        if (imageQuality < MIN_VALUE || imageQuality > MAX_VALUE) {
            throw new ImageQualityValueException();
        }
    }
}
