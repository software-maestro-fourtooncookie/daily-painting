package com.startingblue.dailypainting.feedback.service;

import com.startingblue.dailypainting.diary.domain.Diary;
import com.startingblue.dailypainting.diary.domain.DiaryRepository;
import com.startingblue.dailypainting.diary.domain.Gender;
import com.startingblue.dailypainting.diary.domain.Weather;
import com.startingblue.dailypainting.diary.exception.DiaryNotFoundException;
import com.startingblue.dailypainting.feedback.dto.FeedbackSaveRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class FeedbackServiceTest {

    @Autowired
    private FeedbackService feedbackService;

    @Autowired
    private DiaryRepository diaryRepository;

    @Test
    public void 피드백을_생성한다() {
        //given
        final Diary diary = Diary.createDiary(2000, Gender.FEMALE, Weather.CLEAR, new ArrayList<>(), new ArrayList<>(), "");
        final Diary savedDiary = diaryRepository.save(diary);
        final FeedbackSaveRequest feedbackSaveRequest = new FeedbackSaveRequest(1, 1, 1, "좋습니다.", "없습니다.", savedDiary.getId());

        //when & then
        assertDoesNotThrow(
                () -> feedbackService.save(feedbackSaveRequest)
        );
    }

    @Test
    public void 일기가_없으면_피드백_생성을_실패한다() {
        //given
        final Long notExistDiaryId = -1L;
        final FeedbackSaveRequest feedbackSaveRequest = new FeedbackSaveRequest(1, 1, 1, "좋습니다.", "없습니다.", notExistDiaryId);

        //when & then
        assertThatThrownBy(
                () -> feedbackService.save(feedbackSaveRequest)
        ).isInstanceOf(DiaryNotFoundException.class);
    }
}
