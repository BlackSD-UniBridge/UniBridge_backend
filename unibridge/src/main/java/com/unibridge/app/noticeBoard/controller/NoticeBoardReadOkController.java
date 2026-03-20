package com.unibridge.app.noticeBoard.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.unibridge.app.Execute;
import com.unibridge.app.Result;
import com.unibridge.app.file.dao.FileDAO;
import com.unibridge.app.file.dto.FileDTO;
import com.unibridge.app.noticeBoard.dao.NoticeBoardDAO;
import com.unibridge.app.noticeBoard.dto.NoticeBoardListDTO;

public class NoticeBoardReadOkController implements Execute {

	@Override
	public Result execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("게시글 상세 페이지 이동 시작");
		
		Result result = new Result();
        NoticeBoardDAO noticeBoardDAO = new NoticeBoardDAO();
        NoticeBoardListDTO dto = null;
        
        
		//대회 카드 클릭
        String contestNumberStr = request.getParameter("contestNumber");
        if (contestNumberStr != null && !contestNumberStr.trim().isEmpty()) {
            System.out.println("contestNumber 파라미터 감지: " + contestNumberStr);

            try {
                int contestNumber = Integer.parseInt(contestNumberStr);
                // contest_number FK 로 연결된 공지사항 조회
                dto = noticeBoardDAO.selectByContestNumber(contestNumber);

                if (dto == null) {
                    // 해당 대회에 연결된 공지사항이 없으면 목록으로 이동
                    System.out.println("대회(" + contestNumber + ")에 연결된 공지사항 없음 → 목록으로 이동");
                    result.setPath(request.getContextPath() + "/noticeBoardList.ntb");
                    result.setRedirect(true);
                    return result;
                }

            } catch (NumberFormatException e) {
                System.out.println("contestNumber 파싱 오류: " + contestNumberStr);
                result.setPath(request.getContextPath() + "/noticeBoardList.ntb");
                result.setRedirect(true);
                return result;
            }

        } else {
        	
            //게시판 목록 클릭
            String noticeBoardNumberStr = request.getParameter("noticeBoardNumber");

            if (noticeBoardNumberStr == null || noticeBoardNumberStr.trim().isEmpty()) {
                System.out.println("noticeBoardNumber 값이 없습니다");
                result.setPath(request.getContextPath() + "/noticeBoardList.ntb");
                result.setRedirect(true);
                return result;
            }

            try {
                int noticeBoardNumber = Integer.parseInt(noticeBoardNumberStr);
                dto = noticeBoardDAO.selectBoard(noticeBoardNumber);
            } catch (NumberFormatException e) {
                System.out.println("noticeBoardNumber 파싱 오류: " + noticeBoardNumberStr);
                result.setPath(request.getContextPath() + "/noticeBoardList.ntb");
                result.setRedirect(true);
                return result;
            }

            if (dto == null) {
                System.out.println("존재하지 않는 게시물 → 목록으로 이동");
                result.setPath(request.getContextPath() + "/noticeBoardList.ntb");
                result.setRedirect(true);
                return result;
            }
        }
        
        //첨부파일 조회
        if (dto.getFileNumber() > 0) {
            FileDAO fileDAO = new FileDAO();
            FileDTO fileDTO = fileDAO.selectFile(dto.getFileNumber());
            if (fileDTO != null) {
                System.out.println("첨부파일 확인: " + fileDTO);
                request.setAttribute("attachedFile", fileDTO);
            }
        }
				
		
		//조회시 조회수 증가 (관리자 본인 제외)
		Integer adminNumber = (Integer) request.getSession().getAttribute("adminNumber");
		if (adminNumber == null || adminNumber != dto.getAdminNumber()) {
            noticeBoardDAO.updateClick(dto.getNoticeBoardNumber());
        }
		
		request.setAttribute("noticeBoard", dto);
		result.setPath("/app/user/common/noticeBoardRead.jsp");
		result.setRedirect(false);
		
		return result;
	}
	
}


















