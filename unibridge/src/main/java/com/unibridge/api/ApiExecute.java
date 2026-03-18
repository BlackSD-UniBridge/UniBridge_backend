package com.unibridge.api;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface ApiExecute<T> {
	public ApiResult<T> execute(HttpServletRequest request, HttpServletResponse response) 
	         throws ServletException, IOException;
}
