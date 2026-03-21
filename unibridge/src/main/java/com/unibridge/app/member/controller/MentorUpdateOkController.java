package com.unibridge.app.member.controller;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.unibridge.app.Execute;
import com.unibridge.app.Result;
import com.unibridge.app.member.dao.MemberDAO;
import com.unibridge.app.member.dto.MemberDTO;

public class MentorUpdateOkController implements Execute{

	private Result outResult = new Result();

    @Override
    public Result execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    	System.out.println("--------------MentorMangeController-------------");
    	
        String method = request.getMethod().toUpperCase();

        switch (method) {
            case "GET":
                doGet(request, response);
                break;
            case "POST":
                doPost(request, response);
                break;
        }

        return outResult;
    }

 // [GET] 수정 페이지로 이동할 때 실행 (최초 데이터 로드)
    private void doGet(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("doGet 실행: 수정 폼 출력용 데이터 조회");
        
        HttpSession session = request.getSession();
        MemberDTO loginUser = (MemberDTO) session.getAttribute("loginUser");
        MemberDAO dao = new MemberDAO();

        if (loginUser != null) {
            // DB에서 최신 정보를 조회하여 request에 담기
            Map<String, Object> member = dao.selectMember(loginUser.getMemberNumber());
            request.setAttribute("member", member);
        }

        outResult.setPath("/app/user/mentor/myPage/userManage/userModify.jsp");
        outResult.setRedirect(false); // Forward
    }

    // [POST] 각 폼에서 [변경] 버튼을 눌렀을 때 실행 (데이터 수정)
    private void doPost(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("doPost 실행: DB 업데이트 처리");
        
        HttpSession session = request.getSession();
        MemberDTO loginUser = (MemberDTO) session.getAttribute("loginUser");
        MemberDAO dao = new MemberDAO();
        
        int memberNumber = loginUser.getMemberNumber();
        String updateType = request.getParameter("updateType");

        // 1. 닉네임 수정
        if ("nickname".equals(updateType)) {
            String nickname = request.getParameter("memberNickname");
            
            // memberNumber를 함께 전달하도록 수정
            if (dao.checkNickname(nickname, memberNumber) == 0) { 
                dao.updateNickname(memberNumber, nickname);
                request.setAttribute("updateStatus", "nickname_ok");
            } else {
                request.setAttribute("nickError", "중복된 닉네임입니다.");
            }
        }

        // 2. 비밀번호 수정
        if ("password".equals(updateType)) {
            String pw = request.getParameter("newPw");
            String pwConfirm = request.getParameter("newPwConfirm");
            if (pw != null && pw.equals(pwConfirm) && !pw.isEmpty()) {
                dao.updatePassword(memberNumber, pw);
                request.setAttribute("updateStatus", "password_ok");
            } else {
                request.setAttribute("pwError", "비밀번호가 일치하지 않습니다. / 확인이 필요합니다.");
            }
        }

        // 3. 전화번호 수정 (인증 여부 확인)
        if ("phone".equals(updateType)) {
            Boolean isVerified = (Boolean) session.getAttribute("isPhoneVerified");
            String phone = request.getParameter("memberPhone");
            if (isVerified != null && isVerified) {
                dao.updatePhone(memberNumber, phone);
                session.removeAttribute("isPhoneVerified"); // 인증 초기화
                request.setAttribute("updateStatus", "phone_ok");
            } else {
                request.setAttribute("phoneError", "전화번호 인증이 필요합니다.");
            }
        }

        // 4. 성별 수정
        if ("gender".equals(updateType)) {
            String gender = request.getParameter("memberGender");
            System.out.println("선택된 성별 : "+ gender);
            dao.updateGender(memberNumber, gender);
            request.setAttribute("updateStatus", "gender_ok");
        }

        // 데이터 최신화 후 이동
        request.setAttribute("member", dao.selectMember(memberNumber));
        outResult.setPath("/app/user/mentor/myPage/userManage/userModify.jsp");
        outResult.setRedirect(false);
    }
}
