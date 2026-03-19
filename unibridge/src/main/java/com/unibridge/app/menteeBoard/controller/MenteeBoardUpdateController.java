package com.unibridge.app.menteeBoard.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.unibridge.app.Execute;
import com.unibridge.app.Result;
import com.unibridge.app.menteeBoard.dao.MenteeBoardDAO;

public class MenteeBoardUpdateController implements Execute {

	@Override
	public Result execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("===MenteeBoardUpdateController 실행===");
		int MenteeBoardNumber = Integer.valueOf(request.getParameter("MenteeBoardNumber"));
		MenteeBoardDAO MenteeBoardDAO = new MenteeBoardDAO();
		Result result = new Result();
		System.out.println("MenteeBoardNumber 값 : " + MenteeBoardNumber);
		request.setAttribute("MenteeBoard", MenteeBoardDAO.selectBoard(MenteeBoardNumber));

		result.setPath("/app/user/mentee/menteeBoard/MenteeBoardModify.jsp");
		result.setRedirect(false);

		return result;
	}

}
