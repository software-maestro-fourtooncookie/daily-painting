import React from "react";
import "./SubmitButton.css";

export interface SubmitButtonProps {
    loading: boolean;
    isDisabled: boolean;
}

const SubmitButton = (props: SubmitButtonProps) => {
    const { loading, isDisabled } = props;

    return (
        <div className="submit-button">
            <button type="submit" disabled={isDisabled}>
                {loading ? "저장중..." : "일기 작성"}
            </button>
        </div>
    );
};

export default SubmitButton;
