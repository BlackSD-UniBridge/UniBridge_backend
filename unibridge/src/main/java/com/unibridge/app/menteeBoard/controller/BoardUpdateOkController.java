package com.example.app.board.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.app.Execute;
import com.example.app.Result;
import com.example.app.board.dao.BoardDAO;
import com.example.app.board.dto.BoardDTO;
import com.example.app.file.dao.FileDAO;
import com.example.app.file.dto.FileDTO;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.oreilly.servlet.multipart.FilePart;
import com.oreilly.servlet.multipart.MultipartParser;
import com.oreilly.servlet.multipart.ParamPart;
import com.oreilly.servlet.multipart.Part;

public class BoardUpdateOkController implements Execute {

	@Override
	public Result execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		BoardDAO boardDAO = new BoardDAO();
		BoardDTO boardDTO = new BoardDTO();
		FileDAO fileDAO = new FileDAO();
		Result result = new Result();

		final String UPLOAD_PATH = request.getSession().getServletContext().getRealPath("/") + "upload/";
		final int FILE_SIZE = 1024 * 1024 * 5;
		// 1024 byte = 1KB
		// 1024 KB = 1MB * 5 = 5MB

		// MultipartParser 실행
		MultipartParser parser = new MultipartParser(request, FILE_SIZE);
		parser.setEncoding("utf-8");
		System.out.println("MultipartParser 초기화 완료");

		int boardNumber = 0;
		boolean isFileUpload = false;

		// 파일, 텍스트 데이터 처리
		Part part; // multipart 요청안에 들어있는 각 조각을 의미(boardNumber, boardTitle, boardContent, file)
		while ((part = parser.readNextPart()) != null) {
			System.out.println("Part : " + part.getClass().getSimpleName());
	//나는 파일 안쓴다
			if (part.isParam()) { // 현재 part가 일반 텍스트 데이터 인지 확인
				// 텍스트 파라미터 처리
				ParamPart paramPart = (ParamPart) part; // part는 부모타입 Part로 선언, 텍스트전용메소드는 ParamPart에 있기 때문에 다운캐스팅
				String paramName = paramPart.getName(); // input의 name 속성값
				String paramValue = paramPart.getStringValue(); // 사용자가 실제로 입력한 값

				System.out.println("파라미터 : " + paramName + " = " + paramValue);

				if ("boardNumber".equals(paramName)) {
					boardNumber = Integer.parseInt(paramValue);
					boardDTO.setBoardNumber(boardNumber); // dto에 게시글 번호 저장
				} else if ("boardTitle".equals(paramName)) {
					boardDTO.setBoardTitle(paramValue); // dto에 title 제목 저장
				} else if ("boardContent".equals(paramName)) {
					boardDTO.setBoardContent(paramValue); // dto에 내용 저장
				}
			} else if (part.isFile() && !isFileUpload) { // 파일 처리 조건(파일 파트이고, 아직 파일 처리를 하지 않은 경우)
				FilePart filePart = (FilePart) part; // Part 부모, FilePart 있는 파일 관련 메소드를 쓰기 위해 다운캐스팅
				filePart.setRenamePolicy(new DefaultFileRenamePolicy()); // 동일한 파일명이 있을경우 자동으로 이름 변경해주는 정책
				String fileOriginalName = filePart.getFileName(); // 사용자가 업로드한 파일의 원래 이름

				// 기존 파일 삭제
				if (boardNumber != 0) {
					List<FileDTO> existingFiles = fileDAO.select(boardNumber); // 해당 게시글에 연결된 기존 첨부파일 목록을 DB에서 조회
					for (FileDTO file : existingFiles) {
						File oldFile = new File(UPLOAD_PATH, file.getFileSystemName()); // 실제 서버 파일 객체 생성
						if (oldFile.exists()) { // 해당 파일이 실제로 존재하는지 확인
							System.out.println("기존 파일 삭제 : " + oldFile.getAbsolutePath());
							oldFile.delete(); // 파일 삭제
						}
					}
					fileDAO.delete(boardNumber); // 파일 DB에서도 해당 정보 삭제
					System.out.println("기존 파일 db삭제 완료");
				}

				// 새로운 파일 저장
				if (fileOriginalName != null) { // 사용자가 실제 새로운 파일을 선택했을 때만 실행
					String newFileName = System.currentTimeMillis() + "_" + fileOriginalName;
					// System,currentTimeMillis() : 1970년 1월 1일부터 지금까지의 밀리초 시간을 숫자로 반환(파일명의 중복될 확률
					// 줄이기 위함)
					File newFile = new File(UPLOAD_PATH, newFileName); // 실제로 저장할 서버 파일 객체 생성
					filePart.writeTo(newFile); // 실제 업로드 파일을 서버 디스크에 저장, 사용자가 올린 파일 데이터를 newFile 경로에 기록하는 부분

					if (newFile.exists()) {
						System.out.println("새로운 파일 저장 완료 : " + newFile.getAbsolutePath());
						// .getAbsolutePath() : 파일의 전체 경로 출력하는 메소드
					} else {
						System.out.println("새로운 파일 저장 실패 : " + newFile.getAbsolutePath());
					}

					// 새 파일 정보를 db저장
					FileDTO fileDTO = new FileDTO();
					fileDTO.setFileSystemName(newFileName);
					fileDTO.setFileOriginalName(fileOriginalName);
					fileDTO.setBoardNumber(boardNumber);
					System.out.println("새로운 파일 확인 : " + fileDTO);
					fileDAO.insert(fileDTO);
					System.out.println("새로운 파일 DB 저장 완료 : " + fileDTO);

					isFileUpload = true;
				} else {
					System.out.println("업로드된 파일이 없습니다."); // 사용자가 수정은 햇지만 새파일을 선택하지 않은 경우
				}

			}
		}

		// 게시글 업데이트 실행
		boardDTO.setMemberNumber((Integer) request.getSession().getAttribute("memberNumber"));
		boardDAO.updateBoard(boardDTO);
		System.out.println("게시글 수정 완료");

		// 수정 완료 후 페이지 이동
		result.setPath("/board/boardListOk.bo");
		result.setRedirect(true);

		return result;
	}

}
