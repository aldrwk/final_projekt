package com.spring.final_project.product;

import com.spring.final_project.category_first.FirstCategoryDomain;
import com.spring.final_project.category_first.FirstCategoryService;
import com.spring.final_project.category_second.SecondCategoryService;
import com.spring.final_project.category_second.UnitedCategoryVo;
import com.spring.final_project.host.HostDomain;
import com.spring.final_project.host.HostService;
import com.spring.final_project.member.MemberController;
import com.spring.final_project.reservation_dates.ReservationDatesDomain;
import com.spring.final_project.reservation_dates.ReservationDatesService;
import com.spring.final_project.util.CalendarVo;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import static com.spring.final_project.util.DateService.toDay;
import static com.spring.final_project.util.FileUploadService.imageUpload;

@Controller
public class ProductController {
	private static final Logger log = LoggerFactory.getLogger(MemberController.class);

	private static final String DB_UPDATE_FAIL = "fail";

	FirstCategoryService firstCategoryService;
	SecondCategoryService secondCategoryService;

	HostService hostService;
	ProductService productService;
	ProductOptionService productOptionService;

	ReservationDatesService reservationDatesService;


	@Autowired
	public ProductController(FirstCategoryService firstCategoryService, SecondCategoryService secondCategoryService, ProductService productService, ProductOptionService productOptionService, ReservationDatesService reservationDatesService, HostService hostService) {
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
		productpack = productService.setProductPack(products);
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
		List<ReservationDatesDomain> reservationDates = reservationDatesService.findByProductNum(productNum);
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
		Map<String, List<String>> secondCategoryMap = new HashMap<>();
		secondCategoryMap = secondCategoryService.setCategoryPerFirstCategory(firtCategory);
		JSONObject secondCategoryJson = new JSONObject(secondCategoryMap);
		model.addAttribute("secondCategory", secondCategoryJson.toString());
		return "/product/regist ::regist";
	}

	@PostMapping("/product/list")
	public String list(HttpSession session, HostDomain host, Model model) {
		host = (HostDomain) session.getAttribute("host_info");
		List<ProductDomain> productList = new ArrayList<>();
		productList = productService.findByHostNum(host.getHostNum());
		int productCount = productService.countByHostNum(host.getHostNum());
		model.addAttribute("productCount", productCount);
		model.addAttribute("products", productList);

		return "/product/list ::list";
	}

	@PostMapping("/product/modify/{productNum}")
	public String modify(@PathVariable("productNum") int productNum, Model model, HttpSession session) {

//		List<FirstCategoryDomain> firstCategorys = firstCategoryService.findAll();
//		UnitedCategoryVo unitedCategoryName = secondCategoryService.findCategorysName(productNum);
//		ProductDomain product = productService.findByProductNum(productNum);
//		List<ProductOptionDomain> productOptions = productOptionService.optionsByProduct(productNum);
//		List<CalendarVo> reservationDates = reservationDatesService.findByCalendarVo(productNum);
//
//		Map<String, List<String>> secondCategoryMap = new HashMap<>();
//		secondCategoryMap = secondCategoryService.setCategoryPerFirstCategory(firstCategorys);
//		JSONObject secondCategoryJson = new JSONObject(secondCategoryMap);
//		model.addAttribute("secondCategory", secondCategoryJson.toString());
//
//		model.addAttribute("firstcategory", firstCategorys);
//		model.addAttribute("unitedCategoryName", unitedCategoryName);
//		model.addAttribute("product", product);
//		model.addAttribute("productoptions", productOptions);
//		model.addAttribute("dates", reservationDates);


		CompletableFuture<List<FirstCategoryDomain>> firstCategorys = CompletableFuture.supplyAsync(() ->
				firstCategoryService.findAll());
		CompletableFuture<UnitedCategoryVo> unitedCategoryName = CompletableFuture.supplyAsync(() ->
				secondCategoryService.findCategorysName(productNum));
		CompletableFuture<ProductDomain> product = CompletableFuture.supplyAsync(() ->
				productService.findByProductNum(productNum));
		CompletableFuture<List<ProductOptionDomain>> productOptions = CompletableFuture.supplyAsync(() ->
				productOptionService.optionsByProduct(productNum));
		CompletableFuture<List<CalendarVo>> reservationDates = CompletableFuture.supplyAsync(() ->
				reservationDatesService.findByCalendarVo(productNum));

		CompletableFuture<Void> combinedFutureAllof = CompletableFuture.allOf(firstCategorys, unitedCategoryName, product, productOptions, reservationDates);
		combinedFutureAllof.thenRun(() -> {
			try {
				log.info(String.valueOf(unitedCategoryName.get()));

				Map<String, List<String>> secondCategoryMap = new HashMap<>();
				secondCategoryMap = secondCategoryService.setCategoryPerFirstCategory(firstCategorys.get());
				JSONObject secondCategoryJson = new JSONObject(secondCategoryMap);
				model.addAttribute("secondCategory", secondCategoryJson.toString());

				model.addAttribute("firstcategory", firstCategorys.get());
				model.addAttribute("unitedCategoryName", unitedCategoryName.get());
				model.addAttribute("product", product.get());
				model.addAttribute("productoptions", productOptions.get());
				model.addAttribute("dates", reservationDates.get());
			} catch (InterruptedException e) {
				throw new RuntimeException(e);
			} catch (ExecutionException e) {
				throw new RuntimeException(e);
			}
		}).join();
		session.setAttribute("productNum", productNum);
		return "/product/modify ::modify";
	}

