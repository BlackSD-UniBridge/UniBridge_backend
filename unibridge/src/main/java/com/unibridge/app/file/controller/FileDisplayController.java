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

	@Override
	public Result execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String fileName = request.getParameter("fileName");

		// 물리적 경로 설정 (직접 지정하신 경로)
		String filePath = "C:/upload/profile/" + fileName;
		File file = new File(filePath);

		if (!file.exists())
			return null;

		// MIME 타입 설정 (이미지로 인식하게 함)
		String mimeType = request.getServletContext().getMimeType(file.toString());
		response.setContentType(mimeType != null ? mimeType : "image/jpeg");

		// 파일 전송 (FileDownloadController의 5번 로직과 동일하지만 헤더만 없음)
		try (FileInputStream fis = new FileInputStream(file); OutputStream os = response.getOutputStream()) {
			byte[] buffer = new byte[4096];
			int read;
			while ((read = fis.read(buffer)) != -1) {
				os.write(buffer, 0, read);
			}
		}
		return null;
	}

}
