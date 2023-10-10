package com.spring.final_project.member;

import com.spring.final_project.api.kakao.KakaoService;
import com.spring.final_project.api.kakao.kakaoAcountDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;

import static com.spring.final_project.util.dateService.*;
import static com.spring.final_project.util.folderService.createFolder;

@Controller
public class memberController {

	private static final Logger logger = LoggerFactory.getLogger(memberController.class);

	private KakaoService kakaoService;
	private memberService memberService;
	private PasswordEncoder passwordEncoder;

	@Autowired
	public memberController(KakaoService kakaoService, memberService memberService, PasswordEncoder passwordEncoder) {
		this.kakaoService = kakaoService;
		this.memberService = memberService;
		this.passwordEncoder = passwordEncoder;
	}


	@GetMapping("sign-up")
	public String signUp() {
		return "member/signup";
	}

	@PostMapping("signupProc")
	public String signupProc(memberDomain member, Model model) {
		if (member.getName() == "") {
			member.setName("free");
		}
		member.setProfile("/image/default_profile.webp");
		LocalDateTime Date = LocalDateTime.now();
		member.setChangeDate(Date);
		member.setCreateDate(Date);

		member.setPassword(passwordEncoder.encode(member.getPassword()));
		int result = memberService.insert(member);
		if (result == 1) {
//			CompletableFuture<Void> future = CompletableFuture.runAsync(() ->{
//				vo.setTo(member.getEmail());
//				vo.setContent(member.getId() + "님 회원 가입");
//				sendMail.sendMail(vo);
//			});
//			redirectAttributes.addFlashAttribute("result", "joinSuccess");
			return "redirect:/";
		}
		return "redirect:/";
	}


	@GetMapping("login")
	public String login(@RequestParam(required = false) String redirectPath, Model model, HttpServletRequest request) {

		HttpSession session = request.getSession();
		if (redirectPath.equals("/login") || redirectPath.equals("/sign-up")) {
			redirectPath = "/";
		}
		session.setAttribute("redirectPath", redirectPath);
		model.addAttribute("redirectPath", redirectPath);
		return "member/login";
	}

//	@PostMapping("loginProc")
//	public String loginProc(String email, String password, Model model, HttpServletRequest request){
//		memberDomain member = memberService.findById(email);
//		if(!member.password.equals(password)){
//			return "/";
//		}
//		HttpSession session = request.getSession();
//		String redirectPath = String.valueOf(session.getAttribute("redirectPath"));
//		logger.info(redirectPath);
//		session.setAttribute("user_info", member);
//		return "redirect:" + redirectPath;
//	}

	@GetMapping("loginOk")
	public String loginOk(Model model, HttpServletRequest request) {
		HttpSession session = request.getSession();
		String redirectPath = String.valueOf(session.getAttribute("redirectPath"));
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		UserDetails user = (UserDetails) principal;
		memberDomain member = memberService.findById(user.getUsername());
		session.setAttribute("user_info", member);
		model.addAttribute("user_info", member);
		return "redirect:" + redirectPath;
	}

	@GetMapping("memberCheck")
	public ResponseEntity<Map<String, Integer>> memberCheck(String email) {
		memberDomain member = memberService.findById(email);
		Map<String, Integer> resultMap = new HashMap<>();
		int result = member == null ? 0 : 1;

		resultMap.put("result", result);

		return ResponseEntity.ok(resultMap);
	}

	@GetMapping("logout")
	public String logout(HttpServletRequest request) {
		HttpSession session = request.getSession();
		try {
			kakaoService.logoutProc((kakaoAcountDto) session.getAttribute("user_info"));
		} catch (Exception e) {
		}
		session.invalidate();
		return "redirect:/";
	}

	@GetMapping("mypage")
	public String mypage(String member, Model model) {

//        Member member로 정보 받아서 ㄱㄱ
		return "member/mypage";
	}

	@GetMapping("/mypage/info")
	public String info(String member, Model model) {
		return "member/info";
	}

	@PostMapping("/mypage/info/passwordupdate")
	public ResponseEntity passwordUpdate(String oldPassword, String newPassword, Model model, HttpServletRequest request, HttpSession session) {
		session = request.getSession();
		memberDomain member = (memberDomain) session.getAttribute("local_user_info");
		logger.info(oldPassword);
		logger.info(String.valueOf(member));
		if (!passwordEncoder.matches(oldPassword, member.getPassword())) {
			model.addAttribute("oldPassword", "not matched");
			return ResponseEntity.ok(model);
		} else {
			member.setPassword(passwordEncoder.encode(newPassword));
			memberService.updatePassword(member);
			model.addAttribute("result", "Success");
			session.invalidate();
			return ResponseEntity.ok(model);
		}


	}

	@PostMapping("/mypage/infoupdate")
	public String infoUpdate(String name, String email, MultipartFile profile, HttpSession session) {
		System.out.println(profile);
		memberDomain member = memberService.findById(email);
		if (!name.equals(member.getName())) {
			member.setName(name);
		}
		if (profile != null && !profile.isEmpty()) {
			try {
				// 이미지를 저장할 경로 설정
				String savePath = "/src/main/resources/static";
				String saveFolder = "/image/member/" + toDay();

				String realPath = System.getProperty("user.dir") + savePath + saveFolder;
				createFolder(realPath);
				String fileName = uploadTime() + "_" + profile.getOriginalFilename();
				String filePath = realPath + File.separator + fileName;
				File dest = new File(filePath);

				profile.transferTo(dest);
				String saveDbPath = saveFolder + File.separator + fileName;
				System.out.println("filePath : " + filePath);
				member.setProfile(saveDbPath);
				logger.info(member.getProfile());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		member.setChangeDate(LocalToDayTime());
		System.out.println(member);
		memberService.updateInfo(member);
		member = memberService.findById(email);
		session.setAttribute("user_info", member);

		return "redirect:/mypage";
	}
}


