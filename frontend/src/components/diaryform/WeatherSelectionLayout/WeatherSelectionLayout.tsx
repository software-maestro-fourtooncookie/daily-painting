import React from "react";
import "./WeatherSelectionLayout.css";

export interface WeatherSelectionLayoutProps {
    weathers: string[];
    selectedWeather: string;
    onWeatherClick: (weather: string) => void;
}

const WeatherSelectionLayout = (props: WeatherSelectionLayoutProps) => {
    const { weathers, selectedWeather, onWeatherClick } = props;

    return (
        <div className="weather-selection">
            <label className="weather-label">날씨</label>
            <div className="weather-options">
                {weathers.map((weather, index) => (
                    <WeatherOption
                        key={index}
                        weather={weather}
                        isSelected={selectedWeather === weather}
                        onWeatherChange={onWeatherClick}
                    />
                ))}
            </div>
            <p className="selected-weather">
                Selected Weather: {selectedWeather}
            </p>
        </div>
    );
};

export interface WeatherOptionProps {
    weather: string;
    isSelected: boolean;
    onWeatherChange: (weather: string) => void;
}

const WeatherOption = (props: WeatherOptionProps) => {
    const { weather, isSelected, onWeatherChange } = props;

    const getWeatherIcon = (weather: string) => {
        try {
            return require(`../icon/weather/${weather}.png`);
        } catch (error) {
            return require("../icon/weather/DEFAULT.png");
        }
    };

    return (
        <div
            className={`weather-option ${isSelected ? "selected" : ""}`}
            onClick={() => onWeatherChange(weather)}
        >
            <img
                src={getWeatherIcon(weather)}
                alt={weather}
                className="weather-icon"
            />
            <label className="weather-option-label">{weather}</label>
        </div>
    );
};

export default WeatherSelectionLayout;
