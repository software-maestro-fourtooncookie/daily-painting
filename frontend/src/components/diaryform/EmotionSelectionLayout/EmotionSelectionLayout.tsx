import React from "react";
import "./EmotionSelectionLayout.css";

export interface EmotionSelectionLayoutProps {
    emotions: string[];
    selectedEmotions: string[];
    onEmotionClick: (emotion: string) => void;
}

const EmotionSelectionLayout = (props: EmotionSelectionLayoutProps) => {
    const { emotions, selectedEmotions, onEmotionClick } = props;

    return (
        <div className="emotion-selection">
            <label className="emotion-label">감정</label>
            <div className="emotion-options">
                {emotions.map((emotion, index) => (
                    <EmotionOption
                        key={index}
                        emotion={emotion}
                        isSelected={selectedEmotions.includes(emotion)}
                        onEmotionClick={onEmotionClick}
                    />
                ))}
            </div>
            <p className="selected-emotions">
                Selected Emotions: {selectedEmotions.join(", ")}
            </p>
        </div>
    );
};

export interface EmotionOptionProps {
    emotion: string;
    isSelected: boolean;
    onEmotionClick: (emotion: string) => void;
}

const EmotionOption = (props: EmotionOptionProps) => {
    const { emotion, isSelected, onEmotionClick } = props;

    const getEmotionIcon = (emotion: string) => {
        try {
            return require(`../icon/emotion/${emotion}.png`);
        } catch (error) {
            return require("../icon/emotion/DEFAULT.png");
        }
    };

    return (
        <div
            className={`emotion-option ${isSelected ? "selected" : ""}`}
            onClick={() => onEmotionClick(emotion)}
        >
            <img
                src={getEmotionIcon(emotion)}
                alt={emotion}
                className="emotion-icon"
            />
            <label className="emotion-option-label">{emotion}</label>
        </div>
    );
};

export default EmotionSelectionLayout;
