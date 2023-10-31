package com.spring.final_project.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributesModelMap;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;


public class LoginFailHandler implements AuthenticationFailureHandler {

	private static final Logger logger = LoggerFactory.getLogger(CustomUserDetailsService.class);


	@Override
	public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
		HttpSession httpSession = httpServletRequest.getSession();
		String redirectPath = (String) httpSession.getAttribute("redirectPath");
		logger.info("로그인 실패 : " + e.getMessage());
		httpSession.setAttribute("loginresult", "fail");
		String url = "/login?redirectPath=" + redirectPath;
		httpServletResponse.sendRedirect(url);
	}
}
