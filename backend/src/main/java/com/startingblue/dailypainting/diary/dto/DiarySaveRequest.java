package com.startingblue.dailypainting.diary.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;

import java.util.List;

@Getter
public final class DiarySaveRequest {

    @Min(value = 1960, message = "출생연도는 1960년 이상이어야 합니다.")
    @Max(value = 2024, message = "출생연도는 2024년 이하이어야 합니다.")
    private final Integer birthYear;

    @NotEmpty(message = "성별을 선택해야 합니다.")
    private final String gender;

    @NotEmpty(message = "오늘 날씨를 선택해야 합니다.")
    private final String weather;

    @NotEmpty(message = "오늘 느낀 감정을 최소 1개 선택해야 합니다.")
    private final List<String> emotions;

    @NotEmpty(message = "오늘 만난 사람을 최소 1개 선택해야 합니다.")
    private final List<String> metPeople;

    @NotEmpty(message = "일기 내용을 작성해야 합니다.")
    private final String content;

    public DiarySaveRequest(
            final int birthYear, final String gender,
            final String weather, final List<String> emotions, final List<String> metPeople,
            final String content) {
        this.birthYear = birthYear;
        this.gender = gender;
        this.weather = weather;
        this.emotions = emotions;
        this.metPeople = metPeople;
        this.content = content;
    }
}
