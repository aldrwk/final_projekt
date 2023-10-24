package com.spring.final_project.security;

import com.spring.final_project.member.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;


public class LoginSuccessHandler implements AuthenticationSuccessHandler {
	private final static Logger logger = LoggerFactory.getLogger(LoginSuccessHandler.class);


	private MemberService member;
	@Override
	public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,Authentication authentication) throws IOException, ServletException {
		logger.info("로그인 성공 : LoginSuccessHandler" );
//		HttpSession session = httpServletRequest.getSession();
//		String redirectPath = String.valueOf(session.getAttribute("redirectPath"));
//		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//		UserDetails user = (UserDetails) principal;
//		System.out.println(user.getUsername());
//		String email = user.getUsername();
//		memberDomain user_info = member.findById(email);

		String url = "/loginOk";
		httpServletResponse.sendRedirect(url);
	}
}
