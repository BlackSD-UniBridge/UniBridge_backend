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
        
        System.out.println("-----------------------------------------");
        System.out.println("[Log] MentoringViewController 진입");
        
        MentoringDAO dao = new MentoringDAO();
        Result result = new Result();
        
        // 1. 파라미터 수신 (WriteOk에서 보낸 ?mentoringNumber=값 을 받음)
        String mentoringNumberParam = request.getParameter("mentoringNumber");
        System.out.println("[Log] 전달받은 파라미터(mentoringNumber): " + mentoringNumberParam);
        
        try {
            // 2. 유효성 검사 (번호가 없으면 마이페이지 홈으로 튕겨냄)
            if (mentoringNumberParam == null || mentoringNumberParam.isEmpty()) {
                System.out.println("[Warn] 파라미터가 누락되었습니다. 마이페이지로 이동합니다.");
                result.setPath(request.getContextPath() + "/auth/mentor/myPage.my"); 
                result.setRedirect(true);
                return result;
            }

            // 3. 숫자 변환 (WriteOk에서 넘겨준 ID 값)
            int id = Integer.parseInt(mentoringNumberParam);
            System.out.println("[Log] DB 조회 시도 ID: " + id);
            
            // 4. DAO 실행 (단일 상세 정보 조회)
            MentoringDTO dto = dao.select(id);
            
            if (dto != null) {
                System.out.println("[Log] DB 조회 성공 - 제목: " + dto.getMentoringTitle());
                
                // JSP에서 ${mentoring.mentoringTitle} 등으로 출력 가능하도록 저장
                request.setAttribute("mentoring", dto);
                
                // 5. 성공 시 상세 페이지 JSP로 이동 (Forward)
                result.setPath("/app/user/mentor/myPage/userMentoring/mentoringView.jsp");
                result.setRedirect(false); 
                System.out.println("[Log] 상세보기 JSP로 포워딩: " + result.getPath());
            } else {
                System.out.println("[Log] DB 조회 결과 없음 - ID: " + id);
                // 데이터가 없으면 마이페이지 메인으로
                result.setPath(request.getContextPath() + "/auth/mentor/myPage.my");
                result.setRedirect(true);
            }

        } catch (NumberFormatException e) {
            System.out.println("[Error] ID 형식이 숫자가 아닙니다: " + mentoringNumberParam);
            result.setPath(request.getContextPath() + "/auth/mentor/myPage.my");
            result.setRedirect(true);
        } catch (Exception e) {
            System.out.println("[Error] 알 수 없는 예외 발생!");
            e.printStackTrace();
            // 최후의 보루: 메인 페이지로 이동
            result.setPath(request.getContextPath() + "/index.jsp");
            result.setRedirect(true);
        }

        System.out.println("[Log] MentoringViewController 종료");
        System.out.println("-----------------------------------------");
        return result;
    }
}