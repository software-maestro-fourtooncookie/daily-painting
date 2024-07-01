import React from "react";
import "./Checkbox.css";

export interface CheckboxProps {
    isChecked: boolean;
    onClick: () => void;
}

const Checkbox = (props: CheckboxProps) => {
    const { isChecked, onClick } = props;

    return (
        <div className="checkbox-container">
            <input type="checkbox" checked={isChecked} onChange={onClick} />
        </div>
    );
};

export default Checkbox;
