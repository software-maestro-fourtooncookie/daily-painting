import React, { ChangeEvent } from "react";
import "./BirthYearInput.css";

export interface BirthYearInputProps {
    birthYear: number;
    onChange: (birthYear: number) => void;
}

const BirthYearInput = (props: BirthYearInputProps) => {
    const { birthYear, onChange } = props;

    const handleBirthYearChange = (e: ChangeEvent<HTMLSelectElement>) => {
        onChange(Number(e.target.value));
    };

    const generateYearOptions = (): JSX.Element[] => {
        const years: number[] = [];

        for (let year = 2024; year >= 1960; year--) {
            years.push(year);
        }

        return years.map((year) => (
            <option key={year} value={year}>
                {year}
            </option>
        ));
    };

    return (
        <div className="birth-year-input">
            <label className="birth-year-label">출생 연도</label>
            <select
                className="birth-year-select"
                value={birthYear}
                onChange={handleBirthYearChange}
            >
                {generateYearOptions()}
            </select>
            <p className="selected-birth-year">
                Selected Birth Year: {birthYear}
            </p>
        </div>
    );
};

export default BirthYearInput;
