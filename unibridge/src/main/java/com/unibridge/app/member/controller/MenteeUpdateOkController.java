package com.unibridge.app.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.unibridge.app.Execute;
import com.unibridge.app.Result;
import com.unibridge.app.member.dao.MemberDAO;
import com.unibridge.app.member.dto.MemberDTO;

public class MenteeUpdateOkController implements Execute {
	
	private Result outResult = new Result();

    @Override
    public Result execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    		String method = request.getMethod().toUpperCase();
        Result result = new Result(); // 필드 변수 대신 지역 변수 사용 권장

        switch (method) {
            case "GET":
                System.out.println("doGet 실행: 수정 폼 출력");
                result.setPath("/app/user/mentee/myPage/userManage/userModify.jsp");
                result.setRedirect(false); // Forward로 화면 출력
                break; // 👈 이게 없어서 doPost까지 실행되었던 것임!

            case "POST":
                System.out.println("doPost 실행: DB 업데이트 처리");
                // ... DB 업데이트 로직 (생략) ...
                
                // 처리 완료 후 리다이렉트 (ContextPath 중복 주의)
                result.setPath(request.getContextPath() + "/auth/mentee/finishUpdate.my");
                result.setRedirect(true);
                break;
        }

        return result;
    }

	private void doGet(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		MemberDTO memberNumber = (MemberDTO) session.getAttribute("loginUser");
		
		System.out.println("doget 실행 됨");
		outResult.setPath("/app/user/mentee/myPage/userManage/userModify.jsp");
	    outResult.setRedirect(false); 
		
	}

	private void doPost(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("[UpdateOk] DB 업데이트 시작");
	    
	    HttpSession session = request.getSession();
	    MemberDTO loginUser = (MemberDTO) session.getAttribute("loginUser");
	    String newPhone = (String) request.getAttribute("newPhoneNumber"); // SubmitController에서 전달

//	    if (loginUser != null && newPhone != null) {
//	        MemberDAO dao = new MemberDAO();
//	        // 실제 DB 업데이트 실행 (DAO에 해당 메서드가 구현되어 있어야 함)
//	        dao.updatePhoneNumber(loginUser.getMemberNumber(), newPhone);
//	        
//	        // 세션 정보 갱신 (선택 사항)
//	        loginUser.setMemberPhone(newPhone);
//	        session.setAttribute("loginUser", loginUser);
//	        
//	        System.out.println("[UpdateOk] 휴대폰 번호 변경 완료: " + newPhone);
//	    }
//
//	    // 수정 완료 후 마이페이지 메인으로 리다이렉트
	    outResult.setPath(request.getContextPath() + "/auth/mentee/finishUpdate.my");
	    outResult.setRedirect(true);
		
	}

}
