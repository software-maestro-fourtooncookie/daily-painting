package com.startingblue.dailypainting.diary.domain;

import jakarta.validation.ConstraintViolationException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class DiaryTest {

    @Test
    void 정상_입력() {
        Assertions.assertDoesNotThrow(() -> {
            Diary.createDiary(2020, Gender.MALE, Weather.CLEAR,
                    List.of(Emotion.HAPPY), List.of(MetPerson.FRIEND), "오늘은 좋은 날이다.");
        });

        Assertions.assertDoesNotThrow(() -> {
            Diary.createDiary(2020, Gender.FEMALE, Weather.SNOW,
                    Arrays.asList(Emotion.HAPPY, Emotion.ANGRY), Arrays.asList(MetPerson.FRIEND, MetPerson.ACQUAINTANCE),
                    "오늘은 좋은 날이다.");
        });
    }

    @ParameterizedTest
    @ValueSource(ints = {-1, 0, 1950, 1959})
    void 출생연도가_낮은_경우(int birthYear) {
        assertThatThrownBy(() -> Diary.createDiary(birthYear, Gender.MALE, Weather.CLEAR,
                List.of(Emotion.HAPPY), List.of(MetPerson.FRIEND), "오늘은 좋은 날이다.")).isInstanceOf(ConstraintViolationException.class);
    }

    @ParameterizedTest
    @ValueSource(ints = {2025, 2050, 30000, 99999})
    void 출생연도가_높은_경우(int birthYear) {
        assertThatThrownBy(() -> Diary.createDiary(birthYear, Gender.MALE, Weather.CLEAR,
                List.of(Emotion.HAPPY), List.of(MetPerson.FRIEND), "오늘은 좋은 날이다.")).isInstanceOf(ConstraintViolationException.class);
    }

    @Test
    void 성별이_NULL인_경우() {
        assertThatThrownBy(() -> {
            Diary.createDiary(1990, null, Weather.CLEAR,
                    List.of(Emotion.HAPPY), List.of(MetPerson.FRIEND), "오늘은 좋은 날이다.");
        }).isInstanceOf(ConstraintViolationException.class);
    }

    @Test
    void 날씨가_NULL인_경우() {
        assertThatThrownBy(() -> Diary.createDiary(1990, Gender.MALE, null,
                List.of(Emotion.HAPPY), List.of(MetPerson.FRIEND), "오늘은 좋은 날이다.")).isInstanceOf(ConstraintViolationException.class);
    }

    @Test
    void 감정목록이_비어있는_경우() {
        assertThatThrownBy(() -> Diary.createDiary(1990, Gender.MALE, Weather.CLEAR,
                Collections.emptyList(), List.of(MetPerson.FRIEND), "오늘은 좋은 날이다.")).isInstanceOf(ConstraintViolationException.class)
                .hasMessage("emotions: 오늘 느낀 감정을 최소 1개 선택해야 합니다.");
    }

    @Test
    void 만난사람_목록이_비어있는_경우() {
        assertThatThrownBy(() -> Diary.createDiary(1990, Gender.MALE, Weather.CLEAR,
                List.of(Emotion.HAPPY), Collections.emptyList(), "오늘은 좋은 날이다.")).isInstanceOf(ConstraintViolationException.class);
    }

    @Test
    void 일기_내용이_너무_짧은_경우() {
        assertThatThrownBy(() -> Diary.createDiary(1990, Gender.MALE, Weather.CLEAR,
                List.of(Emotion.HAPPY), List.of(MetPerson.FRIEND), "")).isInstanceOf(ConstraintViolationException.class);
    }

    @ParameterizedTest
    @ValueSource(ints = {2025, 2050, 30000, 99999})
    void 일기_내용이_너무_긴_경우(int diaryContentLength) {
        String longContent = "a".repeat(diaryContentLength);
        assertThatThrownBy(() -> Diary.createDiary(1990, Gender.MALE, Weather.CLEAR,
                List.of(Emotion.HAPPY), List.of(MetPerson.FRIEND), longContent)).isInstanceOf(ConstraintViolationException.class);
    }
}
