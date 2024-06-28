import React from "react";
import "./StarIcon.css"

const starColor: string = "#f59e0b";

export interface RatingProps {
    filled: boolean;
}

const StarIcon = ({ filled }: RatingProps) => (
    <svg
        xmlns="http://www.w3.org/2000/svg"
        viewBox="0 0 24 24"
        fill={filled ? starColor : "none"}
        stroke="#f59e0b"
        strokeWidth="2"
        strokeLinecap="round"
        strokeLinejoin="round"
        className="feather feather-star star-size"
    >
        <polygon points="12 2 15.09 8.26 22 9.27 17 14.14 18.18 21.02 12 17.77 5.82 21.02 7 14.14 2 9.27 8.91 8.26 12 2" />
    </svg>
);

export default StarIcon;