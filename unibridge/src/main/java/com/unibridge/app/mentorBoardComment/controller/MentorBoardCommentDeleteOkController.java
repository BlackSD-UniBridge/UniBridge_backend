package com.unibridge.app.mentorBoardComment.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.unibridge.app.Execute;
import com.unibridge.app.Result;
import com.unibridge.app.mentorBoardComment.dao.MentorBoardCommentDAO;

public class MentorBoardCommentDeleteOkController implements Execute {

    @Override
    public Result execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        System.out.println("===MentorBoardCommentDeleteOkController===");
        Result result = new Result();

        int mentorComId   = Integer.parseInt(request.getParameter("mentorComId"));
        int mentorBoardId = Integer.parseInt(request.getParameter("mentorBoardId"));

        new MentorBoardCommentDAO().deleteComment(mentorComId);
        System.out.println("댓글 삭제 완료");

        result.setPath(request.getContextPath()
                + "/mentor/mentorBoard/MentorBoardRead.mob?MentorBoardNumber=" + mentorBoardId);
        result.setRedirect(true);
        return result;
    }
}
