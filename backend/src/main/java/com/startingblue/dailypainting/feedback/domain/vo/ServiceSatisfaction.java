package com.startingblue.dailypainting.feedback.domain.vo;

import com.startingblue.dailypainting.feedback.exception.ServiceSatisfactionValueException;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public final class ServiceSatisfaction {

    private static final int MIN_VALUE = 1;
    private static final int MAX_VALUE = 5;
    @Min(MIN_VALUE)
    @Max(MAX_VALUE)
    private int serviceSatisfaction;

    public ServiceSatisfaction(final int serviceSatisfaction) {
        validate(serviceSatisfaction);
        this.serviceSatisfaction = serviceSatisfaction;
    }

    private void validate(final int serviceSatisfaction) {
        if (serviceSatisfaction < MIN_VALUE || serviceSatisfaction > MAX_VALUE) {
            throw new ServiceSatisfactionValueException();
        }
    }
}
