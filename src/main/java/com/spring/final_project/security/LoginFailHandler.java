package com.spring.final_project.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Service;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;


public class LoginFailHandler implements AuthenticationFailureHandler {

	private static final Logger logger = LoggerFactory.getLogger(CustomUserDetailsService.class);


	@Override
	public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {

		HttpSession httpSession = httpServletRequest.getSession();
		logger.info("로그인 실패 : " + e.getMessage());
		httpSession.setAttribute("loginfail", "loginFailMsg");
		String url = httpServletRequest.getContextPath() + "/member/login";
		httpServletResponse.sendRedirect(url);
	}
}
