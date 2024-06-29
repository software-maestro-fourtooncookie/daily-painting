package com.startingblue.dailypainting.diary.service;

import com.startingblue.dailypainting.diary.domain.*;
import com.startingblue.dailypainting.diary.dto.DiarySaveRequest;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class DiaryService {

    private final DiaryRepository diaryRepository;

    public Long save(final DiarySaveRequest diarySaveRequest) {
        int birthYear = diarySaveRequest.getBirthYear();
        Gender gender = Gender.findGender(diarySaveRequest.getGender());
        Weather weather = Weather.findWeather(diarySaveRequest.getWeather());
        List<Emotion> emotions = Emotion.findEmotions(diarySaveRequest.getEmotions());
        List<MetPerson> metPeople = MetPerson.findMetPeople(diarySaveRequest.getMetPeople());
        String content = diarySaveRequest.getContent();

        Diary diary = Diary.createDiary(
                birthYear,
                gender,
                weather,
                emotions,
                metPeople,
                content);

        Diary savedDiary = diaryRepository.save(diary);
        log.info("save diary: {}", savedDiary);

        return savedDiary.getId();
    }

    public void updateDiaryImagePath(final Long diaryId, final String imageUrl) {
            Diary diary = diaryRepository.findById(diaryId)
                    .orElseThrow(() -> new EntityNotFoundException("Diary with ID " + diaryId + " not found"));

            diary.updateImageUrl(imageUrl);
            diaryRepository.save(diary);
    }
}
