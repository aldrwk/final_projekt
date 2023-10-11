package com.spring.final_project.host;

import com.spring.final_project.member.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.InternalResourceView;

import java.time.LocalDateTime;

@Controller
@RequestMapping("/host")
public class hostController {
	private static final Logger log = LoggerFactory.getLogger(memberController.class);


	private memberService memberService;
	private PasswordEncoder passwordEncoder;
	private hostService hostService;

	@Autowired
	public hostController(memberService memberService, PasswordEncoder passwordEncoder, hostService hostService) {
		this.memberService = memberService;
		this.passwordEncoder = passwordEncoder;
		this.hostService = hostService;
	}

	@GetMapping("/regist")
	public String regist() {
		return "host/regist";
	}

	@PostMapping("/registproc")
	public String registproc(memberDomain member, hostDomain host) {
		LocalDateTime Date = LocalDateTime.now();
		member.setAuthorization("host");
		if (host.getHostname().equals("")) {
			host.setHostname("free Host");
		}
		if (memberService.findById(member.getEmail()) == null) {
			if (member.getName().equals("")) {
				member.setName("free");
			}
			member.setProfile("/image/default_profile.webp");
			member.setPassword(passwordEncoder.encode(member.getPassword()));
			member.setChangeDate(Date);
			member.setCreateDate(Date);
			memberService.insert(member);
		}
		int memberNum = memberService.findById(member.getEmail()).getNum();
		host.setMemberNum(memberNum);
		host.setChangeHostDate(Date);
		host.setCreateHostDate(Date);
		memberService.updateAuth(member);
		hostService.insert(host);
		return "redirect:/";
	}

	@GetMapping("/center")
	public String center() {
		return "host/managecenter";
	}

	@GetMapping("/info/{hostNum}")
	public String hostInfo(@PathVariable("hostNum") int hostNum) {

		return "host/hostinfo";
	}

	@PostMapping("/profile")
	public String hostProfile(Model model) {
		model.addAttribute("replace", "testtest");
		return "/host/profile ::profile";
	}
}
