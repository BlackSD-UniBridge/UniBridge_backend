package com.unibridge.api.admin.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.unibridge.api.ApiExecute;
import com.unibridge.api.ApiResult;
import com.unibridge.api.admin.dao.AdminUserMMDetailDAO;
import com.unibridge.api.admin.dto.AdminUserMMDTO;

public class AdminUserMMDetailController implements ApiExecute<String> {
	AdminUserMMDetailDAO adminUserMMDetailDAO = new AdminUserMMDetailDAO();
	
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
	}

	private void doPost(HttpServletRequest request, HttpServletResponse response, ApiResult<String> apiResult) throws IOException {
		String requestURI = request.getRequestURI();
		String target = extractTargetPath(requestURI);
		switch (target) {
		case  "userMM/updateUserPendingKill.admin":
		case "/userMM/updateUserPendingKill.admin":
			apiResult = updateUserPendingKill(request, response, apiResult);
			break;
		case  "userMM/acceptUserStateBySurvey.admin":
		case "/userMM/acceptUserStateBySurvey.admin":
			apiResult = acceptUserStateBySurvey(request, response, apiResult);
			break;
		case  "userMM/rejectUserStateBySurvey.admin":
		case "/userMM/rejectUserStateBySurvey.admin":
			apiResult = rejectUserStateBySurvey(request, response, apiResult);
			break;
		default:
			break;
		}
	}
	
	private ApiResult<String> updateUserPendingKill(HttpServletRequest request, HttpServletResponse response, ApiResult<String> apiResult) throws IOException {
		Map<String, Object> respMap = new HashMap<String, Object>();
		apiResult.setContentType("application/json;charset=UTF-8");
		
	    Map<String, Object> bodyJson = AdminUserMMDetailController.extractBodyToJson(request); 
	    
		String strMemberNumber = bodyJson.get("memberNumber").toString();
		if (strMemberNumber == null || strMemberNumber.isEmpty()) {
			respMap.put("status", "error");
			apiResult.setRawData(new Gson().toJson(respMap));
			return apiResult;
		}
		int memberNubmer = (int)Float.parseFloat(strMemberNumber);
		
		int udpateResult = this.adminUserMMDetailDAO.updateMemberStatePendingKill(memberNubmer);
		if (udpateResult == -1) {
			respMap.put("status", "error");
			apiResult.setRawData(new Gson().toJson(respMap));
			return apiResult;
		}
		
		if (udpateResult == 0) {
			respMap.put("status", "error");
			respMap.put("message", "Cannot delete this account because the member is currently matched.");
			apiResult.setRawData(new Gson().toJson(respMap));
			return apiResult;
		}
		
		AdminUserMMDTO userMMDetail = this.adminUserMMDetailDAO.selectOneUser(memberNubmer);
		if (userMMDetail == null) {
			respMap.put("status", "warning");
			respMap.put("messgae", "Updated user pending kill but search user failed.");
			apiResult.setRawData(new Gson().toJson(respMap));
			return apiResult;
		}
		
		respMap.put("status", "ok");
		respMap.put("detail", userMMDetail);
		apiResult.setRawData(new Gson().toJson(respMap));
		return apiResult;
	}
	
	private ApiResult<String> acceptUserStateBySurvey(HttpServletRequest request, HttpServletResponse response, ApiResult<String> apiResult) throws IOException {
		Map<String, Object> respMap = new HashMap<String, Object>();
		apiResult.setContentType("application/json;charset=UTF-8");
		
	    Map<String, Object> bodyJson = AdminUserMMDetailController.extractBodyToJson(request); 
		String strMemberNumber = bodyJson.get("memberNumber").toString();
		if (strMemberNumber == null || strMemberNumber.isEmpty()) {
			respMap.put("status", "error");
			apiResult.setRawData(new Gson().toJson(respMap));
			return apiResult;
		}
		int memberNubmer = (int)Float.parseFloat(strMemberNumber);
	    
		int acceptResult = this.adminUserMMDetailDAO.acceptUserStateBySurvey(memberNubmer);
		if (acceptResult == -1) {
			respMap.put("status", "error");
			apiResult.setRawData(new Gson().toJson(respMap));
			return apiResult;
		}
		
		if (acceptResult == 0) {
			respMap.put("status", "error");
			respMap.put("message", "Cannot accept this account because the member is already accepted or anomaly.");
			apiResult.setRawData(new Gson().toJson(respMap));
			return apiResult;
		}
		
	    respMap.put("state", "ok");
		apiResult.setRawData(new Gson().toJson(respMap));
		return apiResult;
	}
	
	private ApiResult<String> rejectUserStateBySurvey(HttpServletRequest request, HttpServletResponse response, ApiResult<String> apiResult) throws IOException {
		Map<String, Object> respMap = new HashMap<String, Object>();
		apiResult.setContentType("application/json;charset=UTF-8");
		
	    Map<String, Object> bodyJson = AdminUserMMDetailController.extractBodyToJson(request); 
		String strMemberNumber = bodyJson.get("memberNumber").toString();
		if (strMemberNumber == null || strMemberNumber.isEmpty()) {
			respMap.put("status", "error");
			apiResult.setRawData(new Gson().toJson(respMap));
			return apiResult;
		}
		int memberNubmer = (int)Float.parseFloat(strMemberNumber);
		
		String rejectReason = bodyJson.get("rejectReason").toString();
		if (rejectReason == null) {
			respMap.put("status", "error");
			respMap.put("message", "rejectReason is empty.");
			apiResult.setRawData(new Gson().toJson(respMap));
			return apiResult;
		}
		
		int rejectResult = this.adminUserMMDetailDAO.rejectUserStateBySurvey(memberNubmer, rejectReason);
		if (rejectResult == -1) {
			respMap.put("status", "error");
			apiResult.setRawData(new Gson().toJson(respMap));
			return apiResult;
		}
		
		if (rejectResult == 0) {
			respMap.put("status", "error");
			respMap.put("message", "Cannot accept this account because the member is already accepted or anomaly.");
			apiResult.setRawData(new Gson().toJson(respMap));
			return apiResult;
		}
		
	    respMap.put("state", "ok");
		apiResult.setRawData(new Gson().toJson(respMap));
		return apiResult;
	}
	
	private static Map<String, Object> extractBodyToJson(HttpServletRequest request) throws IOException {		
	    StringBuilder sb = new StringBuilder();
	    String line;
	    try (BufferedReader reader = request.getReader()) {
	        while ((line = reader.readLine()) != null) {
	            sb.append(line);
	        }
	    }
	    String body = sb.toString();
	    Type type = new TypeToken<Map<String, Object>>() {}.getType();
	    return  new Gson().fromJson(body, type); 
	}
	
	private String extractTargetPath(String requetUri) {
		String[] uriPaths = requetUri.split("/api/admin");
		String   targetUriPath = uriPaths[uriPaths.length - 1];
		return targetUriPath;
	}
}
