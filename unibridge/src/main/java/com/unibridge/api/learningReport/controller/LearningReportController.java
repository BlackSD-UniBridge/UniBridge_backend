package com.unibridge.api.learningReport.controller;

import java.io.IOException;
import java.lang.reflect.Type;
import java.text.DateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoUnit;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.unibridge.api.ApiExecute;
import com.unibridge.api.ApiResult;
import com.unibridge.api.learningReport.dao.LearningReportDAO;
import com.unibridge.api.learningReport.dto.LrDTO;
import com.unibridge.api.learningReport.dto.LrDetailDTO;
import com.unibridge.api.learningReport.dto.LrMatchingInfoDTO;
import com.unibridge.api.learningReport.dto.LrSubjectDTO;

public class LearningReportController implements ApiExecute<String> {
	LearningReportDAO learningReportDAO = new LearningReportDAO();
	
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

	private void doGet(HttpServletRequest request, HttpServletResponse response, ApiResult<String> apiResult) {
		String requestURI = request.getRequestURI();
		String target = extractTargetPath(requestURI);
		

		
		switch (target) {
		case  "lr/searchAllReports.rep":
		case "/lr/searchAllReports.rep": {			
			Integer memberNumber = Integer.parseInt(request.getParameter("userNumber"));
			String  memberType 	 = request.getParameter("userType");
			apiResult = this.getSearchAllReports(apiResult, memberNumber, memberType);
		}
			break;
		case  "lr/selectAllSubjects.rep":
		case "/lr/selectAllSubjects.rep":
			apiResult = this.getSearchAllSubjects(apiResult);
			break;
		case  "lr/searchAllReportsNum.rep":
		case "/lr/searchAllReportsNum.rep": {
			Integer memberNumber = Integer.parseInt(request.getParameter("userNumber"));
			String  memberType 	 = request.getParameter("userType");
			apiResult = this.getSearchAllReportsNum(apiResult, memberNumber, memberType);
		}
			break;
		default:
			break;
		}
	}

	private void doPost(HttpServletRequest request, HttpServletResponse response, ApiResult<String> apiResult) {
	}
	
	private ApiResult<String> getSearchAllReports(ApiResult<String> apiResult, int memberNumber,  String memberType) {
		LrDetailDTO lrDetailDTO = this.learningReportDAO.selectLrDetail(memberNumber, memberType);
		if (
			lrDetailDTO.getReports() == null ||
			lrDetailDTO.getMatchingInfo() == null
		) {
			Map<String, String> retMap = new HashMap<String, String>();
			apiResult.setRawData(new Gson().toJson(retMap));
			apiResult.setContentType("application/json;charset=UTF-8");
			return apiResult;
		}
		
		LocalDate matchingStartDate = LearningReportController.toLocalDate(lrDetailDTO.getMatchingInfo().getMatchingStart());
		Map<Integer, List<LrDTO>> groupedReports = lrDetailDTO.getReports().stream()
				.map(value -> (LrDTO)value)
		        .filter(report -> report.getLrReportDate() != null)
		        .sorted(Comparator.comparing(report -> toLocalDate(((LrDTO)report).getLrReportDate())))
		        .collect(Collectors.groupingBy(
		                report -> calculateWeekGroup(
		                        matchingStartDate,
		                        toLocalDate(report.getLrReportDate())
		                ),
		                LinkedHashMap::new,
		                Collectors.toList()
		        ));
		
		DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		Gson gson = new GsonBuilder()
		        .registerTypeAdapter(Date.class, new JsonSerializer<Date>() {
		            @Override
		            public JsonElement serialize(Date src, Type typeOfSrc, JsonSerializationContext context) {
		                return new JsonPrimitive(
		                        src.toInstant()
		                           .atZone(ZoneId.of("Asia/Seoul"))
		                           .toLocalDate()
		                           .format(dateFormatter)
		                );
		            }
		        })
		        .create();
		
		String strJson = gson.toJson(groupedReports);
		apiResult.setRawData(strJson);
		apiResult.setContentType("application/json;charset=UTF-8");
		return apiResult;
	}
	
	private ApiResult<String> getSearchAllSubjects(ApiResult<String> apiResult) {
		List<LrSubjectDTO> subjects = this.learningReportDAO.selectLrAllSubjects();
		String strJson = new Gson().toJson(subjects);
		apiResult.setRawData(strJson);
		apiResult.setContentType("application/json;charset=UTF-8");
		return apiResult;
	}
	
	private ApiResult<String> getSearchAllReportsNum(ApiResult<String> apiResult, int memberNumber,  String memberType) {
		LrDetailDTO lrDetailDTO = this.learningReportDAO.selectLrDetail(memberNumber, memberType);
		if (
			lrDetailDTO.getReports() == null ||
			lrDetailDTO.getMatchingInfo() == null
		) {
			Map<String, String> retMap = new HashMap<String, String>();
			apiResult.setRawData(new Gson().toJson(retMap));
			apiResult.setContentType("application/json;charset=UTF-8");
			return apiResult;
		}
	    LocalDate matchingStartDate =
	            LearningReportController.toLocalDate(
	                    lrDetailDTO.getMatchingInfo().getMatchingStart()
	            );
	    
	    Map<Integer, Long> groupedReportCounts = lrDetailDTO.getReports().stream()
	            .map(value -> (LrDTO) value)
	            .filter(report -> report.getLrReportDate() != null)
	            .sorted(Comparator.comparing(report -> toLocalDate(report.getLrReportDate())))
	            .collect(Collectors.groupingBy(
	                    report -> calculateWeekGroup(
	                            matchingStartDate,
	                            toLocalDate(report.getLrReportDate())
	                    ),
	                    LinkedHashMap::new,
	                    Collectors.counting()
	            ));
	    
		int reportNum = lrDetailDTO.getReports().size();
		
		Map<String, Object> mapJson = new HashMap<String, Object>();
		mapJson.put("reportNum", reportNum);
		mapJson.put("reportNumPerWeek", groupedReportCounts);
		
		apiResult.setRawData(new Gson().toJson(mapJson));
		apiResult.setContentType("application/json;charset=UTF-8");
		return apiResult;
	}
	
	private static LocalDate toLocalDate(Date date) {
	    return date.toInstant()
	            .atZone(ZoneId.systemDefault())
	            .toLocalDate();
	}

	private static int calculateWeekGroup(LocalDate matchingStart, LocalDate reportDate) {
	    long days = ChronoUnit.DAYS.between(matchingStart, reportDate);

	    if (days < 0) {
	        return 1;
	    }

	    return (int) (days / 7) + 1;
	}
	
	private String extractTargetPath(String requetUri) {
		String[] uriPaths = requetUri.split("/api/user");
		String   targetUriPath = uriPaths[uriPaths.length - 1];
		return targetUriPath;
	}
}
