import './FeedbackThanksPage.css';

const FeedbackThanksPage = () => {
    return (
        <div
            className="flex min-h-100dvh flex-col items-center justify-center bg-background px-4 py-12 sm-px-6 lg-px-8">
            <div className="mx-auto max-w-md text-center">
                <h1 className="mt-4 text-3xl font-bold tracking-tight text-foreground sm-text-4xl">
                    피드백 감사합니다!
                </h1>
                <p className="mt-4 text-muted-foreground">
                    하루그림은 7월 2차 베타를 거쳐 8월에 앱으로 출시될 예정이예요.
                </p>
                <p className="mt-4 text-muted-foreground">
                    앞으로도 많은 관심 부탁드려요!
                </p>
            </div>
        </div>
    );
}

export default FeedbackThanksPage;