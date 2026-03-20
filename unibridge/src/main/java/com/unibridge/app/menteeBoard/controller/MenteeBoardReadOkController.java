package com.unibridge.app.menteeBoard.controller;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.unibridge.app.Execute;
import com.unibridge.app.Result;
import com.unibridge.app.member.dto.MemberDTO;
import com.unibridge.app.menteeBoard.dao.MenteeBoardDAO;
import com.unibridge.app.menteeBoard.dto.MenteeBoardListDTO;
import com.unibridge.app.menteeBoardComment.dao.MenteeBoardCommentDAO;
import com.unibridge.app.menteeBoardComment.dto.MenteeBoardCommentDTO;

public class MenteeBoardReadOkController implements Execute {

    @Override
    public Result execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("게시글 상세 페이지 이동 완료");

        Result result = new Result();

        String menteeBoardNumberStr = request.getParameter("MenteeBoardNumber");
        if (menteeBoardNumberStr == null || menteeBoardNumberStr.trim().isEmpty()) {
            result.setPath(request.getContextPath() + "/mentee/menteeBoard/MenteeBoardList.meb");
            result.setRedirect(true);
            return result;
        }

        int menteeBoardNumber = Integer.parseInt(menteeBoardNumberStr);
        MenteeBoardDAO menteeBoardDAO = new MenteeBoardDAO();
        MenteeBoardListDTO menteeBoardListDTO = menteeBoardDAO.selectBoard(menteeBoardNumber);

        if (menteeBoardListDTO == null) {
            result.setPath(request.getContextPath() + "/mentee/menteeBoard/MenteeBoardList.meb");
            result.setRedirect(true);
            return result;
        }

        MemberDTO loginUser = (MemberDTO) request.getSession().getAttribute("loginUser");
        Integer loginMemberNumber = (loginUser != null) ? loginUser.getMemberNumber() : null;

        if (!Objects.equals(loginMemberNumber, menteeBoardListDTO.getMemberNumber())) {
            menteeBoardDAO.updateClick(menteeBoardNumber);
        }

        // ▼ 댓글 목록 조회
        MenteeBoardCommentDAO commentDAO = new MenteeBoardCommentDAO();
        List<MenteeBoardCommentDTO> commentList = commentDAO.selectCommentList(menteeBoardNumber);
        int commentTotal = commentDAO.getCommentTotal(menteeBoardNumber);

        request.setAttribute("MenteeBoard", menteeBoardListDTO);
        request.setAttribute("commentList", commentList);
        request.setAttribute("commentTotal", commentTotal);

        result.setPath("/app/user/mentee/menteeBoard/MenteeBoardDetail.jsp");
        result.setRedirect(false);
        return result;
    }
}
