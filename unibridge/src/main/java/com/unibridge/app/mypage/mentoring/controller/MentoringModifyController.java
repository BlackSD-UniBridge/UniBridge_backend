package com.unibridge.app.mypage.mentoring.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.unibridge.app.Execute;
import com.unibridge.app.Result;
import com.unibridge.app.mypage.mentoring.dao.MentoringDAO;
import com.unibridge.app.mypage.mentoring.dto.MentoringDTO;

public class MentoringModifyController implements Execute {

    @Override
    public Result execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        System.out.println("[Log] MentoringModifyController 진입");
        
        Result result = new Result();
        MentoringDAO mentoringDAO = new MentoringDAO();

        try {
            // 1. 수정할 게시글 번호 파라미터 수신 및 확인
            String mentoringNumberStr = request.getParameter("mentoringNumber");
            System.out.println("[Log] 전달받은 mentoringNumber: " + mentoringNumberStr);

            if (mentoringNumberStr == null || mentoringNumberStr.isEmpty()) {
                System.out.println("[Warn] mentoringNumber 파라미터가 없습니다.");
                // 필요 시 예외를 던지거나 특정 페이지로 리다이렉트 설정
            }

            int internalId = Integer.parseInt(mentoringNumberStr);
            System.out.println("[Log] 수정 데이터 로드 시도 (ID: " + internalId + ")");

            // 2. DAO를 통해 기존 데이터 조회
            MentoringDTO mentoringDTO = mentoringDAO.select(internalId);

            // 3. 조회 결과 검증 및 데이터 전달
            if (mentoringDTO != null) {
                System.out.println("[Log] 데이터 조회 성공 - 제목: " + mentoringDTO.getMentoringTitle());
                
                // JSP에서 ${mentoring.xxx}로 사용하기 위해 request에 저장
                request.setAttribute("mentoring", mentoringDTO);
                
                // 4. 결과 객체(Result) 설정 및 이동 경로 지정
                result.setPath("/app/user/mentor/myPage/userMentoring/mentoringModify.jsp");
                result.setRedirect(false); // 데이터를 유지해야 하므로 Forward
                System.out.println("[Log] 수정 페이지(JSP)로 Forward 설정 완료");
            } else {
                System.out.println("[Log] 해당 ID(" + internalId + ")의 데이터를 찾을 수 없습니다.");
            }

        } catch (NumberFormatException e) {
            System.out.println("[Error] ID 형식이 숫자가 아닙니다.");
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("[Error] MentoringModifyController 실행 중 예외 발생!");
            e.printStackTrace();
        }

        return result;
    }
}