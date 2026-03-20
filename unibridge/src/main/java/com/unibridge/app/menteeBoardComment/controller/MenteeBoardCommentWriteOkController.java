package com.unibridge.app.menteeBoardComment.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.unibridge.app.Execute;
import com.unibridge.app.Result;
import com.unibridge.app.member.dto.MemberDTO;
import com.unibridge.app.menteeBoardComment.dao.MenteeBoardCommentDAO;
import com.unibridge.app.menteeBoardComment.dto.MenteeBoardCommentDTO;

public class MenteeBoardCommentWriteOkController implements Execute {

    @Override
    public Result execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        System.out.println("===MenteeBoardCommentWriteOkController===");
        Result result = new Result();
        
        request.setCharacterEncoding("UTF-8");

        MemberDTO loginUser = (MemberDTO) request.getSession().getAttribute("loginUser");
        if (loginUser == null) {
            result.setPath(request.getContextPath() + "/signin.mem");
            result.setRedirect(true);
            return result;
        }

        int menteeBoardId = Integer.parseInt(request.getParameter("menteeBoardId"));
        String content    = request.getParameter("menteeComContent");

        if (content == null || content.trim().isEmpty()) {
            result.setPath(request.getContextPath()
                    + "/mentee/menteeBoard/MenteeBoardRead.meb?MenteeBoardNumber=" + menteeBoardId);
            result.setRedirect(true);
            return result;
        }

        MenteeBoardCommentDTO dto = new MenteeBoardCommentDTO();
        dto.setMenteeBoardId(menteeBoardId);
        dto.setMenteeComContent(content.trim());
        dto.setMemberNumber(loginUser.getMemberNumber());

        new MenteeBoardCommentDAO().insertComment(dto);
        System.out.println("댓글 등록 완료");

        result.setPath(request.getContextPath()
                + "/mentee/menteeBoard/MenteeBoardRead.meb?MenteeBoardNumber=" + menteeBoardId);
        result.setRedirect(true);
        return result;
    }
}
