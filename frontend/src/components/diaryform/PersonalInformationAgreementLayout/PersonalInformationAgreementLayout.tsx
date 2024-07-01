import React, { useState } from "react";

import "./PersonalInformationAgreementLayout.css";
import Checkbox from "../../common/Checkbox/Checkbox";

export interface PersonalInformationProps {
    isChecked: boolean;
    onClick: () => void;
}

const PersonalInformationLayout = (props: PersonalInformationProps) => {
    const { isChecked, onClick, ...rest } = props;
    const [isVisible, setIsVisible] = useState(false);

    const handleToolbarClick = () => {
        setIsVisible(!isVisible);
    };

    return (
        <div className="container">
            <div className="toolbar" onClick={handleToolbarClick}>
                개인 일기 저장 및 처리와 품질 개선을 위한 저장 및 활용에 대한
                동의서
            </div>
            {isVisible && (
                <div className="content">
                    <pre>
                        {`
목적
본 동의서는 사용자가 작성한 일기 내용의 저장 및 처리와 품질 개선을 위한 저장 및 활용에 대한 동의와 관련된 사항을 명확히 하기 위해 작성되었습니다.

수집하는 정보
사용자가 작성한 일기 내용

일기 작성 날짜 및 시간
타인의 지식 재산권을 침해하는 내용 또는 본인이나 타인의 개인정보, 비밀정보, 사생활을 침해하는 내용을 포함하지 말아주십시오.

정보의 사용
수집된 일기 내용은 그림일기 생성과 품질 개선을 위한 저장 및 활용을 목적으로 사용됩니다.
수집된 정보는 사용자의 요청에 따라 언제든지 삭제할 수 있습니다.

정보의 보관
사용자의 일기 내용은 보안이 유지되는 서버에 안전하게 저장됩니다.
사용자가 정보 삭제를 요청할 경우, 수집된 모든 정보는 완전히 삭제됩니다.

정보의 공유
사용자의 일기 내용은 어떠한 경우에도 제3자와 공유되지 않습니다.
법적 요구가 있을 경우에만 관련 법률에 따라 정보를 제공할 수 있습니다.

동의 철회
사용자는 언제든지 동의를 철회할 수 있으며, 동의 철회 시 일기 내용이 삭제됩니다.
동의 철회는 고객 지원팀(startingblue0603@gmail.com)으로 연락 주시기 바랍니다.

문의
일기 내용 저장 및 처리 또는 품질 개선을 위한 저장 및 활용에 대한 문의사항은 고객 지원팀(startingblue0603@gmail.com)으로 연락 주시기 바랍니다.
위 내용을 충분히 이해하였으며, 이에 동의합니다.


`}
                    </pre>
                </div>
            )}
            <div className="agree-container">
                <span>개인정보 처리 방침에 동의합니다</span>
                <Checkbox isChecked={isChecked} onClick={onClick} />
            </div>
        </div>
    );
};

export default PersonalInformationLayout;
