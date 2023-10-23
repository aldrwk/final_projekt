package com.spring.final_project.host;

import com.spring.final_project.account.accountDomain;
import com.spring.final_project.account.accountService;
import com.spring.final_project.bank.BankDomain;
import com.spring.final_project.bank.BankService;
import com.spring.final_project.category_first.FirstCategoryDomain;
import com.spring.final_project.category_first.FirstCategoryService;
import com.spring.final_project.member.*;
import com.spring.final_project.product.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.*;

import static com.spring.final_project.util.dateService.*;
import static com.spring.final_project.util.fileUploadService.imageUpload;
import static com.spring.final_project.util.folderService.createFolder;
import static com.spring.final_project.util.messages.*;


@Controller
@RequestMapping("/host")
public class hostController {
	private static final Logger log = LoggerFactory.getLogger(memberController.class);


	private memberService memberService;
	private PasswordEncoder passwordEncoder;
	private hostService hostService;
	private BankService bankService;
	private accountService accountService;

	private productService productService;
	private productOptionService productOptionService;
	private FirstCategoryService firstCategoryService;

	private int result;

	@Autowired
	public hostController(memberService memberService, PasswordEncoder passwordEncoder, hostService hostService, BankService bankService, accountService accountService,productService productService,
						  FirstCategoryService firstCategoryService,productOptionService productOptionService) {
		this.memberService = memberService;
		this.passwordEncoder = passwordEncoder;
		this.hostService = hostService;
		this.bankService = bankService;
		this.accountService = accountService;
		this.productService = productService;
		this.firstCategoryService = firstCategoryService;
		this.productOptionService = productOptionService;
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
		return REDIRECT_HOME;
	}

	@GetMapping("/change")
	public String change(HttpSession session) {
		memberDomain member = (memberDomain) session.getAttribute("user_info");
		hostDomain host = hostService.findById(member.getEmail());
		if (host == null) {
			return "redirect:/host/regist";
		}
		session.setAttribute("host_info", host);
		return REDIRECT_HOME;
	}


	@GetMapping("/center")
	public String center(HttpSession session, Model model) {
		hostDomain host = (hostDomain) session.getAttribute("host_info");
		Map<String, Object> freeInThisMonth = new HashMap<>();
		freeInThisMonth.put("hostNum", host.getHostNum());
		freeInThisMonth.put("thisMoth", thisMonth()+"%");
		int freeCountInThisMonth = productService.countInThisMonth(freeInThisMonth);
		model.addAttribute("freeCountInThisMonth", freeCountInThisMonth);
		return "host/managecenter";
	}

	@GetMapping("/info/{hostNum}")
	public String hostInfo(@PathVariable("hostNum") int hostNum, Model model) {
		hostDomain host = hostService.findByHostNum(hostNum);
		List<FirstCategoryDomain> firstCategory = firstCategoryService.findAll();
		List<productDomain> products = productService.findPopular();
		List<Map<String, Object>> productpacks = new ArrayList<>();
		for (productDomain product : products) {
			Map<String, Object> productpack = new HashMap<>();
			int productNum = product.getProducNum();
			productOptionDomain productOption = productOptionService.OneOptionByProduct(productNum);
			productpack.put("product", product);
			productpack.put("productoption", productOption);
			productpacks.add(productpack);
		}
		model.addAttribute("categorys", firstCategory);
		model.addAttribute("productpacks", productpacks);
		model.addAttribute("host", host);
		return "host/hostinfo";
	}

	@PostMapping("/profile")
	public String hostProfile() {
		return "/host/profile ::profile";
	}

	@PostMapping("/profileupdate")
	public String profileupdate(hostDomain host, String email, MultipartFile hostprofile, HttpSession session) {
		hostDomain originHost = (hostDomain) session.getAttribute("host_info");
		memberDomain member = memberService.findByNum(originHost.memberNum);

		if (hostprofile != null && !hostprofile.isEmpty()) {
				// 이미지를 저장할 경로 설정
				String saveFolder = "/image/host/" + toDay();
			String saveName = imageUpload(saveFolder, hostprofile);
				host.setHostProfile(saveName);
		}
		if (email.equals(member.getEmail())) {
			host.setMemberNum(originHost.getMemberNum());
			host.setChangeHostDate(LocalToDayTime());
			host.setHostNum(originHost.getHostNum());
			result = hostService.updateInfo(host);
			session.setAttribute("host_info", host);
		}
		return result == SUCCESS ? "/host/managecenter" : UPDATE_FAIL_PAGE;
	}

	@PostMapping("/konto")
	public String hostKonto(Model model) {
		List<BankDomain> banks = bankService.findAll();
		model.addAttribute("banks", banks);
		return "/host/konto ::konto";
	}

	@PostMapping("/kontoupdate")
	public String kontoUpdate(String bankName, accountDomain account, HttpSession session) {
		BankDomain bank = bankService.findByBank(bankName);
		account.setBankCode(bank.getBankCode());
		account.setChangeDate(LocalToDayTime());
		hostDomain host = (hostDomain) session.getAttribute("host_info");
		int hostNum = host.getHostNum();
		account.setHostNum(hostNum);

		if (accountService.findById(hostNum) != null) {
			accountService.update(account);
		} else {
			accountService.insert(account);
		}
		return "/host/managecenter";
	}


	@GetMapping("/logout")
	public String hostLogout(HttpSession session) {
		session.removeAttribute("host_info");
		return REDIRECT_HOME;
	}

}
