package com.spring.final_project.product;

import com.spring.final_project.category_first.FirstCategoryDomain;
import com.spring.final_project.category_first.FirstCategoryService;
import com.spring.final_project.category_second.SecondCategoryDomain;
import com.spring.final_project.category_second.SecondCategoryService;
import com.spring.final_project.host.hostDomain;
import com.spring.final_project.host.hostService;
import com.spring.final_project.member.memberController;
import com.spring.final_project.reservation_dates.reservationDatesDomain;
import com.spring.final_project.reservation_dates.reservationDatesService;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.text.NumberFormat;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.*;

import static com.spring.final_project.util.dateService.LocalToDayTime;
import static com.spring.final_project.util.dateService.toDay;
import static com.spring.final_project.util.fileUploadService.imageUpload;
import static com.spring.final_project.util.schaduleService.DatesRetouch;

@Controller
public class productController {
	private static final Logger log = LoggerFactory.getLogger(memberController.class);


	FirstCategoryService firstCategoryService;
	SecondCategoryService secondCategoryService;

	hostService hostService;
	productService productService;
	productOptionService productOptionService;

	reservationDatesService reservationDatesService;


	@Autowired
	public productController(FirstCategoryService firstCategoryService, SecondCategoryService secondCategoryService, productService productService, productOptionService productOptionService, reservationDatesService reservationDatesService, hostService hostService) {
		this.hostService = hostService;
		this.firstCategoryService = firstCategoryService;
		this.secondCategoryService = secondCategoryService;
		this.productService = productService;
		this.productOptionService = productOptionService;
		this.reservationDatesService = reservationDatesService;
	}


	@GetMapping("product/{productNum}")
	public String productList(@PathVariable("productNum") int productNum, Model model) {

		productDomain product = productService.findByProductNum(productNum);
		hostDomain host = hostService.findByHostNum(product.getHostNum());
		productOptionDomain productOption = productOptionService.optionsByProduct(productNum);

		model.addAttribute("product", product);
		model.addAttribute("host", host);
		model.addAttribute("productOption", productOption);
		model.addAttribute("currentUrl", "/product/" + productNum);

		List<productOptionDomain> productsOption = new ArrayList<>();



		model.addAttribute("productsoption", productsOption);
		productService.viewCountUp(productNum);
		return "product/detail";
	}

	@GetMapping("product/{productNum}/participate")
	public String productCalender(@PathVariable("productNum") int productNum, Model model) {
		return "product/participate";
	}

	@GetMapping("/search")
	public String search(String search_data, Model model) {
		model.addAttribute("search_data", search_data);
		return "product/search";
	}

	@PostMapping("/product/regist")
	public String regist(Model model) {
		List<FirstCategoryDomain> firtCategory = firstCategoryService.findAll();
		model.addAttribute("firstcategory", firtCategory);
		return "/product/regist ::regist";
	}

	@GetMapping("/product/secondcategory")
	public String secondCategory(String name, Model model) {
		int firstCategoryNum = firstCategoryService.findByName(name);
		List<SecondCategoryDomain> secondCategory = secondCategoryService.findByFirstCategory(firstCategoryNum);

		model.addAttribute("secondCategory", secondCategory);
		String secondCate = "<li class=\"category categorys\" th:each=\"category: ${secondCategory}\">\n" +
				"<span th:text=\"${category.secCateName}\"></span>" +
				"</li>";
		return "/product/regist ::secondcategorys";
	}

	@PostMapping("/product/productregist")
	@Transactional
	public String productRegist(productDomain product, productOptionDomain productOption, HttpSession session, String categoryf, String categorys,
								@RequestParam(name = "address_detail") String addressDetail, MultipartFile file, String events) {
		hostDomain host = (hostDomain) session.getAttribute("host_info");
		if (file != null && !file.isEmpty()) {
			String saveFolder = "/image/product/" + toDay() + File.separator + host.getHostNum();
			String saveName = imageUpload(saveFolder, file);
			product.setThumnail(saveName);
		}
		int firstCategoryNum = firstCategoryService.findByName(categoryf);
		Map<String, Object> data = new HashMap<>();
		data.put("firstCategoryNum", firstCategoryNum);
		data.put("categoryName", categorys);
		int secondCategoryNum = secondCategoryService.findByName(data);
		product = productService.productSet(product, host.getHostNum(), addressDetail, secondCategoryNum);
		productService.insert(product);
		int productNum = productService.findProductNum(host.getHostNum());
		productOption.setProducNum(productNum);
		Locale locale = new Locale("ko", "KR"); // 한국 로케일 (한국어, 대한민국)
		NumberFormat numberFormat = NumberFormat.getNumberInstance(locale);
		String newPrice = String.valueOf(numberFormat.format(Integer.parseInt(productOption.getPrice())));
		productOption.setPrice(newPrice);
		productOptionService.insert(productOption);
		List<reservationDatesDomain> dates = DatesRetouch(events);
		for (reservationDatesDomain date : dates) {
			date.setProducNum(productNum);
			LocalDateTime closeDate = date.getReservDate().minus(1, ChronoUnit.DAYS).withHour(23).withMinute(59);
			date.setCloseDate(closeDate);
			date.setRegistDate(LocalToDayTime());
			date.setUpdateDate(LocalToDayTime());
			reservationDatesService.insert(date);
		}
		return "redirect:/host/center";
	}

	@PostMapping("/product/contentImgUpload")
	@ResponseBody
	public Map<String, String> contentImgUpload(MultipartFile file, HttpSession session) {
		hostDomain host = (hostDomain) session.getAttribute("host_info");
		String url = "";
		if (file != null && !file.isEmpty()) {
			String saveFolder = "/image/product/" + toDay() + File.separator + host.getHostNum();
			url = imageUpload(saveFolder, file);

		}
		Map<String, String> map = new HashMap<>();
		map.put("url", url);
		return map;
	}
}
