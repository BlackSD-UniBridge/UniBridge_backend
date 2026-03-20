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
		
		String menteeBoardNumberStr = request.getParameter("MenteeBoardNumber");
		if (menteeBoardNumberStr == null || menteeBoardNumberStr.trim().isEmpty()) {
            System.out.println("MenteeBoardNumber 값이 없습니다");
            Result result = new Result();
            result.setPath(request.getContextPath() + "/mentee/menteeBoard/MenteeBoardList.meb");
            result.setRedirect(true);
            return result;
        }
		
		int MenteeBoardNumber = Integer.valueOf(request.getParameter("MenteeBoardNumber"));
		System.out.println("MenteeBoardNumber 값 : " + MenteeBoardNumber);
		
		MenteeBoardDAO MenteeBoardDAO = new MenteeBoardDAO();
		Result result = new Result();
		request.setAttribute("MenteeBoard", MenteeBoardDAO.selectBoard(MenteeBoardNumber));

		result.setPath("/app/user/mentee/menteeBoard/MenteeBoardModify.jsp");
		result.setRedirect(false);

		return result;
	}

}
