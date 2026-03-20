package com.unibridge.app.mentorBoard.controller;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.unibridge.app.Execute;
import com.unibridge.app.Result;
import com.unibridge.app.member.dto.MemberDTO;
import com.unibridge.app.mentorBoard.dao.MentorBoardDAO;
import com.unibridge.app.mentorBoard.dto.MentorBoardListDTO;
import com.unibridge.app.mentorBoardComment.dao.MentorBoardCommentDAO;
import com.unibridge.app.mentorBoardComment.dto.MentorBoardCommentDTO;

public class MentorBoardReadOkController implements Execute {

    @Override
    public Result execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("게시글 상세 페이지 이동 완료");

        Result result = new Result();

        String mentorBoardNumberStr = request.getParameter("MentorBoardNumber");
        if (mentorBoardNumberStr == null || mentorBoardNumberStr.trim().isEmpty()) {
            result.setPath(request.getContextPath() + "/mentor/mentorBoard/MentorBoardList.mob");
            result.setRedirect(true);
            return result;
        }

        int mentorBoardNumber = Integer.parseInt(mentorBoardNumberStr);
        MentorBoardDAO mentorBoardDAO = new MentorBoardDAO();
        MentorBoardListDTO mentorBoardListDTO = mentorBoardDAO.selectBoard(mentorBoardNumber);

        if (mentorBoardListDTO == null) {
            result.setPath(request.getContextPath() + "/mentor/mentorBoard/MentorBoardList.mob");
            result.setRedirect(true);
            return result;
        }

        MemberDTO loginUser = (MemberDTO) request.getSession().getAttribute("loginUser");
        Integer loginMemberNumber = (loginUser != null) ? loginUser.getMemberNumber() : null;

        if (!Objects.equals(loginMemberNumber, mentorBoardListDTO.getMemberNumber())) {
            mentorBoardDAO.updateClick(mentorBoardNumber);
        }

        // ▼ 댓글 목록 조회 후 attribute 추가
        MentorBoardCommentDAO commentDAO = new MentorBoardCommentDAO();
        List<MentorBoardCommentDTO> commentList = commentDAO.selectCommentList(mentorBoardNumber);
        int commentTotal = commentDAO.getCommentTotal(mentorBoardNumber);

        request.setAttribute("MentorBoard", mentorBoardListDTO);
        request.setAttribute("commentList", commentList);
        request.setAttribute("commentTotal", commentTotal);

        result.setPath("/app/user/mentor/mentorBoard/mentorBoardDetail.jsp");
        result.setRedirect(false);
        return result;
    }
}
