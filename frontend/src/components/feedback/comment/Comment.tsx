import React from "react";
import "./Comment.css"

export interface CommentProps {
    comment: string,
    setComment: (comment: string)=>void
}

const Comment = (props: CommentProps) => {
    const { comment, setComment, ...rest } = props;
    return (
        <div className="comment-container">
            <label className="comment-label" htmlFor="comments">
                추가 의견
            </label>
            <textarea
                className="comment-textarea"
                id="comments"
                value={comment}
                onChange={(e) => setComment(e.target.value)}
            />
        </div>
    )
}

export default Comment;