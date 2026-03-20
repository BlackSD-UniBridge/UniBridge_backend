package com.unibridge.app.mentorBoardComment.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.unibridge.app.Execute;
import com.unibridge.app.Result;
import com.unibridge.app.member.dto.MemberDTO;
import com.unibridge.app.mentorBoardComment.dao.MentorBoardCommentDAO;
import com.unibridge.app.mentorBoardComment.dto.MentorBoardCommentDTO;

public class MentorBoardCommentUpdateOkController implements Execute {

    @Override
    public Result execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        System.out.println("===MentorBoardCommentUpdateOkController===");
        Result result = new Result();
        
        request.setCharacterEncoding("UTF-8");

        MemberDTO loginUser = (MemberDTO) request.getSession().getAttribute("loginUser");
        if (loginUser == null) {
            result.setPath(request.getContextPath() + "/signin.mem");
            result.setRedirect(true);
            return result;
        }

        int mentorComId   = Integer.parseInt(request.getParameter("mentorComId"));
        int mentorBoardId = Integer.parseInt(request.getParameter("mentorBoardId"));
        String content    = request.getParameter("mentorComContent");

        MentorBoardCommentDTO dto = new MentorBoardCommentDTO();
        dto.setMentorComId(mentorComId);
        dto.setMentorComContent(content.trim());
        dto.setMemberNumber(loginUser.getMemberNumber());

        new MentorBoardCommentDAO().updateComment(dto);
        System.out.println("댓글 수정 완료");

        result.setPath(request.getContextPath()
                + "/mentor/mentorBoard/MentorBoardRead.mob?MentorBoardNumber=" + mentorBoardId);
        result.setRedirect(true);
        return result;
    }
}
