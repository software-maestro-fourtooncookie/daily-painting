import { MouseEventHandler } from "react";
import "./DiaryImageLayout.css";

// TODO: 추후에는 상수로 분리하여 관리할 수 있도록 수정
const IMAGE_WIDTH = 512;
const IMAGE_HEIGHT = 512;


export interface DiaryImageLayoutProps {
    imageUrl: string;
    onImageDownloadClick: MouseEventHandler<HTMLButtonElement>;
}

const DiaryImageLayout = (props: DiaryImageLayoutProps) => {
    const {imageUrl, onImageDownloadClick, ...rest} = props;
    
    return (
        <div className="diary-image-layout">
            <img
                src={imageUrl}
                style={{
                    aspectRatio: IMAGE_WIDTH / IMAGE_HEIGHT,
                    objectFit: "cover"
                }}
                width={IMAGE_WIDTH} height={IMAGE_HEIGHT}
                className="diary-image"
            />
            <button className="diary-image-download-button" onClick={onImageDownloadClick}>
                Download
            </button>
        </div>
    );
}

export default DiaryImageLayout;