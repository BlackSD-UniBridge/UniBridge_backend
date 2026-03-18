package com.unibridge.app.mypage.mentoring.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.unibridge.app.Execute;
import com.unibridge.app.Result;
import com.unibridge.app.mypage.mentoring.dao.MentoringDAO;

public class MentoringDeleteOkController implements Execute {
    @Override
    public Result execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("[Log] MentoringDeleteOkController 진입");
        
        MentoringDAO dao = new MentoringDAO();
        Result result = new Result();

        try {
            // 1. 삭제할 게시글 번호 파라미터 수신 및 확인
            String mentoringNumberParam = request.getParameter("mentoringNumber");
            System.out.println("[Log] 삭제 요청된 mentoringNumber: " + mentoringNumberParam);

            if (mentoringNumberParam == null || mentoringNumberParam.isEmpty()) {
                System.out.println("[Warn] 삭제할 ID(mentoringNumber)가 전달되지 않았습니다.");
                // 필요 시 예외 처리 로직 추가
            }

            int internalId = Integer.parseInt(mentoringNumberParam);
            System.out.println("[Log] DB 삭제 실행 시도 (ID: " + internalId + ")");

            // 2. DAO 실행 (이전에 만든 DAO의 delete 메서드에서 영향받은 행 수를 찍도록 설계됨)
            dao.delete(internalId);
            System.out.println("[Log] DB 삭제 프로세스 완료");

            // 3. 결과 페이지 설정 (삭제 후 목록으로 리다이렉트)
            result.setPath(request.getContextPath() + "/mentoring.my");
            result.setRedirect(true); 
            System.out.println("[Log] 리다이렉트 경로 설정: " + result.getPath());

        } catch (NumberFormatException e) {
            System.out.println("[Error] ID 형식이 올바르지 않아 삭제를 중단합니다.");
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("[Error] MentoringDeleteOkController 실행 중 예외 발생!");
            e.printStackTrace();
        }

        return result;
    }
}