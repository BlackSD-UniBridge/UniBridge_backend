package com.unibridge.app.mypage.mentoring.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.unibridge.app.Execute;
import com.unibridge.app.Result;
import com.unibridge.app.mypage.mentoring.dao.MentoringDAO;
import com.unibridge.app.mypage.mentoring.dto.MentoringDTO;

public class MentoringModifyOkController implements Execute {
    @Override
    public Result execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("[Log] MentoringModifyOkController 진입");

        MentoringDAO dao = new MentoringDAO();
        MentoringDTO dto = new MentoringDTO();
        Result result = new Result();

        // 1. 파일 업로드 경로 설정
        String uploadPath = request.getServletContext().getRealPath("/") + "upload/";
        System.out.println("[Log] 수정 시 파일 저장 실제 경로: " + uploadPath);
        
        int fileSize = 1024 * 1024 * 5; // 5MB

        try {
            // 2. MultipartRequest 생성 (파일 업로드 처리)
            MultipartRequest multi = new MultipartRequest(request, uploadPath, fileSize, "UTF-8", new DefaultFileRenamePolicy());
            System.out.println("[Log] MultipartRequest 객체 생성 성공 (수정 데이터 수집 준비)");

            // 3. 파라미터 수집 및 로그 출력 (JSP의 name 속성과 일치해야 함)
            String mentoringNumberStr = multi.getParameter("mentoringNumber");
            String title = multi.getParameter("mentoringTitle");
            String purpose = multi.getParameter("mentoringPurpose");
            String curriculum = multi.getParameter("mentoringCurriculum");
            String fileName = multi.getFilesystemName("mentoringFile"); // 새로 업로드된 파일명

            System.out.println("[Log] 수집된 수정 데이터 - ID: " + mentoringNumberStr);
            System.out.println("[Log] 수집된 수정 데이터 - 제목: " + title);
            System.out.println("[Log] 수집된 수정 데이터 - 목적/과목: " + purpose);
            System.out.println("[Log] 수집된 수정 데이터 - 커리큘럼: " + curriculum);
            System.out.println("[Log] 새 업로드 파일명: " + (fileName != null ? fileName : "변경 없음"));

            if (mentoringNumberStr == null) {
                System.out.println("[Error] mentoringNumber 파라미터가 누락되었습니다! (hidden input 확인 필요)");
            }

            // 4. DTO 데이터 세팅
            int mentoringNumber = Integer.parseInt(mentoringNumberStr);
            dto.setInternalId(mentoringNumber); // DTO의 오타(interanlId) 주의
            dto.setMentoringTitle(title);
            dto.setMentoringGoal(purpose);
            dto.setMentoringDetail(curriculum);

            // 5. DB 업데이트 실행
            System.out.println("[Log] DB Update 실행 시도 (ID: " + mentoringNumber + ")");
            dao.update(dto);
            System.out.println("[Log] DB Update 완료");

            // 6. 결과 페이지 설정 (상세보기로 리다이렉트)
            result.setPath(request.getContextPath() + "/mentoringView.my?mentoringNumber=" + mentoringNumber);
            result.setRedirect(true);
            System.out.println("[Log] 리다이렉트 경로: " + result.getPath());
            
        } catch (NumberFormatException e) {
            System.out.println("[Error] ID 형변환 중 오류 발생 (mentoringNumber가 숫자가 아님)");
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("[Error] MentoringModifyOkController 실행 중 예외 발생!");
            System.out.println("[Error] 메시지: " + e.getMessage());
            e.printStackTrace();
        }

        return result;
    }
}