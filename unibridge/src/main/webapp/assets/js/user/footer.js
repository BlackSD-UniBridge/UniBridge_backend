// footer.js

(function() {
    // 1. 현재 브라우저 주소창의 경로를 분석하여 'frontend' 폴더까지의 루트 경로를 찾습니다.
    const getBase = () => {
        const path = window.location.pathname;
        // 'frontend' 문자열이 포함된 위치를 찾아 그 앞까지만 자릅니다.
        if (path.includes('/unibridge')) {
            return window.location.origin + path.substring(0, path.indexOf('/unibridge') + 10);
        }
        // 만약 frontend 폴더가 주소에 없다면 현재 호스트 주소만 사용합니다.
        return window.location.origin;
    };

    const base = getBase();

    const getFooterTemplate = () => {
        return `
        <footer class="footerWrap">
            <div class="footerInner">
                <a href="${base}/main.jsp" class="footerLogo">
                    <img src="${base}/assets/img/UniBridge.png" alt="UniBridge" />
                </a>

                <div class="footerDivider"></div>

                <div class="footerInfoWrap">
                    <p class="footerInfoItem"><span>전화번호 : </span>010-1234-5678</p>
                    <p class="footerInfoItem"><span>사업번호 : </span>111-22-3333</p>
                    <p class="footerInfoItem"><span>이메일 : </span>test@naver.com</p>
                    <p class="footerInfoItem"><span>주소 : </span>서울특별시 강남구 테헤란로 146 번악빌딩 3,4층</p>
                    <p class="footerInfoItem"><span>대표자 : </span>김재형</p>
                </div>
            </div>
        </footer>`;
    };

    function renderFooter() {
        const mount = document.getElementById('footerContainer');
        if (mount) {
            mount.innerHTML = getFooterTemplate();
        }
    }

    // 초기화 실행
    if (document.readyState === 'loading') {
        document.addEventListener('DOMContentLoaded', renderFooter);
    } else {
        renderFooter();
    }
})();