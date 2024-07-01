import React, { ChangeEventHandler } from "react";
import "./DiaryContent.css";

export interface DiaryContentProps {
    content: string;
    onContentChange: ChangeEventHandler;
    isShaking: boolean;
}

const DiaryContent = (props: DiaryContentProps) => {
    const { content, onContentChange, isShaking, ...rest } = props;

    return (
        <div className="content">
            <textarea
                className={isShaking ? "content-error" : ""}
                value={content}
                placeholder="오늘의 일기를 작성하세요"
                onChange={onContentChange}
                {...rest}
            />
        </div>
    );
};

export default DiaryContent;
