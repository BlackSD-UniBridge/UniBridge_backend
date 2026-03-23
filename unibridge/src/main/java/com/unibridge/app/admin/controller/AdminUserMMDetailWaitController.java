package com.unibridge.app.admin.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.unibridge.app.Execute;
import com.unibridge.app.Result;
import com.unibridge.app.admin.dao.AdminUserMMDetailDAO;
import com.unibridge.app.admin.dto.AdminUserMMDetailDTO;

public class AdminUserMMDetailWaitController implements Execute {
	AdminUserMMDetailDAO adminUserMMDetailDAO = new AdminUserMMDetailDAO();
	
	@Override
	public Result execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Result result = new Result();
		String method = request.getMethod().toUpperCase();
		
		switch (method) {
		case "GET":
			this.doGet(request, response, result);
			break;
		case "POST":
			this.doPost(request, response, result);
			break;
		default:
			break;
		}
		return result;
	}
	
	private void doGet(HttpServletRequest request, HttpServletResponse response, Result result) throws IOException, ServletException {
		String strUserNumber = request.getParameter("memberNumber");
		if (strUserNumber == null || strUserNumber.isEmpty()) {
			response.sendError(HttpServletResponse.SC_BAD_REQUEST, "유효하지 않은 접근입니다.");
			throw new ServletException("Invalid access detected.");
		}
		int userNumber = Integer.parseInt(strUserNumber);
		
		HttpSession httpSession = request.getSession(false);
		if (httpSession == null) {
			response.sendError(HttpServletResponse.SC_BAD_REQUEST, "유효하지 않은 접근입니다.");
			throw new ServletException("Invalid access detected.");
		}
		
		AdminUserMMDetailDTO adminUserMMDetailDTO = adminUserMMDetailDAO.selectUserMMDetail(userNumber);
		if (adminUserMMDetailDTO == null) {
			response.sendError(HttpServletResponse.SC_BAD_REQUEST, "유저 정보를 찾을 수 없습니다.");
			throw new ServletException("Invalid parameter.");
		}
		
		String memberType = adminUserMMDetailDTO.getMemberType();
		switch (memberType) {
		case "MENTOR": memberType = "멘토";
			break;
		case "MENTEE": memberType = "멘티";
			break;
		default: memberType = "미정";
		}
		
		StringBuilder memberPhone = new StringBuilder(adminUserMMDetailDTO.getMemberPhone());
		memberPhone = memberPhone.insert(3 , "-");
		memberPhone = memberPhone.insert(8 , "-");
		
		String memberGender = adminUserMMDetailDTO.getMemberPhone();
		switch (memberGender) {
		case "M": memberGender = "남";
			break;
		case "W": memberGender = "여";
			break;
		default: memberGender = "선택 안함";
		}
		
		adminUserMMDetailDTO.setMemberType(memberType);
		adminUserMMDetailDTO.setMemberPhone(memberPhone.toString());
		adminUserMMDetailDTO.setMemberGender(memberGender);
		httpSession.setAttribute("memberDetail", adminUserMMDetailDTO);
		
		result.setRedirect(false);
		result.setPath("/app/admin/adminUserManagement/userDetail/userDetailWaiting.jsp");
	}

	private void doPost(HttpServletRequest request, HttpServletResponse response, Result result) {
	}
}
