package com.spring.final_project.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Service;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;



public class CustomAccessDeniedHandler implements AccessDeniedHandler {
	private static final Logger logger = LoggerFactory.getLogger(CustomAccessDeniedHandler.class);

	@Override
	public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, org.springframework.security.access.AccessDeniedException e) throws IOException, ServletException {
		logger.info("Access Denied Handler");
		logger.info(httpServletRequest.getRequestURI());
		String url = "error";
		RequestDispatcher dispatcher = httpServletRequest.getRequestDispatcher(url);
		httpServletRequest.setAttribute("key", "접근 권한 없는 사용자");
		dispatcher.forward(httpServletRequest, httpServletResponse);
	}
}
