package com.spring.final_project.product;

import com.spring.final_project.category_first.FirstCategoryDomain;
import com.spring.final_project.category_first.FirstCategoryService;
import com.spring.final_project.category_second.SecondCategoryDomain;
import com.spring.final_project.category_second.SecondCategoryService;
import com.spring.final_project.host.HostDomain;
import com.spring.final_project.host.HostService;
import com.spring.final_project.member.MemberController;
import com.spring.final_project.reservation_dates.reservationDatesDomain;
import com.spring.final_project.reservation_dates.reservationDatesService;
import com.spring.final_project.util.CalendarVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.*;

import static com.spring.final_project.util.DateService.LocalToDayTime;
import static com.spring.final_project.util.DateService.toDay;
import static com.spring.final_project.util.FileUploadService.imageUpload;
import static com.spring.final_project.util.SchaduleService.DatesRetouch;

@Controller
public class ProductController {
	private static final Logger log = LoggerFactory.getLogger(MemberController.class);


	FirstCategoryService firstCategoryService;
	SecondCategoryService secondCategoryService;

	HostService hostService;
	ProductService productService;
	ProductOptionService productOptionService;

	reservationDatesService reservationDatesService;


	@Autowired
	public ProductController(FirstCategoryService firstCategoryService, SecondCategoryService secondCategoryService, ProductService productService, ProductOptionService productOptionService, reservationDatesService reservationDatesService, HostService hostService) {
		this.hostService = hostService;
		this.firstCategoryService = firstCategoryService;
		this.secondCategoryService = secondCategoryService;
		this.productService = productService;
		this.productOptionService = productOptionService;
		this.reservationDatesService = reservationDatesService;
	}


	@GetMapping("product/{productNum}")
	public String productList(@PathVariable("productNum") int productNum, Model model) {
		List<ProductDomain> products = new ArrayList<>();
		List<Map<String, Object>> productpack = new ArrayList<>();
		ProductDomain product = productService.findByProductNum(productNum);
		HostDomain host = hostService.findByHostNum(product.getHostNum());
		ProductOptionDomain productOption = productOptionService.OneOptionByProduct(productNum);
		products.add(product);
		productpack= productService.setProductPack(products);
		model.addAttribute("product", product);
		model.addAttribute("host", host);
		model.addAttribute("productOption", productOption);
		model.addAttribute("currentUrl", "/product/" + productNum);
		model.addAttribute("productpack", productpack);

		List<ProductOptionDomain> productsOption = new ArrayList<>();


		model.addAttribute("productsoption", productsOption);
		productService.viewCountUp(productNum);
		return "product/detail";
	}

	@GetMapping("product/{productNum}/participate")
	public String productparticipate(@PathVariable("productNum") int productNum, Model model, HttpSession session, CalendarVo calendarVo) {
		List<reservationDatesDomain> reservationDates = reservationDatesService.findByProductNum(productNum);
		String productTitle = productService.getTitleByProductNum(productNum);
		List<ProductOptionDomain> productOption = productOptionService.optionsByDay(productNum);


		model.addAttribute("dates", reservationDates);
		model.addAttribute("productTitle", productTitle);
		model.addAttribute("productoption", productOption);
		model.addAttribute("productNum", productNum);
		return "product/participate";
	}

	@GetMapping("/search")
	public String search(String search_data, Model model) {
		String search = "%" + search_data + "%";
		List<ProductDomain> productList = productService.findByRecentSearch(search);
		List<Map<String, Object>> productpacks = productService.setProductPack(productList);
		int listCount = productList.size();
		model.addAttribute("search_data", search_data);
		model.addAttribute("searchCount", listCount);
		model.addAttribute("productpacks", productpacks);

		return "product/search";
	}

	@GetMapping("/category/{categoryName}")
	public String category(@PathVariable("categoryName") String categoryName, Model model) {
		List<Map<String, Object>> productpacks = productService.setProductPack(productService.findPerCategory(categoryName));
		model.addAttribute("categoryName", categoryName);
		model.addAttribute("productpacks", productpacks);
		return "product/category";
	}