	@PostMapping("/product/productregist")
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
		productService.insert(product, host, events, options);
//
//		int productNum = productService.findProductNum(host.getHostNum());
//		List<ReservationDatesDomain> dates = DatesRetouch(events);
//		log.info(dates.toString());
//
//		for (ReservationDatesDomain date : dates) {
//			date.setProducNum(productNum);
//			LocalDateTime closeDate = date.getReservDate().minus(1, ChronoUnit.DAYS).withHour(23).withMinute(59);
//			date.setCloseDate(closeDate);
//			date.setRegistDate(LocalToDayTime());
//			date.setUpdateDate(LocalToDayTime());
//			if (1 == reservationDatesService.insert(date)) {
//				int reservationDateId = reservationDatesService.getId(productNum);
//				productOptionService.listInsert(productNum, reservationDateId, options);
//				log.info("등록");
//			}
//		}
		return "redirect:/host/center";
	}

	@PostMapping("/product/productupdate")
	public String productUpdate(RedirectAttributes redirectAttributes, ProductDomain product, ProductOptionDomain productOption, HttpSession session, String categoryf, String categorys,
								@RequestParam(name = "address_detail") String addressDetail, MultipartFile file, String events, String options) {
		HostDomain host = (HostDomain) session.getAttribute("host_info");

		int productNum = (int) session.getAttribute("productNum");
		log.info(String.valueOf(productNum));
		log.info(file.getOriginalFilename());
		if (file.getOriginalFilename() != null && !file.getOriginalFilename().isEmpty()) {
			String saveFolder = "/image/product/" + toDay() + File.separator + host.getHostNum();
			String saveName = imageUpload(saveFolder, file);
			product.setThumnail(saveName);
		}

		int firstCategoryNum = firstCategoryService.findByName(categoryf);

		Map<String, Object> data = new HashMap<>();
		data.put("firstCategoryNum", firstCategoryNum);
		data.put("categoryName", categorys);
		int secondCategoryNum = secondCategoryService.findByName(data);
		product.setProducNum(productNum);
		product = productService.productSet(product, host.getHostNum(), addressDetail, secondCategoryNum);
		if (productService.update(product, host, events, options) == 0) {
			redirectAttributes.addFlashAttribute("result", DB_UPDATE_FAIL);
		}

		session.removeAttribute("productNum");
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
			return "redirect:/login?redirectPath=/product/" + productNum + "/participate";
		}
		ProductDomain product = productService.forPayByProductNum(productNum);
		ProductOptionDomain productOptions = productOptionService.optionsById(optionId);
		ReservationDatesDomain reservationDate = reservationDatesService.findById(reservId);
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
