package com.spring.final_project.config;

import com.spring.final_project.security.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;


@EnableWebSecurity
@Configuration
public class securityConfig extends WebSecurityConfigurerAdapter {

	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
				.antMatchers("/static/**/**").permitAll()
//				.antMatchers("/member/loginProc").permitAll()
				.antMatchers("/loginProc").permitAll()
//				.antMatchers("/member/join").permitAll()
//				.antMatchers("/member/idcheck").permitAll()
//				.antMatchers("/member/joinProcess").permitAll()
//				.antMatchers("/member/memberlist").access("hasRole('ROLE_ADMIN')")
//				.antMatchers("/member/memberinfo").access("hasRole('ROLE_ADMIN')")
//				.antMatchers("/board/**").permitAll()
//				.antMatchers("/comment/**").permitAll()
				.antMatchers("/**/**").permitAll()
				.antMatchers("/*").permitAll()
				.and()
				.csrf()
				.ignoringAntMatchers("/api/**")
				.ignoringAntMatchers("/mypage/**")
				.ignoringAntMatchers("/host/regist")
				.ignoringAntMatchers("/host/registproc")
				.ignoringAntMatchers("/host/*");

		http.formLogin().loginPage("/login")
				.loginProcessingUrl("/loginProc")
				.usernameParameter("email")
				.passwordParameter("password")
				.successHandler(loginSuccessHandler())
				.failureHandler(loginFailHandler());

		http.logout()
				.logoutUrl("logout")
				.logoutSuccessUrl("/")
				.invalidateHttpSession(true)	;


//		http.rememberMe()
//				.rememberMeParameter("remember-me")
//				.rememberMeCookieName("remember-me")
//				.tokenValiditySeconds(2419200);

		http.exceptionHandling().accessDeniedHandler(accessDeniedHandler());
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(customUserService()).passwordEncoder(passwordEncoder());
	}

	@Bean
	public AuthenticationSuccessHandler loginSuccessHandler() {
		return new LoginSuccessHandler();
	}

	@Bean
	public UserDetailsService customUserService() {
		return new CustomUserDetailsService();  // com.spring.security에 존재 하는 모든 클래스파일에 @Service
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public AccessDeniedHandler accessDeniedHandler() {
		return new CustomAccessDeniedHandler();
	}

	@Bean
	public AuthenticationFailureHandler loginFailHandler() {
		return new LoginFailHandler();
	}


}
