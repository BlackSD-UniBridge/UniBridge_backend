package com.unibridge.app.file.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.unibridge.app.Execute;
import com.unibridge.app.Result;

public class FileDisplayController implements Execute {
	
	private static final String UPLOAD_PATH = "C:/upload/profile/";

	@Override
	public Result execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String fileName = request.getParameter("fileName");

		if (fileName == null || fileName.trim().isEmpty()) {
			response.sendError(HttpServletResponse.SC_BAD_REQUEST, "fileName 파라미터가 필요합니다.");
			return null;
		}

		File file = new File(UPLOAD_PATH + fileName);


		// MIME 타입 설정 (이미지로 인식하게 함)
		String mimeType = request.getServletContext().getMimeType(file.toString());
		response.setContentType(mimeType != null ? mimeType : "image/jpeg");
		response.setContentLengthLong(file.length());

		// 파일 전송 (FileDownloadController의 5번 로직과 동일하지만 헤더만 없음)
		try (FileInputStream fis = new FileInputStream(file); OutputStream os = response.getOutputStream()) {
			byte[] buffer = new byte[4096];
			int read;
			while ((read = fis.read(buffer)) != -1) {
				os.write(buffer, 0, read);
			}
			os.flush();
		}
		return null;
	}

}
