import React from 'react';
import './Rating.css'
import StarIcon from '../staricon/StarIcon';

export interface RatingProps {
    label: string;
    rating: number;
    setRating: (rating: number) => void;
}
const Rating = (props: RatingProps) => {
    const { label, rating, setRating, ...rest } = props
    const handleStarClick = (value: number) => {
        setRating(value);
    };

    return (
        <div className="rating-container">
            <label className="rating-label">
                {label}
            </label>
            <div className="rating-button-container">
                {[1, 2, 3, 4, 5].map((i) => (
                    <button
                        key={i}
                        type="button"
                        className="rating-button"
                        onClick={() => handleStarClick(i)}
                    >
                        <StarIcon filled={i <= rating} />
                    </button>
                ))}
            </div>
        </div>
    );
};

export default Rating;