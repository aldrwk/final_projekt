package com.spring.final_project.member;

import com.spring.final_project.api.kakao.KakaoLoginService;
import com.spring.final_project.api.kakao.KakaoAcountDto;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

import static com.spring.final_project.util.DateService.LocalToDayTime;
import static com.spring.final_project.util.DateService.toDay;
import static com.spring.final_project.util.FileUploadService.imageUpload;
import static com.spring.final_project.util.Messages.REDIRECT_HOME;

@Controller
public class MemberController {

	private static final Logger log = LoggerFactory.getLogger(MemberController.class);

	private final int NOBODY = 0;
	private final int SOMEONE = 1;


	private KakaoLoginService kakaoLoginService;
	private MemberService memberService;
	private PasswordEncoder passwordEncoder;

	@Autowired
	public MemberController(KakaoLoginService kakaoLoginService, MemberService memberService, PasswordEncoder passwordEncoder) {
		this.kakaoLoginService = kakaoLoginService;
		this.memberService = memberService;
		this.passwordEncoder = passwordEncoder;
	}


	@GetMapping("sign-up")
	public String signUp() {
		return "member/signup";
	}

	@PostMapping("signupProc")
	public String signupProc(MemberDomain member, Model model) {
		if (member.getName() == "") {
			member.setName("free");
		}
		member.setProfile("/image/default_profile.webp");
		member.setChangeDate(LocalToDayTime());
		member.setCreateDate(LocalToDayTime());

		member.setPassword(passwordEncoder.encode(member.getPassword()));
		int result = memberService.insert(member);
		if (result == 1) {
//			CompletableFuture<Void> future = CompletableFuture.runAsync(() ->{
//				vo.setTo(member.getEmail());
//				vo.setContent(member.getId() + "님 회원 가입");
//				sendMail.sendMail(vo);
//			});
//			redirectAttributes.addFlashAttribute("result", "joinSuccess");
			return REDIRECT_HOME;
		}
		return REDIRECT_HOME;
	}


	@GetMapping("login")
	public String login(@RequestParam(required = false) String redirectPath, Model model, HttpServletRequest request, RedirectAttributes redirectAttributes) {
		HttpSession session = request.getSession();
		if (redirectPath== null || redirectPath.equals("/login") || redirectPath.equals("/sign-up")) {
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
//		log.info(redirectPath);
//		session.setAttribute("user_info", member);
//		return "redirect:" + redirectPath;
//	}

	@GetMapping("loginOk")
	public String loginOk(Model model, HttpServletRequest request) {
		HttpSession session = request.getSession();
		String redirectPath = String.valueOf(session.getAttribute("redirectPath"));
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		UserDetails user = (UserDetails) principal;
		log.info(user.getUsername()+ "stestsete");
		MemberDomain member = memberService.findById(user.getUsername());
		session.setAttribute("user_info", member);
		return "redirect:" + redirectPath;
	}


	@GetMapping("memberCheck")
	public ResponseEntity<Map<String, Integer>> memberCheck(String email) {
		MemberDomain member = memberService.findById(email);
		Map<String, Integer> resultMap = new HashMap<>();
		int result = member == null ? NOBODY : SOMEONE;

		resultMap.put("result", result);

		return ResponseEntity.ok(resultMap);
	}

	@GetMapping("logout")
	public String logout(HttpServletRequest request) {
		HttpSession session = request.getSession();
		try {
			kakaoLoginService.logoutProc((KakaoAcountDto) session.getAttribute("user_info"));
		} catch (Exception e) {
		}
		session.invalidate();
		return REDIRECT_HOME;
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
		MemberDomain member = (MemberDomain) session.getAttribute("user_info");
		log.info(oldPassword);
		member = memberService.login(member.getEmail());
		log.info(String.valueOf(member));
		if (!passwordEncoder.matches(oldPassword, member.getPassword())) {
			model.addAttribute("oldPassword", "not matched");
			return ResponseEntity.ok(model);
		} else {
			member.setPassword(passwordEncoder.encode(newPassword));
			member.setChangeDate(LocalToDayTime());
			memberService.updatePassword(member);
			model.addAttribute("result", "Success");
			session.invalidate();
			return ResponseEntity.ok(model);
		}


	}

	@PostMapping("/mypage/infoupdate")
	public String infoUpdate(String name, String email, MultipartFile profile, HttpSession session) {
		System.out.println(profile);
		MemberDomain member = memberService.findById(email);
		if (!name.equals(member.getName())) {
			member.setName(name);
		}
		if (profile != null && !profile.isEmpty()) {
			// 이미지를 저장할 경로 설정
			String saveFolder = "/image/member/" + toDay();
			String saveName = imageUpload(saveFolder, profile);
			member.setProfile(saveName);
		}
		member.setChangeDate(LocalToDayTime());
		System.out.println(member);
		memberService.updateInfo(member);
		session.setAttribute("user_info", member);
		return "redirect:/mypage";
	}

	@GetMapping("member/change")
	public String change(HttpSession session) {
		session.removeAttribute("host_info");
		return REDIRECT_HOME;
	}
}


