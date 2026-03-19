package com.unibridge.api.admin.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.plaf.synth.SynthScrollBarUI;

import com.google.gson.Gson;
import com.unibridge.api.ApiExecute;
import com.unibridge.api.ApiResult;
import com.unibridge.api.admin.dao.AdminUserMMDAO;
import com.unibridge.api.admin.dto.AdminUserMMDTO;

public class AdminUserMMController implements ApiExecute<String> {
	AdminUserMMDAO adminUserMMDao = new AdminUserMMDAO();
	
	@Override
	public ApiResult<String> execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String method = request.getMethod().toUpperCase();
		ApiResult<String> apiResult = new ApiResult<String>();
		
		
		switch (method) {
		case "GET":
			this.doGet(request, response, apiResult);
			break;
		case "POST":
			this.doPost(request, response, apiResult);
			break;
		default:
			break;
		}
		return apiResult;
	}

	private void doGet(HttpServletRequest request, HttpServletResponse response, ApiResult<String> apiResult) throws IOException {
		List<AdminUserMMDTO> resultUsers = adminUserMMDao.selectAll();
		
		String strJson = new Gson().toJson(resultUsers);
		apiResult.setRawData(strJson);
		apiResult.setContentType("application/json;charset=UTF-8");
	}

	private void doPost(HttpServletRequest request, HttpServletResponse response, ApiResult<String> apiResult) {
		// TODO Auto-generated method stub
		
	}
}
