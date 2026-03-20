package com.unibridge.app.main.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.unibridge.app.Execute;
import com.unibridge.app.Result;
import com.unibridge.app.main.dao.MainDAO;
import com.unibridge.app.main.dto.MainDTO.CompanyDTO;
import com.unibridge.app.main.dto.MainDTO.ContestDTO;
import com.unibridge.app.main.dto.MainDTO.MentorCardDTO;

public class MainController implements Execute {

    private MainDAO mainDAO = new MainDAO();
    private Result  outResult = new Result();

    @Override
    public Result execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String method = request.getMethod().toUpperCase();

        switch (method) {
            case "GET":
                this.doGet(request, response);
                break;
            default:
                // 메인페이지는 GET만 사용
                response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
                break;
        }

        return outResult;
    }

    /**
     * 메인 페이지 GET 요청 처리
     */
    private void doGet(HttpServletRequest request, HttpServletResponse response) {

        // 1. 진행중인 대회 목록 조회
        List<ContestDTO> contestList = mainDAO.getContestList();
        request.setAttribute("contestList", contestList);

        // 2. 추천 멘토 목록 조회
        List<MentorCardDTO> mentorCardList = mainDAO.getMentorCardList();
        request.setAttribute("mentorCardList", mentorCardList);
        
        // 3. 취업 회사 목록
        List<CompanyDTO> companyList = mainDAO.getCompanyList();
        request.setAttribute("companyList", companyList);

        // 3. main.jsp로 forward (redirect 아님)
        outResult.setPath("/main.jsp");
        outResult.setRedirect(false);
    }
}
