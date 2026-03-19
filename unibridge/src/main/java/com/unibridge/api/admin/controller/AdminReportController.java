package com.unibridge.api.admin.controller;

import java.io.IOException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.WeekFields;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.unibridge.api.ApiExecute;
import com.unibridge.api.ApiResult;
import com.unibridge.api.admin.dao.AdminReportDAO;
import com.unibridge.api.admin.dto.AdminReportDTO;

public class AdminReportController implements ApiExecute<String> {
	AdminReportDAO adminReportDAO = new AdminReportDAO();

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
		WeekFields weekFields = WeekFields.of(Locale.KOREA);
		
		List<AdminReportDTO> resultUsers = adminReportDAO.selectAll();
		Map<String, List<AdminReportDTO>> responseMap = resultUsers.stream()
	            .filter(Objects::nonNull)
	            .filter(dto -> dto.getMatchingStart() != null)
	            .sorted(Comparator.comparing(AdminReportDTO::getMatchingStart))
	            .collect(Collectors.groupingBy(
	                    dto -> {
	                    	LocalDate date = dto.getMatchingStart()
	                                .toInstant()
	                                .atZone(ZoneId.systemDefault())
	                                .toLocalDate();
	                        
	                        int weekYear = date.get(weekFields.weekBasedYear());
	                        return weekYear + "-" + String.format("%02d", date.getMonthValue());
	                    },
	                    LinkedHashMap::new,
	                    Collectors.toList()
	            ));
		
		Gson gson = new GsonBuilder()
			    .setDateFormat("yyyy-MM-dd HH:mm:ss")
			    .create();
		
		String strJson = gson.toJson(responseMap);
		apiResult.setRawData(strJson);
		apiResult.setContentType("application/json;charset=UTF-8");
	}

	private void doPost(HttpServletRequest request, HttpServletResponse response, ApiResult<String> apiResult) {
	}	
}
