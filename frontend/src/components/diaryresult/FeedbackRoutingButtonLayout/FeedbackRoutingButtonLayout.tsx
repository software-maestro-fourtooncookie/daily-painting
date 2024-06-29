import { MouseEventHandler } from 'react';
import './FeedbackRoutingButtonLayout.css';

export interface FeedbackRoutingButtonLayoutProps {
    onFeedbackClick: MouseEventHandler<HTMLButtonElement>;
}

const FeedbackRoutingButtonLayout = (props: FeedbackRoutingButtonLayoutProps) => {
    const {onFeedbackClick, ...rest} = props;
    
    return (
        <button 
            className="feedback-button" 
            onClick={onFeedbackClick}
        >
            Feedback
        </button>
    );
}

export default FeedbackRoutingButtonLayout;