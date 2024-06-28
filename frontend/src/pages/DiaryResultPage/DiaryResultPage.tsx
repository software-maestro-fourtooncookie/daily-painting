// import * as S from '';
import './DiaryResultPage.css';

import DiaryImageLayout from "../../components/diaryresult/DiaryImageLayout/DiaryImageLayout";
import FeedbackRoutingButtonLayout from "../../components/diaryresult/FeedbackRoutingButtonLayout/FeedbackRoutingButtonLayout";
import { useNavigate, NavigateFunction, useLocation } from 'react-router-dom';
import { MouseEventHandler } from 'react';

// TODO: 추후에는 상수로 분리하여 관리할 수 있도록 수정
const FeedbackPageUrl = '/feedback'; // TODO: Update this URL to the correct feedback page URL
const ApiUrl = 'http://localhost:8080/proxy'; // TODO: Update this URL to the correct API URL

const DiaryResultPage = () => { 
    const {state} = useLocation();
    const {imageUrl, diaryId, ...rest} = state;

    console.log("DiaryResultPage 접속")
    console.log("imageUrl: " + imageUrl)
    console.log("diaryId: " + diaryId)

    const navigate: NavigateFunction = useNavigate();

    const handleImageDownloadClick: MouseEventHandler<HTMLButtonElement> = () => {
        const proxyUrl = ApiUrl + `?url=${encodeURIComponent(imageUrl)}`;
        fetch(proxyUrl, { method: 'GET' })
            .then(response => {
                if (!response.ok) {
                    throw new Error('Network response was not ok.');
                }
                return response.blob();
            })
            .then(blob => {
                const url = URL.createObjectURL(blob);
                const link = document.createElement('a');
                link.href = url;
                link.download = 'image.jpg';
                link.click();
                URL.revokeObjectURL(url);
            })
            .catch(error => {
                console.error('Error downloading image:', error);
            });
    };

    const handleFeedbackClick: MouseEventHandler<HTMLButtonElement> = () => { // TODO: FeedbackPageUrl에 맞추어서 수정 필요
        navigate(FeedbackPageUrl, { state: { diaryId: diaryId } }); 
    };

    
    return (
    <div className="diary-result-page">
        <DiaryImageLayout imageUrl={imageUrl} onImageDownloadClick={handleImageDownloadClick}/>
        <FeedbackRoutingButtonLayout onFeedbackClick={handleFeedbackClick}/>
    </div>
    );
}

export default DiaryResultPage;