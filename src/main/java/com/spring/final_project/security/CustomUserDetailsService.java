package com.spring.final_project.security;

import com.spring.final_project.member.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;

@Service
public class CustomUserDetailsService implements UserDetailsService {
	private static final Logger logger = LoggerFactory.getLogger(CustomUserDetailsService.class);


//	private SqlSessionTemplate sqlSessionTemplate;

	@Autowired
	private memberMapper member;


	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		logger.info("username = " + username+ " / ######################");
		memberDomain user = member.findById(username);
		if (user == null) {
			logger.info("username" + username + " not found");
			throw new UsernameNotFoundException("username" + username + " not found");
		}

//		GrantedAuthority : 인증 개체에 부여된 권한을 나타내기 위한 interface로 이를 구현한 구현체는 생성자에 권한을 문자열로 보내주면 됨
//		SimpleGrantedAuthority : GrantedAuthority의 구현체
		Collection<SimpleGrantedAuthority> roles = new ArrayList<>();

//		roles.add(new SimpleGrantedAuthority(user.getAuth()));

		UserDetails userDetails = new User(username, user.getPassword(), roles);


		return userDetails;
	}
}
