package com.unibridge.app.menteeBoardComment.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.unibridge.app.Execute;
import com.unibridge.app.Result;
import com.unibridge.app.menteeBoardComment.dao.MenteeBoardCommentDAO;

public class MenteeBoardCommentDeleteOkController implements Execute {

    @Override
    public Result execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        System.out.println("===MenteeBoardCommentDeleteOkController===");
        Result result = new Result();

        int menteeComId   = Integer.parseInt(request.getParameter("menteeComId"));
        int menteeBoardId = Integer.parseInt(request.getParameter("menteeBoardId"));

        new MenteeBoardCommentDAO().deleteComment(menteeComId);
        System.out.println("댓글 삭제 완료");

        result.setPath(request.getContextPath()
                + "/mentee/menteeBoard/MenteeBoardRead.meb?MenteeBoardNumber=" + menteeBoardId);
        result.setRedirect(true);
        return result;
    }
}