	@PostMapping("/product/regist")
	public String regist(Model model) {
		List<FirstCategoryDomain> firtCategory = firstCategoryService.findAll();
		model.addAttribute("firstcategory", firtCategory);
		return "/product/regist ::regist";
	}

	@PostMapping("/product/list")
	public String list(HttpSession session,HostDomain host, Model model) {
		host = (HostDomain) session.getAttribute("host_info");
		List<ProductDomain> productList = new ArrayList<>();
		productList = productService.findByHostNum(host.getHostNum());
		int productCount = productService.countByHostNum(host.getHostNum());
		model.addAttribute("productCount", productCount);
		model.addAttribute("products", productList);

		return "/product/list ::list";
	}

	@PostMapping("/product/modify/{productNum}")
	public String modify(@PathVariable("productNum") int productNum, Model model) {
		List<FirstCategoryDomain> firtCategory = firstCategoryService.findAll();
		String firstCategoryName = firstCategoryService.findCategoryName(productNum);
		String secondCategoryName = secondCategoryService.findCategoryName(productNum);
		ProductDomain product = productService.findByProductNum(productNum);
		List<ProductOptionDomain> productOptions = productOptionService.optionsByProduct(productNum);
		List<CalendarVo> reservationDates = reservationDatesService.findByCalendarVo(productNum);

		model.addAttribute("firstcategory", firtCategory);
		model.addAttribute("firstCategoryName", firstCategoryName);
		model.addAttribute("secondCategoryName", secondCategoryName);
		model.addAttribute("product", product);
		model.addAttribute("productoptions", productOptions);
		model.addAttribute("dates", reservationDates);

		return "/product/modify ::modify";
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
	public String productRegist(ProductDomain product, ProductOptionDomain productOption, HttpSession session, String categoryf, String categorys,
								@RequestParam(name = "address_detail") String addressDetail, MultipartFile file, String events, String options) {
		HostDomain host = (HostDomain) session.getAttribute("host_info");

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
		List<reservationDatesDomain> dates = DatesRetouch(events);
		log.info(dates.toString());
		for (reservationDatesDomain date : dates) {
			date.setProducNum(productNum);
			LocalDateTime closeDate = date.getReservDate().minus(1, ChronoUnit.DAYS).withHour(23).withMinute(59);
			date.setCloseDate(closeDate);
			date.setRegistDate(LocalToDayTime());
			date.setUpdateDate(LocalToDayTime());
			if (1 == reservationDatesService.insert(date)) {
				int reservationDateId = reservationDatesService.getId(productNum);
				productOptionService.listInsert(productNum, reservationDateId, options);
				log.info("등록");
			}
		}
		return "redirect:/host/center";
	}

	@PostMapping("/product/contentImgUpload")
	@ResponseBody
	public Map<String, String> contentImgUpload(MultipartFile file, HttpSession session) {
		HostDomain host = (HostDomain) session.getAttribute("host_info");
		String url = "";
		if (file != null && !file.isEmpty()) {
			String saveFolder = "/image/product/" + toDay() + File.separator + host.getHostNum();
			url = imageUpload(saveFolder, file);

		}
		Map<String, String> map = new HashMap<>();
		map.put("url", url);
		return map;
	}

	@PostMapping("/pay/pay-method")
	public String payment(Model model, HttpSession session, String totalPrice, int reservId, int optionId, int productNum, int quantity) {
		if (session.getAttribute("user_info") == null) {
			return "redirect:/login?redirectPath=/product/"+productNum+"/participate";
		}
		ProductDomain product = productService.forPayByProductNum(productNum);
		ProductOptionDomain productOptions = productOptionService.optionsById(optionId);
		reservationDatesDomain reservationDate = reservationDatesService.findById(reservId);
		session.setAttribute("product", product);
		model.addAttribute("productNum", productNum);
		model.addAttribute("quantity", quantity);
		model.addAttribute("product", product);
		model.addAttribute("totalPrice", totalPrice);
		model.addAttribute("productoptions", productOptions);
		model.addAttribute("optionId", optionId);
		model.addAttribute("reservationDate", reservationDate);
		return "/payment/pay-method";
	}



}
