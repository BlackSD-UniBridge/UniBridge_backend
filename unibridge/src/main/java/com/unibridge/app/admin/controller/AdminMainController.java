package com.unibridge.app.admin.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.unibridge.app.Execute;
import com.unibridge.app.Result;
import com.unibridge.app.admin.dao.AdMainDAO;
import com.unibridge.app.admin.dto.AdMainDTO;

public class AdminMainController implements Execute{

   @Override
   public Result execute(HttpServletRequest request, HttpServletResponse response)
         throws ServletException, IOException {
      
      AdMainDAO adMainDAO = new AdMainDAO();
      Gson gson = new Gson();
      Result result = new Result();
      
      
      HashMap<String, Object> data = new HashMap<String, Object>();
      data.put("totalUsers", adMainDAO.memberTotal());
      data.put("mentorCount", adMainDAO.mentorTotal());
      data.put("menteeCount", adMainDAO.menteeTotal());
      data.put("matchingCount", adMainDAO.matchingTotal());
      data.put("todayVisitors", 58);
      
      List<AdMainDTO> boardList = adMainDAO.recentboard();
      List<AdMainDTO> memberList = adMainDAO.recentmember();
      
      request.setAttribute("summaryDataJson", gson.toJson(data));
      request.setAttribute("recentBoardsJson", gson.toJson(boardList));
      request.setAttribute("recentUsersJson", gson.toJson(memberList));
      
      System.out.println(request.getAttribute("summaryDataJson"));
      System.out.println(request.getAttribute("recentBoardsJson"));
      System.out.println(request.getAttribute("recentUsersJson"));
      
      result.setPath(request.getContextPath() + "/app/admin/adminMain/main.jsp");
      result.setRedirect(true);
      return result;
   };
   

}
