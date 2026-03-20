package com.unibridge.app.admin.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.unibridge.app.Execute;
import com.unibridge.app.Result;
import com.unibridge.app.admin.dao.AdminDAO;
import com.unibridge.app.admin.dto.AdminDTO;

public class AdminLoginOkController implements Execute{

	@Override
	public Result execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		AdminDTO adminDTO = new AdminDTO();
		AdminDAO adminDAO = new AdminDAO();
		int adminNumber = 0;
		Result result = new Result();
		
		String adminId = request.getParameter("adminId");
		String adminPw = request.getParameter("adminPw");
		HttpSession session = request.getSession();
		String path = null;
		
		adminDTO.setAdminId(adminId);
		adminDTO.setAdminPw(adminPw);
		System.out.println("ID : " + adminDTO.getAdminId() + "PW : " + adminDTO.getAdminPw());
		 
		adminNumber = adminDAO.login(adminDTO);
		System.out.println(adminNumber + " AdminLiginOkController넘버값확인");
		if(adminNumber != -1) {
			path = request.getContextPath() + "/main.admin";
			session.setAttribute("adminNumber", adminNumber);
			System.out.println("세션 : " +  session.getAttribute("adminNumber"));			
		}else {
			path = request.getContextPath() + "/login.admin?login=fail";
		}
		
		System.out.println("path : " + path);
		
		result.setRedirect(true); 
		result.setPath(path);

		return result;
	}
}
