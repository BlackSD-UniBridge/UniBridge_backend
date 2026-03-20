package com.unibridge.app.file.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.unibridge.app.Execute;
import com.unibridge.app.Result;
import com.unibridge.app.file.dao.FileDAO;
import com.unibridge.app.file.dto.FileDTO;

public class FileDownloadController implements Execute {

    @Override
    public Result execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        System.out.println("FileDownloadController 실행");

        // 1. 파라미터 유효성 검사
        String fileNumberStr = request.getParameter("fileNumber");
        if (fileNumberStr == null || fileNumberStr.trim().isEmpty()) {
            System.out.println("fileNumber 파라미터 없음");
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "fileNumber가 필요합니다.");
            return null;
        }

        int fileNumber;
        try {
            fileNumber = Integer.parseInt(fileNumberStr);
        } catch (NumberFormatException e) {
            System.out.println("fileNumber 파싱 오류: " + fileNumberStr);
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "잘못된 fileNumber입니다.");
            return null;
        }

        // 2. DB에서 파일 정보 조회
        FileDAO fileDAO = new FileDAO();
        FileDTO fileDTO = fileDAO.selectFile(fileNumber);
        if (fileDTO == null) {
            System.out.println("파일 정보 없음 (fileNumber=" + fileNumber + ")");
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "파일을 찾을 수 없습니다.");
            return null;
        }

        // 3. 실제 파일 존재 확인
        File file = new File(fileDTO.getFilePath());
        if (!file.exists()) {
            System.out.println("실제 파일 없음: " + fileDTO.getFilePath());
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "파일이 서버에 존재하지 않습니다.");
            return null;
        }

        // 4. 다운로드 응답 헤더 설정
        String encodedName = URLEncoder.encode(fileDTO.getFileOriginalName(), "UTF-8")
                                       .replaceAll("\\+", "%20");  // 공백 처리
        response.setContentType("application/octet-stream");
        response.setHeader("Content-Disposition", "attachment; filename=\"" + encodedName + "\"");
        response.setContentLengthLong(file.length());

        // 5. 파일 스트림 전송
        try (FileInputStream fis = new FileInputStream(file);
             OutputStream os = response.getOutputStream()) {

            byte[] buffer = new byte[4096];
            int bytesRead;
            while ((bytesRead = fis.read(buffer)) != -1) {
                os.write(buffer, 0, bytesRead);
            }
            os.flush();
            System.out.println("파일 전송 완료: " + fileDTO.getFileOriginalName());
        }

        // 6. 스트림 직접 처리했으므로 Result 반환 불필요
        return null;
    }
}








