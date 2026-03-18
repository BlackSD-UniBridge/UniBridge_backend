package com.unibridge.app.mypage.mentoring.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.unibridge.app.Execute;
import com.unibridge.app.Result;
import com.unibridge.app.mypage.mentoring.dao.MentoringDAO;
import com.unibridge.app.mypage.mentoring.dto.MentoringDTO;

public class MentoringViewController implements Execute {
    @Override
    public Result execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("[Log] MentoringViewController 진입");
        
        MentoringDAO dao = new MentoringDAO();
        Result result = new Result();
        
        try {
            // 1. 파라미터 수신 확인
            String mentoringNumberParam = request.getParameter("mentoringNumber");
            System.out.println("[Log] 전달받은 mentoringNumber 파라미터: " + mentoringNumberParam);
            
            if (mentoringNumberParam == null || mentoringNumberParam.isEmpty()) {
                System.out.println("[Warn] mentoringNumber가 누락되었습니다.");
                // 필요 시 예외 처리 혹은 리스트로 리다이렉트 로직 추가
            }

            int internalId = Integer.parseInt(mentoringNumberParam);
            System.out.println("[Log] 조회 시도 ID: " + internalId);
            
            // 2. DAO 실행 및 결과 확인
            MentoringDTO dto = dao.select(internalId);
            
            if (dto != null) {
                System.out.println("[Log] DB 조회 성공 - 제목: " + dto.getMentoringTitle());
                // JSP에서 사용할 객체 바인딩
                request.setAttribute("mentoring", dto);
            } else {
                System.out.println("[Log] DB 조회 결과 없음 - ID: " + internalId);
            }

            // 3. 이동 경로 설정
            result.setPath("/app/user/mentor/myPage/userMentoring/mentoringView.jsp");
            result.setRedirect(false); // 데이터를 담아서 가야 하므로 forward
            System.out.println("[Log] 이동 경로 설정(Forward): " + result.getPath());

        } catch (NumberFormatException e) {
            System.out.println("[Error] ID 형식이 올바르지 않습니다(숫자가 아님).");
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("[Error] MentoringViewController 실행 중 알 수 없는 예외 발생!");
            e.printStackTrace();
        }

        return result;
    }
}