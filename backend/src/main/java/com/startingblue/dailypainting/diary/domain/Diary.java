package com.startingblue.dailypainting.diary.domain;

import jakarta.persistence.*;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import jakarta.validation.constraints.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public final class Diary {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "diary_id")
    private Long id;

    @Min(value = 1960, message = "출생연도는 1960년 이상이어야 합니다.")
    @Max(value = 2024, message = "출생연도는 2024년 이하이어야 합니다.")
    @Column(nullable = false)
    private Integer birthYear;

    @NotNull(message = "성별을 선택해야 합니다.")
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Gender gender;

    @NotNull(message = "오늘 날씨를 선택해야 합니다.")
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Weather weather;

    @NotEmpty(message = "오늘 느낀 감정을 최소 1개 선택해야 합니다.")
    @ElementCollection(targetClass = Emotion.class)
    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "diary_emotions", joinColumns = @JoinColumn(name = "diary_id"))
    @Column(name = "emotion", nullable = false)
    private List<Emotion> emotions;

    @NotEmpty(message = "오늘 만난 사람을 최소 1개 선택해야 합니다.")
    @ElementCollection(targetClass = MetPerson.class)
    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "diary_met_people", joinColumns = @JoinColumn(name = "diary_id"))
    @Column(name = "met_person", nullable = false)
    private List<MetPerson> metPeople;

    @Size(min = 1, max = 200, message = "일기 내용은 최소 1글자 최대 200글자 입니다.")
    @Column(nullable = false, columnDefinition = "TEXT")
    private String content;

    @Column(columnDefinition = "TEXT")
    private String imageUrl;

    private Diary(final int birthYear, final Gender gender,
                  final Weather weather, final List<Emotion> emotions, final List<MetPerson> metPeople,
                  final String content) {
        this.birthYear = birthYear;
        this.gender = gender;
        this.weather = weather;
        this.emotions = emotions;
        this.metPeople = metPeople;
        this.content = content;
    }

    public static Diary createDiary(final int birthYear, final Gender gender,
                                    final Weather weather, final List<Emotion> emotions, final List<MetPerson> metPeople,
                                    final String content) {
        Diary diary = new Diary(birthYear, gender, weather, emotions, metPeople, content);
        diary.validate();
        return diary;
    }

    public void updateImageUrl(final String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public void validate() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        Set<ConstraintViolation<Diary>> violations = validator.validate(this);
        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(violations);
        }
    }
}
