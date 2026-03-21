package com.unibridge.app.member.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.unibridge.app.Execute;
import com.unibridge.app.Result;
import com.unibridge.app.member.dto.MemberDTO;
import com.unibridge.app.mypage.matching.dao.MatchingDAO;
import com.unibridge.app.mypage.matching.dto.matchingDTO;

public class MentorMatchingController implements Execute{
	
	private Result outResult = new Result();

	@Override
	public Result execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("===MatchingController===");

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
	
	private void doGet(HttpServletRequest request, HttpServletResponse response) {

		System.out.println("[MatchingController] GET - 매칭 정보 페이지");
		
		HttpSession session = request.getSession();
		MemberDTO loginUser = (MemberDTO) session.getAttribute("loginUser");
	    System.out.println("MenteeMange컨트롤러 : " + loginUser.getMemberNumber());
	    
	    // 2. DAO를 호출하여 DB 데이터 조회
	    int memberNumber = loginUser.getMemberNumber();
	    System.out.println("조회 시도 회원번호 : " + memberNumber);
	    System.out.println("회원 타입 : "+ loginUser.getMemberType());
	    
	    // MatchingDAO 객체 생성 (import 필요)
	    MatchingDAO matchingDAO = new MatchingDAO();
	    
	    // '매칭됨' 상태인 리스트 가져오기
	    List<matchingDTO> matchingList = matchingDAO.selectMatchingList(memberNumber);
	    
	    // 3. 데이터 유무에 따른 분기 처리
	    if (matchingList == null || matchingList.isEmpty()) {
	        System.out.println("매칭된 데이터가 없음: 안내 페이지로 이동");
	        
	        // 데이터가 없을 때 보여줄 JSP 경로 (예: noMatching.jsp)
	        // 또는 기존 JSP 내에서 처리하고 싶다면 메시지만 담아서 보낼 수도 있습니다.
	        // 회원 타입의 따라 출력
	        if(loginUser.getMemberType().equals("MENTOR")) {
	        	outResult.setPath("/app/user/mentor/myPage/userMatching/nonMatching.jsp"); 	        	
	        }else {
	        	outResult.setPath("/app/user/mentee/myPage/userMatching/nonMatching.jsp"); 	        		        	
	        }
	    } else {
	        System.out.println("조회된 매칭 수: " + matchingList.size());
	        for(matchingDTO str : matchingList) {
	        	System.out.println("매칭 정보 : "+  str );	        	
	        }
	        
	        // 데이터가 있을 때 정상적으로 리스트 전달
	        request.setAttribute("matchingList", matchingList);
	        // 회원 타입의 따라 출력
	        if(loginUser.getMemberType().equals("MENTOR")) {
	        	outResult.setPath("/app/user/mentor/myPage/userMatching/userMatching.jsp"); 	        	
	        }else {
	        	outResult.setPath("/app/user/mentee/myPage/userMatching/userMatching.jsp"); 	        		        	
	        }
	    }

	    outResult.setRedirect(false); // 둘 다 forward 처리 (필요시)
	    
	    System.out.println("request.getContextPath() :" + request.getContextPath());
		
	}

	private void doPost(HttpServletRequest request, HttpServletResponse response) {
		
		System.out.println("[MatchingController] POST - 매칭 취소 처리 시작");
	    
	    // 1. JSP(JS)에서 보낸 매칭 번호 파라미터 가져오기
	    // 변수명은 JS에서 보낼 이름과 일치해야 합니다. (matchinNumber)
		HttpSession session = request.getSession();
		MemberDTO loginUser = (MemberDTO) session.getAttribute("loginUser");
	    System.out.println("MenteeMange컨트롤러 : " + loginUser.getMemberNumber());
	    
	    try {
			String reason = request.getParameter("matchingCanReason");
			System.out.println("전달받은 취소사유: " + reason);
			
			int memberNumber = loginUser.getMemberNumber();
			
			MatchingDAO matchingDAO = new MatchingDAO();
			// '매칭됨' 상태인 리스트 가져오기
			List<matchingDTO> matchingList = matchingDAO.selectMatchingList(memberNumber);
			
			if (matchingList.get(0) != null) {
			    
				// 2. 1024자 제한 방어 코드 (DB 에러 방지)
			    if (reason != null && reason.length() > 1024) {
			        reason = reason.substring(0, 1024);
			        System.out.println("[경고] 사유가 1024자를 초과하여 절삭되었습니다.");
			    }

			    // 3. DTO 객체 생성 및 데이터 세팅
			    matchingDTO dto = new matchingDTO();
			    dto.setMatchinNumber(matchingList.get(0).getMatchinNumber());
			    dto.setMatchingCanReason(reason);
			    
			    // 4. DAO 호출하여 DB 업데이트
			    matchingDAO.updateMatchingCancel(dto);
			    
			    System.out.println("매칭 취소 완료");
			}
			
			// 3. 처리 완료 후 다시 매칭 리스트 페이지로 이동 (Redirect)
			// Redirect를 해야 업데이트된 결과(취소된 행은 안 보임)가 반영된 doGet이 실행됩니다.
			// 회원 타입의 따라 출력
	        if(loginUser.getMemberType().equals("MENTOR")) {	        	
	        	outResult.setPath(request.getContextPath() + "/mvc/auth/mentor/matching.my");
	        }else {
	        	outResult.setPath(request.getContextPath() + "/mvc/auth/mentee/matching.my"); 	        		        	
	        }
			outResult.setRedirect(true);
		} catch (Exception e) {
			System.out.println("[에러] 매칭 취소 중 오류 발생: " + e.getMessage());
	        e.printStackTrace();
		}
		
	}

}
