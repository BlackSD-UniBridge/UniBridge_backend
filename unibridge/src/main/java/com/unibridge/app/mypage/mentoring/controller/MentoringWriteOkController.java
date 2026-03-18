package com.unibridge.app.mypage.mentoring.controller;

import java.io.File;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.unibridge.app.Execute;
import com.unibridge.app.Result;
import com.unibridge.app.mypage.mentoring.dao.MentoringDAO;
import com.unibridge.app.mypage.mentoring.dto.MentoringDTO;

public class MentoringWriteOkController implements Execute {
    @Override
    public Result execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        System.out.println("=========================================");
        System.out.println("[Log] MentoringWriteOkController 시작");
        
        MentoringDAO mentoringDAO = new MentoringDAO();
        MentoringDTO mentoringDTO = new MentoringDTO();
        Result result = new Result();
        
        HttpSession session = request.getSession();
        Integer loginUserNumber = (Integer) session.getAttribute("memberNumber");

        String uploadPath = request.getServletContext().getRealPath("/") + "upload/";
        int fileSize = 1024 * 1024 * 5; // 5MB

        try {
            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()) {
                uploadDir.mkdirs();
            }

            MultipartRequest multi = new MultipartRequest(request, uploadPath, fileSize, "UTF-8",
                    new DefaultFileRenamePolicy());

            String title = multi.getParameter("mentoringTitle");
            String subject = multi.getParameter("mentoringSubject");
            String goal = multi.getParameter("mentoringPurpose");
            String detail = multi.getParameter("mentoringCurriculum");

            int mentorNumber;
            if (loginUserNumber == null) {
                System.out.println("[Warn] 세션에 유저 정보가 없음 - 테스트용 21번 세팅");
                mentorNumber = 21; // 테스트 시 이미 등록된 번호로 테스트해보려면 21 사용
            } else {
                mentorNumber = loginUserNumber;
            }
            mentoringDTO.setMentorNumber(mentorNumber);

            // [추가] DB Insert 전 중복 등록 여부 체크
            // DAO에 checkAlreadyExists 메서드가 있다고 가정합니다.
            int existingCount = mentoringDAO.checkAlreadyExists(mentorNumber);

            if (existingCount > 0) {
                // 이미 글이 있는 경우: Insert를 하지 않고 알림과 함께 마이페이지로 이동
                System.out.println("[Warn] 멘토 " + mentorNumber + "는 이미 등록된 멘토링이 있음. 등록 중단.");
                
                // 에러 메시지를 쿼리 스트링으로 들고 가거나 세션에 담아 보낼 수 있습니다.
                result.setPath(request.getContextPath() + "/mvc/auth/mentor/myPage.my?error=already_exists");
                result.setRedirect(true);
                return result; 
            }

            // 중복이 없을 때만 실행되는 기존 로직
            mentoringDTO.setSubjectNumber(Integer.parseInt(subject));
            mentoringDTO.setMentoringTitle(title);
            mentoringDTO.setMentoringGoal(goal);
            mentoringDTO.setMentoringDetail(detail);
            mentoringDTO.setFileNumber(1); 

            System.out.println("[Step 6] 중복 없음. DB Insert 실행 중...");
            mentoringDAO.insert(mentoringDTO); 
            
            int createdId = mentoringDTO.getInternalId();
            System.out.println("[Step 6] DB Insert 성공! 생성된 ID: " + createdId);

            // 또는 상대 경로인 "mentoringView.my?mentoringNumber=" + createdId 사용 가능
            result.setPath(request.getContextPath() + "/mvc/auth/mentor/mentoringView.my?mentoringNumber=" + createdId);
            result.setRedirect(true);
            
            System.out.println("[Log] 이동 경로 설정 완료: " + result.getPath());
            return result;

        } catch (Exception e) {
            System.out.println("[Error] 예외 발생: " + e.getMessage());
            e.printStackTrace();
            
            // [수정] 예외 발생 시에도 /mvc가 포함된 경로로 리다이렉트하여 404 방지
            result.setPath(request.getContextPath() + "/mvc/auth/mentor/myPage.my");
            result.setRedirect(true);
            return result;
        } finally {
            System.out.println("[Log] MentoringWriteOkController 종료");
            System.out.println("=========================================");
        }
    }
}